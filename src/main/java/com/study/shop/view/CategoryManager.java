package com.study.shop.view;

import com.study.shop.dao.CategoryDao;
import com.study.shop.dao.CategoryDaoImpl;
import com.study.shop.po.Category;
import java.util.List;
import java.util.Scanner;

/**
 * 餐品类目管理菜单：增删改查
 */
public class CategoryManager {
    private static final CategoryDao CATEGORY_DAO = new CategoryDaoImpl();
    private static final Scanner SC = ShopownerView.SC;

    /**
     * 类目管理总菜单
     */
    public static void categoryMenu() {
        while (true) {
            System.out.println("\n===== 类目管理 =====");
            System.out.println("1. 新增类目");
            System.out.println("2. 查询所有类目");
            System.out.println("3. 修改类目");
            System.out.println("4. 删除类目");
            System.out.println("5. 返回上一级");
            System.out.println("0. 退出系统");
            System.out.print("请选择功能：");
            String opt = SC.next();

            switch (opt) {
                case "1":
                    addCategory();
                    break;
                case "2":
                    queryAll();
                    break;
                case "3":
                    updateCategory();
                    break;
                case "4":
                    deleteCategory();
                    break;
                case "5":
                    // 返回店主主菜单
                    return;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("选择无效！");
            }
        }
    }

    // 1. 新增类目
    private static void addCategory() {
        System.out.println("\n----- 新增类目 -----");
        System.out.print("请输入类目名称：");
        String name = SC.next();
        Category category = new Category();
        category.setCategoryName(name);
        int rows = CATEGORY_DAO.addCategory(category);
        if (rows > 0) {
            System.out.println("类目新增成功！");
        } else {
            System.out.println("类目新增失败！");
        }
    }

    // 2. 查询所有类目 + 模糊查询
    private static void queryAll() {
        System.out.println("\n----- 所有类目 -----");
        List<Category> list = CATEGORY_DAO.findAll();
        list.forEach(System.out::println);

        System.out.print("是否开启模糊查询？(1是/0否)：");
        int flag = SC.nextInt();
        if (flag == 1) {
            System.out.print("请输入查询关键字：");
            String key = SC.next();
            List<Category> keyList = CATEGORY_DAO.findByKey(key);
            System.out.println("查询结果：");
            keyList.forEach(System.out::println);
        }
    }

    // 3. 修改类目
    private static void updateCategory() {
        System.out.println("\n----- 修改类目 -----");
        List<Category> list = CATEGORY_DAO.findAll();
        list.forEach(System.out::println);
        System.out.print("请选择要修改的类目编号：");
        Integer id = SC.nextInt();
        Category category = CATEGORY_DAO.findById(id);
        if (category == null) {
            System.out.println("该类目不存在！");
            return;
        }
        System.out.print("请输入新的类目名称：");
        String newName = SC.next();
        category.setCategoryName(newName);
        int rows = CATEGORY_DAO.updateCategory(category);
        if (rows > 0) {
            System.out.println("修改成功！");
        } else {
            System.out.println("修改失败！");
        }
    }

    // 4. 逻辑删除类目
    private static void deleteCategory() {
        System.out.println("\n----- 删除类目 -----");
        List<Category> list = CATEGORY_DAO.findAll();
        list.forEach(System.out::println);
        System.out.print("请选择要删除的类目编号：");
        Integer id = SC.nextInt();
        Category category = CATEGORY_DAO.findById(id);
        if (category == null) {
            System.out.println("类目不存在！");
            return;
        }
        System.out.print("确认删除？(1是/0否)：");
        int flag = SC.nextInt();
        if (flag == 1) {
            int rows = CATEGORY_DAO.deleteCategory(id);
            if (rows > 0) {
                System.out.println("删除成功！");
            } else {
                System.out.println("删除失败！");
            }
        }
    }
}