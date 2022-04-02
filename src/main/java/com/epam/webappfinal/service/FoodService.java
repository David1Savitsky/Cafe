package com.epam.webappfinal.service;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface FoodService {

    List<Food> getFood() throws ServiceException;

    List<Food> getFood(Long typeId) throws ServiceException;

    List<FoodType> getTypeList() throws ServiceException;

    void deleteFood(Long foodId) throws ServiceException;

    void saveFood(Long id, String name, Long typeId, BigDecimal price) throws ServiceException;

    void addFood(String name, String type, BigDecimal price) throws ServiceException;
}
