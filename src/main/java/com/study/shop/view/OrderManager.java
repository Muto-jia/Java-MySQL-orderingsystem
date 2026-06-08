package com.study.shop.view;

import com.study.shop.dao.OrderDao;
import com.study.shop.dao.OrderDaoImpl;
import com.study.shop.po.Order;
import java.util.List;
import java.util.Scanner;

public class OrderManager {
    private static final OrderDao orderDao = new OrderDaoImpl();
    private static final Scanner sc = new Scanner(System.in);

    public static void orderMenu() {
        while (true) {
            System.out.println("\n===== 店主订单管理 =====");
            // 先查询所有待接单
            List<Order> list = orderDao.findNoHandleOrder();
            if (list.isEmpty()) {
                System.out.println("当前暂无待处理订单！");
            } else {
                for (Order order : list) {
                    System.out.println(order);
                }
            }
            System.out.println("1. 全部接单  2. 部分接单  3. 取消订单  4. 返回主菜单");
            System.out.print("请选择操作：");
            String op = sc.next();
            switch (op) {
                case "1":
                    int allRows = orderDao.receiveAllOrder();
                    System.out.println("成功接单" + allRows + "条订单");
                    break;
                case "2":
                    System.out.print("请输入多个订单编号(逗号分隔，例：1,2)：");
                    String ids = sc.next();
                    int partRows = orderDao.receivePartOrder(ids);
                    System.out.println("成功接单" + "条订单");
                    break;
                case "3":
                    System.out.print("请输入要取消的订单编号：");
                    int oid = sc.nextInt();
                    int rows = orderDao.cancelOrder(oid);
                    System.out.println(rows > 0 ? "取消订单成功" : "取消失败");
                    break;
                case "4":
                    return;
                default:
                    System.out.println("输入无效，请重新选择！");
            }
        }
    }
}