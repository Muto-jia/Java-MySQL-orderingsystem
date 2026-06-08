package com.study.shop.po;

/**
 * 类目实体类 对应数据表 t_category
 */
public class Category {
    private Integer categoryId;    // 类目编号
    private String categoryName;   // 类目名称
    private boolean deleted;       // 是否逻辑删除 0=正常 1=删除

    // 无参构造（JDBC反射必需）
    public Category() {}

    // 全参构造
    public Category(Integer categoryId, String categoryName, boolean deleted) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.deleted = deleted;
    }

    // Get & Set 方法
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    // 重写toString 控制台打印使用
    @Override
    public String toString() {
        return "类目编号：" + categoryId + "，类目名称：" + categoryName;
    }
}