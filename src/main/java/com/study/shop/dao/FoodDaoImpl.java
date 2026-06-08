package com.study.shop.dao;

import com.study.shop.po.Food;
import com.study.shop.util.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {

    @Override
    public int addFood(Food food) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        String sql = "INSERT INTO t_food(food_name,category_id,price,description) VALUES(?,?,?,?)";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, food.getFoodName());
            pstmt.setInt(2, food.getCategoryId());
            pstmt.setInt(3, food.getPrice());
            pstmt.setString(4, food.getDescription());
            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt);
        }
        return rows;
    }

    @Override
    public int deleteFood(Integer foodId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        String sql = "UPDATE t_food SET is_deleted=1 WHERE food_id=?";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodId);
            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt);
        }
        return rows;
    }

    @Override
    public int updateFood(Food food) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        String sql = "UPDATE t_food SET food_name=?,category_id=?,price=?,description=? WHERE food_id=?";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, food.getFoodName());
            pstmt.setInt(2, food.getCategoryId());
            pstmt.setInt(3, food.getPrice());
            pstmt.setString(4, food.getDescription());
            pstmt.setInt(5, food.getFoodId());
            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt);
        }
        return rows;
    }

    @Override
    public Food findById(Integer foodId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Food food = null;
        String sql = "SELECT f.*,c.category_name FROM t_food f LEFT JOIN t_category c ON f.category_id=c.category_id WHERE f.food_id=? AND f.is_deleted=0";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, foodId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                food = new Food();
                food.setFoodId(rs.getInt("food_id"));
                food.setFoodName(rs.getString("food_name"));
                food.setCategoryId(rs.getInt("category_id"));
                food.setCategoryName(rs.getString("category_name"));
                food.setPrice(rs.getInt("price"));
                food.setDescription(rs.getString("description"));
                food.setDeleted(rs.getBoolean("is_deleted"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return food;
    }

    @Override
    public List<Food> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Food> list = new ArrayList<>();
        String sql = "SELECT f.*,c.category_name FROM t_food f LEFT JOIN t_category c ON f.category_id=c.category_id WHERE f.is_deleted=0";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getInt("food_id"));
                food.setFoodName(rs.getString("food_name"));
                food.setCategoryId(rs.getInt("category_id"));
                food.setCategoryName(rs.getString("category_name"));
                food.setPrice(rs.getInt("price"));
                food.setDescription(rs.getString("description"));
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }

    @Override
    public List<Food> findByCategoryId(Integer categoryId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Food> list = new ArrayList<>();
        String sql = "SELECT f.*,c.category_name FROM t_food f LEFT JOIN t_category c ON f.category_id=c.category_id WHERE f.is_deleted=0 AND f.category_id=?";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodId(rs.getInt("food_id"));
                food.setFoodName(rs.getString("food_name"));
                food.setCategoryId(rs.getInt("category_id"));
                food.setCategoryName(rs.getString("category_name"));
                food.setPrice(rs.getInt("price"));
                food.setDescription(rs.getString("description"));
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return list;
    }
}