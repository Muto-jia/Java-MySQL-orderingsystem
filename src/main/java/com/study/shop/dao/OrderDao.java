package com.study.shop.dao;

import com.study.shop.po.Order;
import java.util.List;

public interface OrderDao {
    // 新增订单（顾客点餐）
    int addOrder(Order order);

    // 查询所有待接单订单（店主视图）
    List<Order> findNoHandleOrder();

    // 全部接单（状态改为2）
    int receiveAllOrder();

    // 部分接单（根据订单编号）
    int receivePartOrder(String orderIds);

    // 取消订单（状态改为3）
    int cancelOrder(Integer orderId);

    // 根据手机号+类型查询订单（顾客查询）
    List<Order> findOrderByPhone(String phone, int type);
}