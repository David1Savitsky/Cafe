package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.exception.DaoException;

import java.util.List;

/**
 * This interface declares the methods that will interact with database.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface FoodDao extends Dao<Food>{

    /**
     * This method gets the list of all not disabled food.
     *
     * @return {@code List<Food>} list of food.
     * @throws DaoException if any dao exception occurred during processing.
     */
    List<Food> getAllNotDisabled() throws DaoException;

    /**
     * This method gets the list of not disabled food by food's type.
     *
     * @param typeId food type's id.
     * @return {@code List<Food>} list of food.
     * @throws DaoException if any dao exception occurred during processing.
     */
    List<Food> getAllNotDisabled(Long typeId) throws DaoException;

    /**
     * This method makes the food item disabled.
     *
     * @param foodId food's id.
     * @throws DaoException if any dao exception occurred during processing.
     */
    void makeDisabledFoodById(Long foodId) throws DaoException;

    /**
     * This method updates the food's rating made by user.
     *
     * @param orderId order's id.
     * @param userId  user's id.
     * @param rating  new rating.
     * @throws DaoException if any dao exception occurred during processing.
     */
    void updateRating(Long orderId, Long userId, int rating) throws DaoException;
}
