package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

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
    private JTextField textField;
    private JTable m_table;

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
        frame.setTitle("订单页面");
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

        m_table = new JTable();
        m_table.setFont(new Font("宋体", Font.PLAIN, 15));
        m_table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "已点菜品"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        scrollPane_1.setViewportView(m_table);

        ordertable = new JTable();
        ordertable.setFont(new Font("宋体", Font.PLAIN, 15));
        ordertable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "订单编号", "桌号", "顾客编号", "手机号", "消费金额"
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

        JLabel lblNewLabel_2 = new JLabel("修改金额");
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

        textField = new JTextField();
        textField.setColumns(10);

        JButton btnNewButton_1 = new JButton("确认");
        btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 15));

        JButton btnNewButton_2 = new JButton("就餐完成");
        btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 15));
        btnNewButton_2.setToolTipText("");
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
                                .addGap(28)
                                .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                                .addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                .addGap(123))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(27)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnNewButton_1)
                                        .addComponent(btnNewButton_2))
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);

        JLabel lblNewLabel = new JLabel("订单编号");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        o_idTxt = new JTextField();
        o_idTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("手机号");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

        phone_numTxt = new JTextField();
        phone_numTxt.setColumns(10);

        JButton btnNewButton = new JButton("查询");
        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
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
                                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(32, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                                .addComponent(phone_numTxt, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(o_idTxt, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(33, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        frame.getContentPane().setLayout(groupLayout);
    }
}
