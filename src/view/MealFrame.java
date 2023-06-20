package view;

        import dao.MealDao;
        import model.Meal;
        import util.DbUtil;
        import util.StringUtil;

        import java.awt.EventQueue;

        import javax.swing.*;
        import javax.swing.border.EmptyBorder;
        import java.awt.Font;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.MouseEvent;
        import java.awt.event.MouseListener;
        import java.math.BigDecimal;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import javax.swing.table.DefaultTableModel;

public class MealFrame extends JFrame {

    private JPanel contentPane;
    private JTextField searchTextField;
    private JTable table;
    private JButton deleteButton;
    private JButton addButton;
    private JButton updateButton;
    private JTextField mael_nameTextField;
    private JTextField priceTextField;

    private DbUtil dbUtil = new DbUtil();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MealFrame frame = new MealFrame();
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
    public MealFrame() {
        setTitle("菜品管理");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("菜名：");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel.setBounds(62, 23, 53, 35);
        contentPane.add(lblNewLabel);

        searchTextField = new JTextField();
        searchTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        searchTextField.setBounds(118, 30, 136, 23);
        contentPane.add(searchTextField);
        searchTextField.setColumns(10);

        JButton searchButton = new JButton("查询");
        searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        searchButton.setBounds(275, 27, 100, 30);
        contentPane.add(searchButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(62, 69, 312, 214);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "菜名", "价格"
                }
        ));
        table.getTableHeader().setFont(new Font("微软雅黑", Font.PLAIN, 16));
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fillMealName();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                fillMealName();
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

        deleteButton = new JButton("删除");
        deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        deleteButton.setBounds(293, 422, 97, 31);
        contentPane.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "请选择要删除的菜品!");
                    return;
                }
                String mealName = (String) table.getValueAt(selectedRow, 0);
                Connection con = null;
                try {
                    con = dbUtil.getCon();

                    // Check if the meal has associated foreign key references
                    if (MealDao.hasReferences(con, mealName)) {
                        // Update the status of the meal to indicate it is deleted
                        int num = MealDao.updateStatus(con, mealName, 1);
                        if (num == 1) {
                            JOptionPane.showMessageDialog(null, "删除成功!");
                            mael_nameTextField.setText("");
                            priceTextField.setText("");
                            fillTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "删除失败!");
                        }
                    } else {
                        // No associated foreign key references, delete the meal directly
                        int num = MealDao.delete(con, new Meal(mealName));
                        if (num == 1) {
                            JOptionPane.showMessageDialog(null, "删除成功!");
                            mael_nameTextField.setText("");
                            priceTextField.setText("");
                            fillTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "删除失败!");
                        }
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
        });


        addButton = new JButton("添加");
        addButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        addButton.setBounds(49, 422, 100, 31);
        contentPane.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mealName = mael_nameTextField.getText();
                String price = priceTextField.getText();

                if (StringUtil.isEmpty(mealName) || StringUtil.isEmpty(price)) {
                    JOptionPane.showMessageDialog(null, "请填写菜名和价格!");
                    return;
                }

                Connection con = null;
                try {
                    con = dbUtil.getCon();
                    Meal meal = new Meal();
                    meal.setMeal_name(mealName);
                    meal.setPrice(new BigDecimal(price));

                    MealDao.insert(con, meal);
                    JOptionPane.showMessageDialog(null, "添加成功!");
                    mael_nameTextField.setText("");
                    priceTextField.setText("");
                    fillTable();
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
        });

        updateButton = new JButton("修改");
        updateButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        updateButton.setBounds(171, 422, 100, 31);
        contentPane.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "请选择要修改的菜品!");
                    return;
                }

                String mealName = mael_nameTextField.getText();
                String price = priceTextField.getText();

                if (StringUtil.isEmpty(mealName) || StringUtil.isEmpty(price)) {
                    JOptionPane.showMessageDialog(null, "请填写菜名和价格!");
                    return;
                }

                Connection con = null;
                try {
                    con = dbUtil.getCon();
                    String oldMealName = (String) table.getValueAt(selectedRow, 0);

                    Meal meal = new Meal();
                    meal.setMeal_name(mealName);
                    meal.setPrice(new BigDecimal(price));

                    MealDao.update(con, oldMealName, meal);
                    JOptionPane.showMessageDialog(null, "修改成功!");
                    mael_nameTextField.setText("");
                    priceTextField.setText("");
                    fillTable();
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
        });


        JLabel lblNewLabel_1 = new JLabel("菜名：");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(90, 310, 53, 35);
        contentPane.add(lblNewLabel_1);

        mael_nameTextField = new JTextField();
        mael_nameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        mael_nameTextField.setColumns(10);
        mael_nameTextField.setBounds(167, 316, 136, 23);
        contentPane.add(mael_nameTextField);

        JLabel lblNewLabel_1_1 = new JLabel("价格：");
        lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(90, 355, 53, 35);
        contentPane.add(lblNewLabel_1_1);

        priceTextField = new JTextField();
        priceTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        priceTextField.setColumns(10);
        priceTextField.setBounds(167, 361, 136, 23);
        contentPane.add(priceTextField);
        fillTable();
    }
    private void fillTable() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet resultSet = MealDao.list(con, new Meal());

            while (resultSet.next()) {
                String mealName = resultSet.getString("meal_name");
                String price = resultSet.getString("price");

                Object[] rowData = {mealName, price};
                dtm.addRow(rowData);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void fillMealName(){
        int selectedRowIndex = table.getSelectedRow();
        String mealName = (String) table.getValueAt(selectedRowIndex,0);
        mael_nameTextField.setText(mealName);
    }
}
