package view;

import dao.*;
import model.Customer;
import model.DiningTable;
import model.Meal;
import model.Order;
import util.DbUtil;
import util.StringUtil;

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
        setBounds(600, 200, 853, 735);
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
//                System.out.println(calculateTotalPrice(list));
//                BigDecimal totalPrice = calculateTotalPrice(list);
//                String message = "总价格：" + totalPrice.toString();
//                JOptionPane.showMessageDialog(null, message);
                BigDecimal totalPrice = calculateTotalPrice(list);
                if (totalPrice.compareTo(BigDecimal.ZERO)==0){
                    String message = "请选择菜品再下单。" ;
                    JOptionPane.showMessageDialog(null, message);
                }else {
                    Connection con = null;
                    try {
                        con = dbUtil.getCon();
                        OrderDao.update(con, new Order(o_id,totalPrice));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }finally {
                        try {
                            dbUtil.closeCon(con);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    StringBuilder messageBuilder = new StringBuilder();
                    messageBuilder.append("菜品  价格\n");
                    for (Meal meal : list) {
                        messageBuilder.append(meal.getMeal_name()).append(" ").append(meal.getPrice()).append("\n");
                    }
                    messageBuilder.append("\n总价格：").append(totalPrice.toString());


//                JOptionPane.showMessageDialog(null, messageBuilder.toString());
                    int result = JOptionPane.showOptionDialog(null, messageBuilder.toString(), "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{"确定"}, null);
                    if (result == JOptionPane.OK_OPTION) {
                        dispose();

                    }
                }



            }
        });
        JScrollPane Customer_order_scrollPane = new JScrollPane();
        Customer_order_scrollPane.setBounds(111, 127, 597, 357);
        contentPane.add(Customer_order_scrollPane);

        Customer_order_table = new JTable();
        Customer_order_table.setFont(new Font("微软雅黑", Font.PLAIN, 20));
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
        Customer_order_table.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 20));
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

    private List<Meal> listMeal() {
        List<Meal> list = new ArrayList<Meal>();
        for (int i = 0; i < Customer_order_table.getRowCount(); i++) {
            Object value = Customer_order_table.getValueAt(i, 2);
            if (value != null && value instanceof Boolean && (Boolean) value) {
                Object mealNameObj = Customer_order_table.getValueAt(i, 0);
                Object priceObj = Customer_order_table.getValueAt(i, 1);
                if (mealNameObj instanceof String && priceObj instanceof String) {
                    String mealName = (String) mealNameObj;
                    String priceStr = (String) priceObj;
                    BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceStr));
                    list.add(new Meal(mealName, price));
                }
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
    private void show_check(int t_id, String phone_num) throws SQLException {
        if (StringUtil.isEmpty(phone_num)) {
            JOptionPane.showMessageDialog(null, "手机号不能为空!");
            return;
        }
        Connection con = null;
        Customer customer = new Customer(t_id, phone_num);
        try {
            con = dbUtil.getCon();
            int addCustomNum = CustomerDao.add(con,customer);
            if (addCustomNum == 1) {
                int c_id = CustomerDao.list(con, phone_num).getC_id();
                int updateDiningTableNum = DiningTableDao.update(con,new DiningTable(t_id,1));
                int addOrderNum = OrderDao.add(con, new Order(t_id, c_id, phone_num));
                if(updateDiningTableNum == 1 && addOrderNum == 1){
                    dispose();
                    try
                    {
                        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

                    } catch(Exception e){
                        //TODO exception
                    }
                    UIManager.put("RootPane.setupButtonVisible",false);
                    Customer_order frame = new Customer_order(phone_num);
                    frame.setVisible(true);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            dbUtil.closeCon(con);
        }
    }

    private BigDecimal calculateTotalPrice(List<Meal> listMeal) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Meal meal : listMeal) {
            BigDecimal price = meal.getPrice();
            totalPrice = totalPrice.add(price);
        }

        return totalPrice;
    }




}
