package dao;

import model.Business;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
    public static int add(Connection con, Customer customer) throws SQLException {
        String sql = "insert into customer values(null,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, customer.getT_id());
        pstmt.setString(2, customer.getPhone_num());
        return pstmt.executeUpdate();
    }

    public static Customer list(Connection con, String phone_num) throws SQLException {
        Customer resultCustomer = null;
        String sql = "select * from customer where phone_num = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,phone_num);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            resultCustomer = new Customer();
            resultCustomer.setC_id(rs.getInt("c_id"));
            resultCustomer.setT_id(rs.getInt("t_id"));
            resultCustomer.setPhone_num(rs.getString("phone_num"));
        }
        return resultCustomer;
    }

}
