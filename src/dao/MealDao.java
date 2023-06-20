package dao;

import model.Meal;
import util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealDao {
    public static ResultSet list(Connection con, Meal meal) throws SQLException {
        StringBuilder sql = new StringBuilder("select * from meal where is_deleted = 0");
        if(StringUtil.isNotEmpty(meal.getMeal_name())){
            sql.append(" and meal_name = '").append(meal.getMeal_name()).append("'");
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

    public static int insert(Connection con, Meal meal) throws SQLException {
        String sql = "INSERT INTO meal (meal_name, price) VALUES (?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, meal.getMeal_name());
        pstmt.setBigDecimal(2, meal.getPrice());
        return pstmt.executeUpdate();
    }


    public static int update(Connection con, String oldMealName, Meal meal) throws SQLException {
        String sql = "UPDATE meal SET meal_name=?, price=? WHERE meal_name=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, meal.getMeal_name());
        pstmt.setBigDecimal(2, meal.getPrice());
        pstmt.setString(3, oldMealName);
        return pstmt.executeUpdate();
    }

    public static boolean hasReferences(Connection con, String mealName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM meal,order_meal WHERE meal.m_id = order_meal.m_id AND meal_name = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, mealName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
    public static int updateStatus(Connection con, String mealName, int isDeleted) throws SQLException {
        String sql = "UPDATE meal SET is_deleted = ? WHERE meal_name = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, isDeleted);
            pstmt.setString(2, mealName);
            System.out.println(sql);
            return pstmt.executeUpdate();
        }
    }
}
