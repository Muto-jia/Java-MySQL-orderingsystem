package com.study.shop.dao;

import com.study.shop.po.Category;
import com.study.shop.util.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 餐品类目 DAO 实现类
 * 使用 PreparedStatement 预编译，防止SQL注入
 * 逻辑删除：不物理删除，修改 is_deleted 字段
 */
public class CategoryDaoImpl implements CategoryDao {

    // 新增类目
    @Override
    public int addCategory(Category category) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        // SQL：仅插入类目名称，ID自增，默认未删除
        String sql = "INSERT INTO t_category(category_name) VALUES (?)";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            // 给占位符赋值
            pstmt.setString(1, category.getCategoryName());
            // 执行增删改，返回受影响行数
            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 无结果集，调用两个参数的关闭方法
            JdbcUtil.closeAll(conn, pstmt);
        }
        return rows;
    }

    // 逻辑删除类目
    @Override
    public int deleteCategory(Integer categoryId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        // 逻辑删除：修改is_deleted为1
        String sql = "UPDATE t_category SET is_deleted = 1 WHERE category_id = ?";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt);
        }
        return rows;
    }

    // 修改类目名称
    @Override
    public int updateCategory(Category category) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        String sql = "UPDATE t_category SET category_name = ? WHERE category_id = ?";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getCategoryName());
            pstmt.setInt(2, category.getCategoryId());
            rows = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt);
        }
        return rows;
    }

    // 根据ID查询单个类目
    @Override
    public Category findById(Integer categoryId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Category category = null;
        // 只查询未删除的数据
        String sql = "SELECT * FROM t_category WHERE category_id = ? AND is_deleted = 0";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
            rs = pstmt.executeQuery();
            // 遍历结果集
            if (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDeleted(rs.getBoolean("is_deleted"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 有结果集，调用三个参数的关闭方法
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return category;
    }

    // 查询所有正常类目
    @Override
    public List<Category> findAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM t_category WHERE is_deleted = 0";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDeleted(rs.getBoolean("is_deleted"));
                categoryList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return categoryList;
    }

    // 模糊查询类目
    @Override
    public List<Category> findByKey(String key) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Category> categoryList = new ArrayList<>();
        String sql = "SELECT * FROM t_category WHERE is_deleted = 0 AND category_name LIKE ?";
        try {
            conn = JdbcUtil.getConn();
            pstmt = conn.prepareStatement(sql);
            // 模糊查询拼接 % 通配符
            pstmt.setString(1, "%" + key + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setDeleted(rs.getBoolean("is_deleted"));
                categoryList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeAll(conn, pstmt, rs);
        }
        return categoryList;
    }
}