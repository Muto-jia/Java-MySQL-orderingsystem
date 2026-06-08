package com.study.shop.view;

import com.study.shop.dao.FoodDao;
import com.study.shop.dao.FoodDaoImpl;
import com.study.shop.dao.OrderDao;
import com.study.shop.dao.OrderDaoImpl;
import com.study.shop.po.Food;
import com.study.shop.po.Order;
import java.util.List;
import java.util.Scanner;

public class CustomerManager {
    private static final FoodDao foodDao = new FoodDaoImpl();
    private static final OrderDao orderDao = new OrderDaoImpl();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== 顾客点餐系统 =====");
        customerMenu();
    }

    public static void customerMenu() {
        while (true) {
            System.out.println("\n1. 点餐  2. 查询点餐记录  0. 退出");
            System.out.print("请选择：");
            String op = sc.next();
            switch (op) {
                case "1":
                    orderFood();
                    break;
                case "2":
                    findRecord();
                    break;
                case "0":
                    System.out.println("感谢使用，再见！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误！");
            }
        }
    }

    // 顾客点餐
    private static void orderFood() {
        // 展示所有餐品
        List<Food> foodList = foodDao.findAll();
        System.out.println("\n----- 可点餐品列表 -----");
        for (Food f : foodList) {
            System.out.println(f);
        }

        System.out.print("请输入桌号：");
        int desk = sc.nextInt();
        System.out.print("请输入手机号：");
        String phone = sc.next();
        System.out.print("请输入餐品编号：");
        int fid = sc.nextInt();
        System.out.print("请输入购买数量：");
        int num = sc.nextInt();

        // 根据餐品ID查询价格
        Food food = foodDao.findById(fid);
        if (food == null) {
            System.out.println("该餐品不存在！");
            return;
        }
        int total = food.getPrice() * num;
        System.out.println("订单总价：" + total);
        System.out.print("确认下单？(1是/0否)：");
        int confirm = sc.nextInt();
        if (confirm == 0) {
            System.out.println("已取消下单");
            return;
        }

        // 封装订单对象并新增
        Order order = new Order(desk, phone, fid, food.getPrice(), num, total);
        int rows = orderDao.addOrder(order);
        System.out.println(rows > 0 ? "✅ 下单成功！等待店家接单" : "❌ 下单失败");
    }

    // 查询点餐记录
    private static void findRecord() {
        System.out.print("请输入你的手机号：");
        String phone = sc.next();
        System.out.println("1. 当天待接单  2. 当天已接单  3. 所有订单");
        System.out.print("请选择查询类型：");
        int type = sc.nextInt();

        List<Order> list = orderDao.findOrderByPhone(phone, type);
        if (list.isEmpty()) {
            System.out.println("暂无相关订单记录");
            return;
        }
        System.out.println("\n----- 订单记录 -----");
        for (Order o : list) {
            System.out.println(o);
        }
    }
}