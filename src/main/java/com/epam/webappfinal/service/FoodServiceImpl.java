package com.epam.webappfinal.service;

import com.epam.webappfinal.dao.DaoHelper;
import com.epam.webappfinal.dao.DaoHelperFactory;
import com.epam.webappfinal.dao.FoodDao;
import com.epam.webappfinal.dao.FoodTypeDao;
import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;

import java.util.List;

public class FoodServiceImpl implements FoodService {

    private final DaoHelperFactory daoHelperFactory;

    public FoodServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public List<Food> getFood() throws ServiceException {
        List<Food> listFromDao;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            FoodDao foodDao = helper.createFoodDao();
            listFromDao = foodDao.getAllNotDisabled();
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return listFromDao;
    }

    @Override
    public List<Food> getFood(Long typeId) throws ServiceException {
        List<Food> listFromDao;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            FoodDao foodDao = helper.createFoodDao();
            listFromDao = foodDao.getAllNotDisabled(typeId);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return listFromDao;
    }

    @Override
    public List<FoodType> getTypeList() throws ServiceException {
        List<FoodType> foodTypeList;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            FoodTypeDao foodTypeDao = helper.createFoodTypeDao();
            foodTypeList = foodTypeDao.getTypes();
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return foodTypeList;
    }

    @Override
    public void deleteFood(Long foodId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            FoodDao foodDao = helper.createFoodDao();
            foodDao.makeDisabledFoodById(foodId);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
