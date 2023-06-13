package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealDao {
    public static ResultSet list(Connection con) throws SQLException {
        String sql = new String("select * from meal");
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeQuery();
    }
}
