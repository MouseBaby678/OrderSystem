package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import util.StringUtil;

public class OrderDao {
    public List<Order> list(Connection con, Order order) throws SQLException {
        List<Order> orders = new ArrayList<>();
        StringBuilder strb = new StringBuilder("SELECT * FROM orders WHERE 1=1");

        if (order.getO_id() != 0) {
            strb.append(" AND o_id = ").append(order.getO_id());
        }
        if (StringUtil.isNotEmpty(order.getPhone_num())) {
            strb.append(" AND phone_num = ").append(order.getPhone_num());
        }

        String sql = strb.toString();
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            int o_id = rs.getInt("o_id");
            int t_id = rs.getInt("t_id");
            int c_id = rs.getInt("c_id");
            String phone_num = rs.getString("phone_num");
            String dish_name = rs.getString("dish_name");
            BigDecimal cost_money = rs.getBigDecimal("cost_money");

            Order order1 = new Order(o_id, t_id, c_id, phone_num, dish_name, cost_money);
            orders.add(order1);
        }
        rs.close();
        pstmt.close();
        return orders;
    }

    public int update(Connection con, int orderId, BigDecimal newCost) throws SQLException {
        String sql = "UPDATE orders SET cost_money = ? WHERE o_id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setBigDecimal(1, newCost);
        pstmt.setInt(2, orderId);
        return pstmt.executeUpdate();
    }
}
