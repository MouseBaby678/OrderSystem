package dao;

import model.Meal;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealDao {
    public static ResultSet list(Connection con, Meal meal) throws SQLException {
        StringBuilder sql = new StringBuilder("select * from meal ");
        if(StringUtil.isNotEmpty(meal.getMeal_name())){
            sql.append("where meal_name = ").append(meal.getMeal_name());
        }
        PreparedStatement pstmt = con.prepareStatement(sql.toString());
        return pstmt.executeQuery();
    }

    public static int delete(Connection con, Meal meal) throws Exception {
        String sql = "delete from meal where meal_name=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, meal.getMeal_name());
        return pstmt.executeUpdate();
    }
}
