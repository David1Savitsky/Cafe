package com.epam.webappfinal.service;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.exception.ServiceException;

import java.util.List;

public interface FoodService {

    List<Food> getFood() throws ServiceException;

    List<Food> getFood(Long typeId) throws ServiceException;

    List<FoodType> getTypeList() throws ServiceException;

    void deleteFood(Long foodId) throws ServiceException;

}
