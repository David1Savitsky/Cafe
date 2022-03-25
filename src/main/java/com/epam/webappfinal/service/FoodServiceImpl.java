package com.epam.webappfinal.service;

import com.epam.webappfinal.dao.DaoHelper;
import com.epam.webappfinal.dao.DaoHelperFactory;
import com.epam.webappfinal.dao.FoodDao;
import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class FoodServiceImpl implements FoodService {

    private final DaoHelperFactory daoHelperFactory;

    public FoodServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<Food> getFood(FoodType foodType) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            List<Food> foodList = new ArrayList<>();
            List<Food> listFromDao = new ArrayList<>();
            FoodDao foodDao = helper.createFoodDao();
            switch (foodType) {
                case ALL:
                    listFromDao = foodDao.getAllNotDisabled();
                    break;
                case DRINK:
                    listFromDao = foodDao.getAllNotDisabledDrinks();
                    break;
                case MEAL:
                    listFromDao = foodDao.getAllNotDisabledMeal();
            }
            for (Food food : listFromDao) {
                foodList.add(food);
            }
            helper.endTransaction();
            return foodList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
