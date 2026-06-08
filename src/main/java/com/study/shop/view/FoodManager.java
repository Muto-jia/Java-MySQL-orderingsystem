package com.study.shop.view;

import com.study.shop.dao.FoodDao;
import com.study.shop.dao.FoodDaoImpl;
import com.study.shop.po.Food;
import java.util.List;
import java.util.Scanner;

public class FoodManager {
    private static final FoodDao foodDao = new FoodDaoImpl();
    private static final Scanner sc = new Scanner(System.in);

    public static void foodMenu() {
        while (true) {
            System.out.println("\n===== 餐品管理 =====");
            System.out.println("1. 新增餐品");
            System.out.println("2. 查询所有餐品");
            System.out.println("3. 修改餐品");
            System.out.println("4. 删除餐品");
            System.out.println("5. 按分类查询餐品");
            System.out.println("6. 返回主菜单");
            System.out.print("请选择：");
            String op = sc.next();

            switch (op) {
                case "1": addFood(); break;
                case "2": findAll(); break;
                case "3": updateFood(); break;
                case "4": deleteFood(); break;
                case "5": findByCategory(); break;
                case "6": return;
                default: System.out.println("输入错误！");
            }
        }
    }

    private static void addFood() {
        System.out.print("餐品名称：");
        String name = sc.next();
        System.out.print("分类ID：");
        int cid = sc.nextInt();
        System.out.print("价格：");
        int price = sc.nextInt();
        System.out.print("简介：");
        String desc = sc.next();

        Food food = new Food();
        food.setFoodName(name);
        food.setCategoryId(cid);
        food.setPrice(price);
        food.setDescription(desc);

        int rows = foodDao.addFood(food);
        System.out.println(rows > 0 ? "✅ 添加成功" : "❌ 添加失败");
    }

    private static void findAll() {
        System.out.println("\n----- 餐品列表 -----");
        List<Food> list = foodDao.findAll();
        if (list.isEmpty()) {
            System.out.println("暂无餐品");
            return;
        }
        for (Food f : list) {
            System.out.println(f);
        }
    }

    private static void updateFood() {
        findAll();
        System.out.print("请输入要修改的餐品ID：");
        int id = sc.nextInt();
        Food food = foodDao.findById(id);
        if (food == null) {
            System.out.println("餐品不存在");
            return;
        }

        System.out.print("新名称：");
        food.setFoodName(sc.next());
        System.out.print("新分类ID：");
        food.setCategoryId(sc.nextInt());
        System.out.print("新价格：");
        food.setPrice(sc.nextInt());
        System.out.print("新简介：");
        food.setDescription(sc.next());

        int rows = foodDao.updateFood(food);
        System.out.println(rows > 0 ? "✅ 修改成功" : "❌ 修改失败");
    }

    private static void deleteFood() {
        findAll();
        System.out.print("请输入要删除的餐品ID：");
        int id = sc.nextInt();
        int rows = foodDao.deleteFood(id);
        System.out.println(rows > 0 ? "✅ 删除成功" : "❌ 删除失败");
    }

    private static void findByCategory() {
        System.out.print("请输入分类ID：");
        int cid = sc.nextInt();
        List<Food> list = foodDao.findByCategoryId(cid);
        System.out.println("\n----- 查询结果 -----");
        for (Food f : list) {
            System.out.println(f);
        }
    }
}