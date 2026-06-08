package com.study.shop.dao;

import com.study.shop.po.Shopowner;
import com.study.shop.util.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShopownerDaoImpl implements ShopownerDao {

    @Override
    public Shopowner login(String phone, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Shopowner owner = null;

        String sql = "select * from t_shopowner where phone=? and password=?";

        try {
            conn = JdbcUtil.getConn();
            if (conn == null) return null;

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);
            pstmt.setString(2, pwd);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                owner = new Shopowner();
                owner.setShopId(rs.getInt("shop_id"));
                owner.setAddress(rs.getString("address"));
                owner.setShopowner(rs.getString("shopowner"));
                owner.setPhone(rs.getString("phone"));
                owner.setPassword(rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }

        return owner;
    }
}