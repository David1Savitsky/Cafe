package com.epam.webappfinal.service;

import com.epam.webappfinal.dao.*;
import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;

import java.math.BigDecimal;
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

    @Override
    public void saveFood(Long id, String name, Long typeId, BigDecimal price) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            FoodDao foodDao = helper.createFoodDao();
            foodDao.save(new Food(id, name, typeId, price, false));
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addFood(String name, String type, BigDecimal price) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            FoodTypeDao foodTypeDao = helper.createFoodTypeDao();
            Long typeId = foodTypeDao.getTypeId(type);
            if (typeId == null) {
                typeId = foodTypeDao.createTypeId(type);
            }
            FoodDao foodDao = helper.createFoodDao();
            foodDao.save(new Food(null, name, typeId, price, false));
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
