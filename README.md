# Java-MySQL-orderingshop
基于 Java + JDBC + MySQL 的简易点餐系统。

## 项目简介
这是一个控制台版点餐系统，实现**菜品浏览、下单、订单管理、数据持久化**等基础功能，适合 Java 初学者练习 JDBC 与数据库交互。

## 技术栈
- Java 8+
- JDBC
- MySQL 5.7 / 8.0
- Maven
- IDEA

## 功能特性
- ✅ 菜品展示
- ✅ 用户下单
- ✅ 订单查询
- ✅ 数据保存到 MySQL
- ✅ 简单的控制台交互

## 环境准备
1. 安装 MySQL，创建数据库（建议 `ordershop`）
2. 导入项目 SQL 脚本（如果有）
3. 修改数据库连接配置：
```java
String url = "jdbc:mysql://localhost:3306/ordershop?useSSL=false&serverTimezone=UTC";
String user = "root";
String password = "你的密码";
