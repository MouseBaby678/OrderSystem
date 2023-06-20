package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import dao.DiningTableDao;
import dao.OrderDao;
import model.DiningTable;
import model.Meal;
import model.Order;
import util.DbUtil;
import util.StringUtil;

public class OrderFrame {

    private JFrame frame;
    private JTable ordertable;
    private JTextField o_idTxt;
    private JTextField phone_numTxt;
    private JTextField cost_moneyField;
    private JTable menu_table;
    private DbUtil dbUtil = new DbUtil();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        System.setProperty("sun.java2d.noddraw", "true");


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
    public OrderFrame() throws SQLException {
        frame = new JFrame();
        frame.setTitle("订单页面");
        frame.setBounds(550, 200, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JScrollPane scrollPane = new JScrollPane();

        JPanel panel = new JPanel();

        JPanel panel_1 = new JPanel();

        JScrollPane scrollPane_1 = new JScrollPane();
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(groupLayout);
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
        menu_table.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        menu_table.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 20));
        menu_table.setRowHeight(30);
        menu_table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "已点菜品"
                }
        ) {
            boolean[] columnEditables = new boolean[]{
                    false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        scrollPane_1.setViewportView(menu_table);

        ordertable = new JTable();
        ordertable.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        ordertable.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 20));
        ordertable.setRowHeight(30);
        ordertable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "订单编号", "桌号", "顾客编号", "手机号", "消费金额"
                }
        ) {
            boolean[] columnEditables = new boolean[]{
                    false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        scrollPane.setViewportView(ordertable);
        ordertable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    fillMenuTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    fillMenuTable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JLabel lblNewLabel_2 = new JLabel("修改金额");
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

        cost_moneyField = new JTextField();
        cost_moneyField.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        cost_moneyField.setColumns(10);

        JButton confirmButton = new JButton("确认");
        confirmButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        confirmButton.addActionListener(e -> {
            try {
                updateOrder();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        JButton completeButton = new JButton("就餐完成");
        completeButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        completeButton.setToolTipText("");
        completeButton.addActionListener(e -> {
            try {
                free();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        });

        JButton mealButton = new JButton("\u83DC\u5355\u7BA1\u7406");
        mealButton.setToolTipText("");
        mealButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        mealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.setProperty("sun.java2d.noddraw", "true");

                try
                {
                    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

                } catch(Exception error){
                    //TODO exception
                }
                UIManager.put("RootPane.setupButtonVisible",false);
                MealFrame mealFrame = new MealFrame();
                mealFrame.setVisible(true);
            }
        });
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
                                .addGap(104)
                                .addComponent(completeButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                                .addComponent(mealButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                .addGap(27))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addGap(27)
                                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(cost_moneyField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(confirmButton)))
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addGap(26)
                                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(completeButton)
                                                        .addComponent(mealButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(29, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);

        JLabel lblNewLabel = new JLabel("订单编号");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        o_idTxt = new JTextField();
        o_idTxt.setFont(new Font("宋体", Font.PLAIN, 20));
        o_idTxt.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("手机号");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

        phone_numTxt = new JTextField();
        phone_numTxt.setFont(new Font("宋体", Font.PLAIN, 20));
        phone_numTxt.setColumns(10);

        JButton selectButton = new JButton("查询");
        selectButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        selectButton.addActionListener(e -> {
            try {
                searchOrder();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        GroupLayout gl_panel = new GroupLayout(panel);
        panel.setLayout(gl_panel);
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
        frame.getContentPane().setLayout(groupLayout);
        fillTable();
    }

    /**
     * Initialize the contents of the frame.
     */

    private void fillTable() throws SQLException {
        Connection con = null;
        Order order = new Order();
        try {
            con = dbUtil.getCon();
            ResultSet resultSet = OrderDao.list(con, order);
            DefaultTableModel model = (DefaultTableModel) ordertable.getModel();
            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(resultSet.getInt("o_id"));
                rowData.add(resultSet.getInt("t_id"));
                rowData.add(resultSet.getInt("c_id"));
                rowData.add(resultSet.getString("phone_num"));
                rowData.add(resultSet.getDouble("cost_money"));
                model.addRow(rowData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            dbUtil.closeCon(con);
        }
    }

    private void searchOrder() throws SQLException {
        Connection con = null;
        Order order = new Order();
        String o_id = o_idTxt.getText();
        String phone_num = phone_numTxt.getText();
        try {
            con = dbUtil.getCon();
            if (!o_id.isEmpty()) {
                order.setO_id(Integer.parseInt(o_id));
            }
            if (!phone_num.isEmpty()) {
                order.setPhone_num(phone_num);
            }
            ResultSet resultSet = OrderDao.list(con, order);
            DefaultTableModel model = (DefaultTableModel) ordertable.getModel();
            model.setRowCount(0); // Clear the existing table data
            clearInputFields();
            while (resultSet.next()) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(resultSet.getInt("o_id"));
                rowData.add(resultSet.getInt("t_id"));
                rowData.add(resultSet.getInt("c_id"));
                rowData.add(resultSet.getString("phone_num"));
                rowData.add(resultSet.getDouble("cost_money"));
                model.addRow(rowData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            dbUtil.closeCon(con);
        }
        DefaultTableModel model = (DefaultTableModel) menu_table.getModel();
        model.setRowCount(0); // 清空原有数据
    }

    private void updateOrder() throws SQLException {
        Connection con = null;
        Order order = new Order();
        String cost_money = cost_moneyField.getText();
        try {
            con = dbUtil.getCon();
            if (!cost_money.isEmpty()) {
                order.setCost_money(BigDecimal.valueOf(Double.parseDouble(cost_money)));
            } else {
                JOptionPane.showMessageDialog(null, "请选择要修改的金额", "提示", JOptionPane.INFORMATION_MESSAGE);
                return; // No value entered, do not update
            }
            int selectedRowIndex = ordertable.getSelectedRow();
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(null, "请选择要修改的订单", "提示", JOptionPane.INFORMATION_MESSAGE);
                return; // No row selected, do not update
            }
            int o_id = (int) ordertable.getValueAt(selectedRowIndex, 0);
            order.setO_id(o_id);
            int affectedRows = OrderDao.update(con, order);
            if (affectedRows > 0) {
                // Update successful
                DefaultTableModel model = (DefaultTableModel) ordertable.getModel();
                model.setRowCount(0);
                fillTable(); // Refresh the table with updated data
                clearInputFields();
                JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Update failed
                JOptionPane.showMessageDialog(null, "修改失败", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            dbUtil.closeCon(con);
        }
    }

    private void free() throws SQLException {

        Connection con = null;
        //diningTable.setStatus(0);
        try {
            con = dbUtil.getCon();
            int selectedRowIndex = ordertable.getSelectedRow();
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(null, "请选择完成就餐的订单", "提示", JOptionPane.INFORMATION_MESSAGE);
                return; // No row selected, do not update
            }
            int c_id = (int) ordertable.getValueAt(selectedRowIndex, 1);
            DiningTable diningTable = new DiningTable(c_id,1);
            diningTable.setStatus(0);
            int rowsAffected = DiningTableDao.update(con, diningTable);
            if (rowsAffected > 0) {
                // 更新成功
                JOptionPane.showMessageDialog(null, "就餐完成", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // 更新失败
                JOptionPane.showMessageDialog(null, "操作失败", "提示", JOptionPane.INFORMATION_MESSAGE);
            }

            // 关闭连接
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            dbUtil.closeCon(con);
        }
    }

    private void fillMenuTable() throws SQLException {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int selectedRowIndex = ordertable.getSelectedRow();
            int o_id = (int) ordertable.getValueAt(selectedRowIndex, 0);

            // 调用OrderDao中的getMealNamesForOrder方法获取订单的菜名列表
            List<String> mealNames = OrderDao.getMealNamesForOrder(con, o_id);

            DefaultTableModel model = (DefaultTableModel) menu_table.getModel();
            model.setRowCount(0); // 清空原有数据

            // 将菜名添加到表格中
            for (String mealName : mealNames) {
                Vector<Object> rowData = new Vector<>();
                rowData.add(mealName);
                model.addRow(rowData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            dbUtil.closeCon(con);
        }

    }

    private void clearInputFields() {
        o_idTxt.setText("");
        phone_numTxt.setText("");
        cost_moneyField.setText("");
    }
}
