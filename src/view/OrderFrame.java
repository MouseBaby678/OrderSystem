package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Order;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class OrderFrame {

    private JFrame frame;
    private JTable ordertable;
    private JTextField o_idTxt;
    private JTextField phone_numTxt;
    private JTextField cost_moneyField;
    private JTable menu_table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    OrderFrame window = new OrderFrame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public OrderFrame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("\u8BA2\u5355\u9875\u9762");
        frame.setBounds(100, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane();

        JPanel panel = new JPanel();

        JPanel panel_1 = new JPanel();

        JScrollPane scrollPane_1 = new JScrollPane();
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 873, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 875, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(20)
                                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 698, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                .addGap(9))
        );

        menu_table = new JTable();
        menu_table.setFont(new Font("宋体", Font.PLAIN, 15));
        menu_table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u5DF2\u70B9\u83DC\u54C1"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        scrollPane_1.setViewportView(menu_table);

        ordertable = new JTable();
        ordertable.setFont(new Font("宋体", Font.PLAIN, 18));
        ordertable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u8BA2\u5355\u7F16\u53F7", "\u684C\u53F7", "\u987E\u5BA2\u7F16\u53F7", "\u624B\u673A\u53F7", "\u6D88\u8D39\u91D1\u989D"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        scrollPane.setViewportView(ordertable);

        JLabel lblNewLabel_2 = new JLabel("\u4FEE\u6539\u91D1\u989D");
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

        cost_moneyField = new JTextField();
        cost_moneyField.setFont(new Font("宋体", Font.PLAIN, 18));
        cost_moneyField.setColumns(10);

        JButton confirmButton = new JButton("\u786E\u8BA4");
        confirmButton.setFont(new Font("宋体", Font.PLAIN, 18));

        JButton completeButton = new JButton("\u5C31\u9910\u5B8C\u6210");
        completeButton.setFont(new Font("宋体", Font.PLAIN, 18));
        completeButton.setToolTipText("");
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(28)
                                .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(cost_moneyField, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(confirmButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                                .addComponent(completeButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                .addGap(103))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(27)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cost_moneyField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(confirmButton)
                                        .addComponent(completeButton))
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);

        JLabel lblNewLabel = new JLabel("\u8BA2\u5355\u7F16\u53F7");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        o_idTxt = new JTextField();
        o_idTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u624B\u673A\u53F7");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

        phone_numTxt = new JTextField();
        phone_numTxt.setColumns(10);

        JButton selectButton = new JButton("\u67E5\u8BE2");
        selectButton.setFont(new Font("宋体", Font.PLAIN, 18));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(o_idTxt, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(phone_numTxt, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(selectButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(32, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(phone_numTxt, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(selectButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(o_idTxt, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(33, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        frame.getContentPane().setLayout(groupLayout);
    }
}
