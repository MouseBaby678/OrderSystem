package dao;

import model.Business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BusinessDao {
    public Business login(Connection con, Business business) throws Exception {
        Business resultBusiness = null;
        String sql = "select * from ordersystem where username=? and password=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, business.getUsername());
        pstmt.setString(2, business.getPassword());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            resultBusiness = new Business();
            resultBusiness.setB_id(rs.getInt("b_id"));
            resultBusiness.setUsername(rs.getString("username"));
            resultBusiness.setPassword(rs.getString("password"));
            resultBusiness.setRestaurant_name(rs.getString("restaurant_name"));
        }
        return resultBusiness;
    }
}
