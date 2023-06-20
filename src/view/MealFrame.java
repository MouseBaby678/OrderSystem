package view;

import dao.MealDao;
import util.DbUtil;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class MealFrame extends JFrame {

    private JPanel contentPane;
    private JTextField mealNameTextField;
    private JTable table;
    private JButton deleteButton;
    private JButton addButton;
    private JButton updateButton;
    private JTextField textField;
    private JTextField textField_1;

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

        mealNameTextField = new JTextField();
        mealNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        mealNameTextField.setBounds(118, 30, 136, 23);
        contentPane.add(mealNameTextField);
        mealNameTextField.setColumns(10);

        JButton searchButton = new JButton("查询");
        searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        searchButton.setBounds(275, 27, 100, 30);
        contentPane.add(searchButton);

//        searchButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                translationTextPane.setText("");
//                try {
//                    ResultSet rs = wordDao.list(dbUtil.getCon(), new Word(wordTextField.getText()));
//                    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
//                    dtm.setRowCount(0);
//                    while (rs.next()) {
//                        Vector v = new Vector<>();
//                        v.add(rs.getString("word"));
//                        v.add(rs.getString("translation"));
//                        dtm.addRow(v);
//                    }
//                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        });

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
//        table.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int selectedRow = table.getSelectedRow();
//                String word = (String) table.getValueAt(selectedRow, 0);
//                try {
//                    ResultSet rs = wordDao.list(dbUtil.getCon(), new Word(word));
//                    rs.next();
//                    translationTextPane.setText(rs.getString("translation"));
//
//
//                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int selectedRow = table.getSelectedRow();
//                String word = (String) table.getValueAt(selectedRow, 0);
//                try {
//                    ResultSet rs = wordDao.list(dbUtil.getCon(), new Word(word));
//                    rs.next();
//                    translationTextPane.setText(rs.getString("translation"));
//
//
//                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
        scrollPane.setViewportView(table);

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
                String word = (String) table.getValueAt(selectedRow, 0);
                Connection con = null;
                try {
                    con = dbUtil.getCon();
                    int num = MealDao.delete(con, word);
                    if (num == 1) {
                        JOptionPane.showMessageDialog(null, "删除成功!");
                        mealNameTextField.setText("");
                        translationTextPane.setText("");
                        fillTable(new Word());
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败!");
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

        updateButton = new JButton("修改");
        updateButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        updateButton.setBounds(171, 422, 100, 31);
        contentPane.add(updateButton);

        JLabel lblNewLabel_1 = new JLabel("菜名：");
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(90, 310, 53, 35);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        textField.setColumns(10);
        textField.setBounds(167, 316, 136, 23);
        contentPane.add(textField);

        JLabel lblNewLabel_1_1 = new JLabel("价格：");
        lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(90, 355, 53, 35);
        contentPane.add(lblNewLabel_1_1);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        textField_1.setColumns(10);
        textField_1.setBounds(167, 361, 136, 23);
        contentPane.add(textField_1);
//        updateButton.addActionListener(new A ctionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = table.getSelectedRow();
//                if (selectedRow == -1) {
//                    JOptionPane.showMessageDialog(null, "请选择要修改的单词!");
//                    return;
//                }
//                String word = (String) table.getValueAt(selectedRow, 0);
//                String translation = translationTextPane.getText();
//                Connection con = null;
//                try {
//                    con = dbUtil.getCon();
//                    int num = wordDao.update(con, new Word(word, translation));
//                    if (num == 1) {
//                        JOptionPane.showMessageDialog(null, "修改成功!");
//                        wordTextField.setText("");
//                        translationTextPane.setText("");
//                        fillTable(new Word());
//                    } else {
//                        JOptionPane.showMessageDialog(null, "修改失败!");
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                } finally {
//                    try {
//                        dbUtil.closeCon(con);
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        });
//        this.fillTable(new Word());
        private void fillTable() {
            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            dtm.setRowCount(0);
            Connection con = null;
            try {
                con = dbUtil.getCon();
                ResultSet resultSet = MealDao.list(con, meal);

                while (resultSet.next()) {
                    String mealName = resultSet.getString("mealName");
                    String price = resultSet.getString("price");

                    Object[] rowData = {mealName, price};
                    dtm.addRow(rowData);
                }
            } catch (SQLException e) {
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
    }
}
