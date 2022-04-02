package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.exception.DaoException;

import java.util.List;

public interface FoodTypeDao extends Dao<FoodType> {

    List<FoodType> getTypes() throws DaoException;

    Long getTypeId(String name) throws DaoException;

    Long createTypeId(String name) throws DaoException;
}
