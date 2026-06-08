package com.study.shop.dao;

import com.study.shop.po.Food;
import java.util.List;

public interface FoodDao {
    int addFood(Food food);
    int deleteFood(Integer foodId);
    int updateFood(Food food);
    Food findById(Integer foodId);
    List<Food> findAll();
    List<Food> findByCategoryId(Integer categoryId);
}