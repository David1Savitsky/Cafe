package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.exception.DaoException;

import java.util.List;

/**
 * This interface declares the methods that will interact with database.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface FoodTypeDao extends Dao<FoodType> {

    /**
     * This method gets the list of food's types.
     *
     * @return {@code List<FoodType>} list of food's types.
     * @throws DaoException if any dao exception occurred during processing.
     */
    List<FoodType> getTypes() throws DaoException;

    /**
     * This method promotes getting food type's id by name.
     *
     * @param name food's type name.
     * @return {@code Long} food type's id.
     * @throws DaoException if any dao exception occurred during processing.
     */
    Long getTypeId(String name) throws DaoException;

    /**
     * This method promotes creating new food type by name.
     *
     * @param name food's type name.
     * @return {@code Long} food type's id.
     * @throws DaoException if any dao exception occurred during processing.
     */
    Long createTypeId(String name) throws DaoException;
}
