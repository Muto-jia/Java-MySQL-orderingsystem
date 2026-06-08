package com.study.shop.dao;

import com.study.shop.po.Order;
import com.study.shop.util.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    // 下单 → 强制 state=1（待接单），店主就能看见了！
    @Override
    public int addOrder(Order order) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        String sql = "INSERT INTO t_order(desk_number,phone,food_id,price,amount,total,state) VALUES(?,?,?,?,?,?,1)";

        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, order.getDeskNumber());
            pstmt.setString(2, order.getPhone());
            pstmt.setInt(3, order.getFoodId());
            pstmt.setInt(4, order.getPrice());
            pstmt.setInt(5, order.getAmount());
            pstmt.setInt(6, order.getTotal());

            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt);
        }
        return rows;
    }

    // 店主查询待接单订单
    @Override
    public List<Order> findNoHandleOrder() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT o.*,f.food_name FROM t_order o LEFT JOIN t_food f ON o.food_id = f.food_id WHERE o.state = 1";

        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setDeskNumber(rs.getInt("desk_number"));
                order.setPhone(rs.getString("phone"));
                order.setFoodId(rs.getInt("food_id"));
                order.setFoodName(rs.getString("food_name"));
                order.setPrice(rs.getInt("price"));
                order.setAmount(rs.getInt("amount"));
                order.setTotal(rs.getInt("total"));
                order.setCreateTime(rs.getTimestamp("create_time"));
                order.setState(rs.getInt("state"));
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return orderList;
    }

    // 全部接单
    @Override
    public int receiveAllOrder() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        String sql = "UPDATE t_order SET state = 2 WHERE state = 1";

        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt);
        }
        return rows;
    }

    // 部分接单
    @Override
    public int receivePartOrder(String orderIds) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        String sql = "UPDATE t_order SET state = 2 WHERE state = 1 AND order_id IN (" + orderIds + ")";

        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt);
        }
        return rows;
    }

    // 取消订单
    @Override
    public int cancelOrder(Integer orderId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        String sql = "UPDATE t_order SET state = 3 WHERE order_id = ? AND state = 1";

        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt);
        }
        return rows;
    }

    // 顾客查询订单
    @Override
    public List<Order> findOrderByPhone(String phone, int type) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<>();
        String sql = null;

        if (type == 1) {
            sql = "SELECT o.*,f.food_name FROM t_order o LEFT JOIN t_food f ON o.food_id = f.food_id WHERE o.phone=? AND o.state=1";
        } else if (type == 2) {
            sql = "SELECT o.*,f.food_name FROM t_order o LEFT JOIN t_food f ON o.food_id = f.food_id WHERE o.phone=? AND o.state=2";
        } else {
            sql = "SELECT o.*,f.food_name FROM t_order o LEFT JOIN t_food f ON o.food_id = f.food_id WHERE o.phone=?";
        }

        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setDeskNumber(rs.getInt("desk_number"));
                order.setPhone(rs.getString("phone"));
                order.setFoodId(rs.getInt("food_id"));
                order.setFoodName(rs.getString("food_name"));
                order.setPrice(rs.getInt("price"));
                order.setAmount(rs.getInt("amount"));
                order.setTotal(rs.getInt("total"));
                order.setCreateTime(rs.getTimestamp("create_time"));
                order.setState(rs.getInt("state"));
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return orderList;
    }
}