package com.epam.webappfinal.service;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.exception.ServiceException;

import java.util.List;

public interface FoodService {

    List<Food> getFood(FoodType foodType) throws ServiceException;

}
