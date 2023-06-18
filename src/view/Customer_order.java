package view;

import dao.DiningTableDao;
import dao.MealDao;
import dao.OrderDao;
import dao.Order_MealDao;
import model.Meal;
import model.Order;
import util.DbUtil;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Customer_order extends JFrame {

    private JPanel contentPane;
    private static DbUtil dbUtil= new DbUtil();
    private JTable Customer_order_table;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) throws SQLException {
//        try
//        {
//            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//
//        } catch(Exception e){
//            //TODO exception
//        }
//        UIManager.put("RootPane.setupButtonVisible",false);
//        Customer_order frame = new Customer_order(1);
//        frame.setVisible(true);
//    }

    /**
     * Create the frame.
     */
    public Customer_order(String phone_num) throws SQLException {
        int o_id = getO_id(phone_num);
        setIconImage(Toolkit.getDefaultToolkit().getImage("img/菜谱.png"));
        setTitle("点餐界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 853, 735);
        contentPane = new JPanel();
        contentPane.setBorder(null);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel Meal_name_Label = new JLabel("菜名");
        Meal_name_Label.setFont(new Font("宋体", Font.PLAIN, 29));
        Meal_name_Label.setIcon(new ImageIcon("img/菜菜.png"));
        Meal_name_Label.setBounds(122, 29, 153, 88);
        contentPane.add(Meal_name_Label);

        JLabel Meal_price_Label = new JLabel("价格");
        Meal_price_Label.setFont(new Font("宋体", Font.PLAIN, 29));
        Meal_price_Label.setIcon(new ImageIcon("img/钱.png"));
        Meal_price_Label.setBounds(386, 29, 153, 88);
        contentPane.add(Meal_price_Label);

        JLabel Meal_choose_Label = new JLabel("选择");
        Meal_choose_Label.setFont(new Font("宋体", Font.PLAIN, 29));
        Meal_choose_Label.setIcon(new ImageIcon("img/对号.png"));
        Meal_choose_Label.setBounds(626, 29, 153, 88);
        contentPane.add(Meal_choose_Label);

        JButton Meal_order_Button = new JButton("下单");
        Meal_order_Button.setFont(new Font("宋体", Font.PLAIN, 33));
        Meal_order_Button.setBounds(307, 531, 232, 106);
        contentPane.add(Meal_order_Button);
        Meal_order_Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                List<Meal> list = listMeal();
                for(Meal meal:list){
                    Connection con = null;
                    try {
                        con = dbUtil.getCon();
                        int m_id = get_Mid(meal.getMeal_name());
                        Order_MealDao.add(con,o_id, m_id);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }finally {
                        try {
                            dbUtil.closeCon(con);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });
        JScrollPane Customer_order_scrollPane = new JScrollPane();
        Customer_order_scrollPane.setBounds(111, 127, 597, 357);
        contentPane.add(Customer_order_scrollPane);

        Customer_order_table = new JTable();
        Customer_order_table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u83DC\u540D", "\u4EF7\u683C", "\u8D2D\u4E70"
                }
        ) {
            Class[] columnTypes = new Class[] {
                    String.class, String.class, Boolean.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        Customer_order_scrollPane.setViewportView(Customer_order_table);
        fillTable();

    }

    private void fillTable() throws SQLException {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet resultSet = MealDao.list(con, new Meal());
            DefaultTableModel dtm = (DefaultTableModel) this.Customer_order_table.getModel();
            dtm.setRowCount(0);
            while (resultSet.next()){
                Vector v = new Vector<>();
                v.add(resultSet.getString("meal_name"));
                v.add(resultSet.getString("price"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            dbUtil.closeCon(con);
        }
    }

    private List<Meal> listMeal(){
        List<Meal> list = new ArrayList<Meal>();
        for(int i = 0; i< Customer_order_table.getRowCount(); i++){
            if((Boolean) Customer_order_table.getValueAt(i,2)){
                list.add(new Meal((String) Customer_order_table.getValueAt(i,0),
                        BigDecimal.valueOf(Double.valueOf((String) Customer_order_table.getValueAt(i,1)) )));
            }
        }
        return list;
    }

    private int getO_id(String phone_num) throws SQLException {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet result = OrderDao.list(con,new Order(phone_num));
            if (result.next()) {
                return result.getInt("o_id");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            dbUtil.closeCon(con);
        }
        return 0;
    };

    private static int get_Mid(String meal_name) throws SQLException {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet result = MealDao.list(con,new Meal(meal_name));
            if (result.next()) {
                return result.getInt("m_id");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            dbUtil.closeCon(con);
        }
        return 0;
    };

}
