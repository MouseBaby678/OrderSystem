package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Customer_index extends JFrame {

    private JPanel contentPane;
    private JTextField phone_textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch(Exception e){
            //TODO exception
        }
        UIManager.put("RootPane.setupButtonVisible",false);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Customer_index frame = new Customer_index();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Customer_index() {
        setTitle("餐桌选择界面");
        setIconImage(Toolkit.getDefaultToolkit().getImage("img/餐厅.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 720, 640);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        setContentPane(contentPane);

        phone_textField = new JTextField();
        phone_textField.setColumns(10);

        JLabel table_Label = new JLabel("请选择桌号：");
        table_Label.setFont(new Font("宋体", Font.PLAIN, 26));

        JLabel phone_Number_Label = new JLabel("请输入手机号：");
        phone_Number_Label.setFont(new Font("宋体", Font.PLAIN, 26));

        JButton Confirm_Button = new JButton("开始点餐");
        Confirm_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }
        });
        Confirm_Button.setFont(new Font("宋体", Font.PLAIN, 22));

        JComboBox table_comboBox = new JComboBox();
        table_comboBox.setToolTipText("1");
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(phone_Number_Label, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(table_Label))
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(phone_textField, 474, 474, 474)
                                                        .addComponent(table_comboBox, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(197)
                                                .addComponent(Confirm_Button, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(253, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(109)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(table_Label, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(table_comboBox, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                                .addGap(131)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(phone_Number_Label, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(phone_textField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                .addComponent(Confirm_Button, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                .addGap(86))
        );
        contentPane.setLayout(gl_contentPane);
    }
}
