package dao;

import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDao {
    public static int add(Connection con, Customer customer) throws SQLException {
        String sql = "insert into customer values(null,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, customer.getT_id());
        pstmt.setString(2, customer.getPhone_num());
        return pstmt.executeUpdate();
    }
}
