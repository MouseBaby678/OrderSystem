package dao;

import model.DiningTable;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiningTableDao {
    public static ResultSet list(Connection con) throws SQLException {
        StringBuilder strb = new StringBuilder("select * from diningtable where status = 0");
        PreparedStatement pstmt = con.prepareStatement(strb.toString());
        return pstmt.executeQuery();
    }
    public static int update(Connection con,DiningTable diningTable) throws SQLException {
        String sql = "update diningtable set status=? where t_id =?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, diningTable.getStatus());
        pstmt.setInt(2, diningTable.getT_id());
        return pstmt.executeUpdate();
    }
}
