package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Order;
import util.StringUtil;

public class OrderDao {
    public static ResultSet list(Connection con, Order order) throws SQLException {
        StringBuilder strb = new StringBuilder("SELECT * FROM `order` WHERE 1");

        if (order.getO_id() != 0) {
            strb.append(" AND o_id = ").append(order.getO_id());
        }
        if (StringUtil.isNotEmpty(order.getPhone_num())) {
            strb.append(" AND phone_num = ").append(order.getPhone_num());
        }
        PreparedStatement pstmt = con.prepareStatement(strb.toString());
        return pstmt.executeQuery();
    }

    public static int update(Connection con, Order order) throws SQLException {
        String sql = "UPDATE `order` SET cost_money = ? WHERE o_id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setBigDecimal(1, order.getCost_money());
        pstmt.setInt(2, order.getO_id());
        return pstmt.executeUpdate();
    }

    public static int add(Connection con, Order order) throws SQLException {
        String sql = "insert into `order` (t_id, c_id, phone_num) values(?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, order.getT_id());
        pstmt.setInt(2, order.getC_id());
        pstmt.setString(3, order.getPhone_num());
        return pstmt.executeUpdate();
    }
}
