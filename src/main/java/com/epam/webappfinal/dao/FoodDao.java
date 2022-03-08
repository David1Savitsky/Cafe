package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.exception.DaoException;

import java.util.List;

public interface FoodDao extends Dao<Food>{

    List<Food> getAllNotDisabled() throws DaoException;

    List<Food> getAllNotDisabledDrinks() throws DaoException;

    List<Food> getAllNotDisabledMeal() throws DaoException;

}
