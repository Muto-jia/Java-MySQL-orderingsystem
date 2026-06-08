package com.study.shop.view;

import com.study.shop.dao.ShopownerDao;
import com.study.shop.dao.ShopownerDaoImpl;
import com.study.shop.po.Shopowner;
import java.util.Scanner;

/**
 * 店主登录 & 主菜单视图
 */
public class ShopownerView {
    // 全局扫描器
    public static final Scanner SC = new Scanner(System.in);
    // 店主DAO对象
    private static final ShopownerDao OWNER_DAO = new ShopownerDaoImpl();

    public static void main(String[] args) {
        // 第一步：登录
        if (login()) {
            // 登录成功，进入店主主菜单
            mainMenu();
        } else {
            System.out.println("登录失败，程序退出！");
        }
    }

    /**
     * 店主登录逻辑
     */
    private static boolean login() {
        System.out.println("===== 店主登录 =====");
        while (true) {
            System.out.print("请输入手机号：");
            String phone = SC.next();
            System.out.print("请输入密码：");
            String pwd = SC.next();

            // 调用DAO查询
            Shopowner owner = OWNER_DAO.login(phone, pwd);
            if (owner != null) {
                System.out.println("登录成功！欢迎您：" + owner.getShopowner());
                return true;
            } else {
                System.out.println("手机号或密码错误，请重新输入！");
            }
        }
    }

    /**
     * 店主主菜单
     */
    private static void mainMenu() {
        while (true) {
            System.out.println("\n===== 店主管理主菜单 =====");
            System.out.println("1. 类目管理");
            System.out.println("2. 餐品管理");
            System.out.println("3. 订单管理");
            System.out.println("0. 退出系统");
            System.out.print("请选择功能：");
            String opt = SC.next();

            switch (opt) {
                case "1":
                    // 进入类目管理界面
                    CategoryManager.categoryMenu();
                    break;
                case "2":
                    FoodManager.foodMenu();
                    break;
                case "3":
                    OrderManager.orderMenu();
                    break;
                case "0":
                    System.out.println("已退出系统！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入有误，请重新选择！");
            }
        }
    }
}