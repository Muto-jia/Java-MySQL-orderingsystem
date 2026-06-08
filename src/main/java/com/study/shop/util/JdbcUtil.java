package com.study.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUtil {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/jdbc_shop?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public static final String USER = "root";
    public static final String PWD = "root";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        try {
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception e) {
            System.out.println("数据库连接失败!!!");
            e.printStackTrace();
            return null;
        }
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeAll(Connection conn, Statement stmt) {
        closeAll(conn, stmt, null);
    }
}