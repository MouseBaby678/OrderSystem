package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Order_MealDao {
    public static int add(Connection con, int o_id, int m_id) throws SQLException {
        String sql = "insert into order_meal values(?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, o_id);
        pstmt.setInt(2, m_id);
        return pstmt.executeUpdate();
    }
}
