package com.study.shop.util;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        System.out.println("==== 开始测试数据库连接 ====");
        Connection conn = JdbcUtil.getConn();

        if (conn != null) {
            System.out.println("✅ 连接成功！");
        } else {
            System.out.println("❌ 连接失败！");
        }
    }
}