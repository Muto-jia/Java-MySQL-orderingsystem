package com.study.shop.dao;

import com.study.shop.po.Category;
import java.util.List;

/**
 * 餐品类目 DAO 接口
 * 对应数据表：t_category
 */
public interface CategoryDao {

    /**
     * 新增类目
     * @param category 类目实体
     * @return 受影响行数
     */
    int addCategory(Category category);

    /**
     * 根据类目ID 逻辑删除（修改is_deleted=1）
     * @param categoryId 类目编号
     * @return 受影响行数
     */
    int deleteCategory(Integer categoryId);

    /**
     * 修改类目名称
     * @param category 类目实体（携带ID和新名称）
     * @return 受影响行数
     */
    int updateCategory(Category category);

    /**
     * 根据ID查询单个类目
     * @param categoryId 类目编号
     * @return 类目对象
     */
    Category findById(Integer categoryId);

    /**
     * 查询所有未删除的类目（is_deleted = 0）
     * @return 类目集合
     */
    List<Category> findAll();

    /**
     * 根据关键字模糊查询类目
     * @param key 查询关键字
     * @return 匹配的类目集合
     */
    List<Category> findByKey(String key);
}