package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.exception.DaoException;

import java.util.List;

public interface FoodDao extends Dao<Food>{

    List<Food> getAllNotDisabled() throws DaoException;

    List<Food> getAllNotDisabled(Long typeId) throws DaoException;

    void makeDisabledFoodById(Long foodId) throws DaoException;

    void updateRating(Long orderId, Long userId, int rating) throws DaoException;
}
