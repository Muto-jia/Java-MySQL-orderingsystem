package com.study.shop.dao;

import com.study.shop.po.Shopowner;

public interface ShopownerDao {
    Shopowner login(String phone, String pwd);
}