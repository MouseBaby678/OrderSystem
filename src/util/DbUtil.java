package util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
    //获取数据库连接
    public Connection getCon() throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, password);
        return con;
    }

    //关闭数据库
    public void closeCon(Connection con) throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public static void main(String[] args) {
        DbUtil dbUtil = new DbUtil();
        try {
            dbUtil.getCon();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fault");
        }
    }
}
