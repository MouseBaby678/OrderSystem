package view;

import dao.CustomerDao;
import dao.DiningTableDao;
import model.Customer;
import model.DiningTable;
import util.DbUtil;
import util.StringUtil;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer_index extends JFrame {

    private JPanel contentPane;
    private JTextField phone_textField;
    private DbUtil dbUtil = new DbUtil();
    private JComboBox table_comboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws SQLException {
        try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

        } catch(Exception e){
            //TODO exception
        }
        UIManager.put("RootPane.setupButtonVisible",false);
        Customer_index frame = new Customer_index();
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
    public Customer_index() throws SQLException {
        setIconImage(Toolkit.getDefaultToolkit().getImage("img/餐厅.png"));
        setTitle("点餐界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 760, 632);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        phone_textField = new JTextField();
        phone_textField.setFont(new Font("宋体", Font.PLAIN, 20));
        phone_textField.setBounds(215, 309, 333, 45);
        phone_textField.setColumns(10);

        JLabel table_Label = new JLabel("请选择桌号：");
        table_Label.setBounds(15, 114, 158, 54);
        table_Label.setFont(new Font("宋体", Font.PLAIN, 26));

        JLabel phone_Number_Label = new JLabel("请输入手机号：");
        phone_Number_Label.setBounds(15, 300, 196, 54);
        phone_Number_Label.setFont(new Font("宋体", Font.PLAIN, 26));

        JButton Confirm_Button = new JButton("开始点餐");
        Confirm_Button.setBounds(202, 442, 286, 62);
        Confirm_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    order(Integer.parseInt(table_comboBox.getSelectedItem().toString()),phone_textField.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        Confirm_Button.setFont(new Font("宋体", Font.PLAIN, 22));

        table_comboBox = new JComboBox();
        table_comboBox.setFont(new Font("宋体", Font.PLAIN, 20));
        table_comboBox.setBounds(215, 121, 128, 48);
        table_comboBox.setToolTipText("1");
        contentPane.setLayout(null);
        contentPane.add(phone_Number_Label);
        contentPane.add(table_Label);
        contentPane.add(phone_textField);
        contentPane.add(table_comboBox);
        contentPane.add(Confirm_Button);
        fillTable();
    }

    private void order(int t_id, String phone_num) throws SQLException {
        if (StringUtil.isEmpty(phone_num)) {
            JOptionPane.showMessageDialog(null, "手机号不能为空!");
            return;
        }
        Connection con = null;
        Customer customer = new Customer(t_id, phone_num);
        try {
            con = dbUtil.getCon();
            int addnum = CustomerDao.add(con,customer);
            int updatenum = DiningTableDao.update(con,new DiningTable(t_id,1));
            if (addnum == 1 && updatenum == 1) {
                dispose();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            dbUtil.closeCon(con);
        }
    }

    private void fillTable() throws SQLException {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet resultSet = DiningTableDao.list(con);
            while (resultSet.next()){
                table_comboBox.addItem(resultSet.getString("t_id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            dbUtil.closeCon(con);
        }

    }
}
