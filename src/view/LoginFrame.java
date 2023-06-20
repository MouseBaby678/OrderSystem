package view;

import dao.BusinessDao;
import model.Business;
import util.DbUtil;
import util.StringUtil;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JPanel contentPane;
    private JTextField userNameText;

    private DbUtil dbUtil = new DbUtil();
    private JPasswordField passwordTxt;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
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
    public LoginFrame() {
        setResizable(false);
        setTitle("后台登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 970, 570);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
        lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/用户名.png")));
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(683, 190, 91, 30);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("\u5BC6   \u7801\uFF1A");
        lblNewLabel_1_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/密码.png")));
        lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(683, 245, 91, 30);
        contentPane.add(lblNewLabel_1_1);

        userNameText = new JTextField();
        userNameText.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        userNameText.setBounds(774, 190, 138, 30);
        contentPane.add(userNameText);
        userNameText.setColumns(10);

        //登录
        JButton loginButton = new JButton("\u767B\u5F55");
        loginButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/\u767B\u5F55.png")));
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });
        loginButton.setBackground(new Color(240, 248, 255));
        loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 22));
        loginButton.setBounds(711, 328, 185, 46);
        contentPane.add(loginButton);


        //登 录 账 号
        JLabel lblNewLabel_2 = new JLabel("\u767B \u5F55 \u8D26 \u53F7");
        lblNewLabel_2.setForeground(new Color(30, 144, 255));
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 34));
        lblNewLabel_2.setBounds(713, 75, 167, 73);
        contentPane.add(lblNewLabel_2);


        passwordTxt = new JPasswordField();
        passwordTxt.setBounds(774, 245, 138, 30);
        contentPane.add(passwordTxt);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/login.png")));
        lblNewLabel.setBounds(0, 0, 960, 540);
        contentPane.add(lblNewLabel);
        this.setLocationRelativeTo(null);
    }

    //登录事件处理
    private void loginActionPerformed(ActionEvent e) {
        String userName = this.userNameText.getText();
        String password = new String(this.passwordTxt.getPassword());
        if (StringUtil.isEmpty(userName)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空!");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空!");
            return;
        }

        Business business = new Business(userName, password);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            Business currentUser = BusinessDao.login(con, business);
            if (currentUser != null) {
                dispose();
                OrderFrame orderFrame = new OrderFrame();
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
