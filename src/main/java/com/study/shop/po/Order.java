package com.study.shop.po;

import java.util.Date;

/**
 * 订单实体 对应数据表 t_order
 */
public class Order {
    private Integer orderId;     // 订单编号
    private Integer deskNumber;  // 桌号
    private String phone;        // 顾客手机号
    private Integer foodId;      // 餐品编号
    private String foodName;     // 餐品名称
    private Integer price;       // 单价
    private Integer amount;      // 购买数量
    private Integer total;       // 总价
    private Date createTime;     // 下单时间
    private Integer state;       // 1待接单 2已接单 3已取消

    public Order() {}

    // 点餐专用构造方法
    public Order(Integer deskNumber, String phone, Integer foodId, Integer price, Integer amount, Integer total) {
        this.deskNumber = deskNumber;
        this.phone = phone;
        this.foodId = foodId;
        this.price = price;
        this.amount = amount;
        this.total = total;
    }

    // getter & setter
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getDeskNumber() {
        return deskNumber;
    }
    public void setDeskNumber(Integer deskNumber) {
        this.deskNumber = deskNumber;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Integer getFoodId() {
        return foodId;
    }
    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }
    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        String stateStr = state == 1 ? "待接单" : (state == 2 ? "已接单" : "已取消");
        return "订单编号：" + orderId +
                " | 桌号：" + deskNumber +
                " | 手机号：" + phone +
                " | 餐品：" + foodName +
                " | 单价：" + price +
                " | 数量：" + amount +
                " | 总价：" + total +
                " | 下单时间：" + createTime +
                " | 状态：" + stateStr;
    }
}