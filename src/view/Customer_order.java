package view;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Toolkit;

public class Customer_order extends JFrame {

    private JPanel contentPane;

    private JTable Customer_order_table;

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
                    Customer_order frame = new Customer_order();
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
    public Customer_order() {
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
                    String.class, Double.class, Boolean.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        Customer_order_scrollPane.setViewportView(Customer_order_table);

    }
}
