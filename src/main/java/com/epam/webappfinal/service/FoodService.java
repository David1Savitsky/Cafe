package com.epam.webappfinal.service;

import com.epam.webappfinal.entity.*;
import com.epam.webappfinal.exception.ServiceException;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.List;

/**
 * This interface declares the methods that will interact with Command and Dao
 * layer. Methods interact with {@code Food}.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface FoodService {

    /**
     * This method get all the food.
     *
     * @return {@code List<Food>} list of food.
     * @throws ServiceException if any service exception occurred during processing.
     */
    List<Food> getFood() throws ServiceException;

    /**
     * This method get all the food by food's type.
     *
     * @param typeId food type's id.
     * @return {@code List<Food>} list of food.
     * @throws ServiceException if any service exception occurred during processing.
     */
    List<Food> getFood(Long typeId) throws ServiceException;

    /**
     * This method get all the food's types.
     *
     * @return {@code List<FoodType>} list of food's types.
     * @throws ServiceException if any service exception occurred during processing.
     */
    List<FoodType> getTypeList() throws ServiceException;

    /**
     * This method delete the food by food's id.
     *
     * @param foodId food's id.
     * @throws ServiceException if any service exception occurred during processing.
     */
    void deleteFood(Long foodId) throws ServiceException;

    /**
     * This method save the food item.
     *
     * @param id food's id.
     * @param name food's name.
     * @param typeId food's food type's id.
     * @param price food's price.
     * @throws ServiceException if any service exception occurred during processing.
     */
    void saveFood(Long id, String name, Long typeId, BigDecimal price) throws ServiceException;

    /**
     * This method add new food item.
     *
     * @param name food's name.
     * @param type food's food type's id.
     * @param price food's price.
     * @throws ServiceException if any service exception occurred during processing.
     */
    void addFood(String name, String type, BigDecimal price) throws ServiceException;

    /**
     * This method gets user's rating of the food item.
     *
     * @param foodId food's id.
     * @param userId user's name.
     * @return {@code Rating} food's rating.
     * @throws ServiceException if any service exception occurred during processing.
     */
    Rating getRating(Long foodId, Long userId) throws ServiceException;

    /**
     * This method promotes changing food's rating by user.
     *
     * @param foodId food's id.
     * @param userId user's name.
     * @param rating user's new rating.
     * @throws ServiceException if any service exception occurred during processing.
     */
    void changeRating(Long foodId, Long userId, int rating) throws ServiceException;

    /**
     * This method gets food item by id.
     *
     * @param id food's id.
     * @return {@code Food} food item.
     * @throws ServiceException if any service exception occurred during processing.
     */
    Food getFoodById(Long id) throws ServiceException;

    /**
     * This method promotes adding user's comment to food.
     *
     * @param foodId food's id.
     * @param userId user's name.
     * @param comment user's comment.
     * @throws ServiceException if any service exception occurred during processing.
     */
    void addComment(Long foodId, Long userId, String comment) throws ServiceException;

    /**
     * This method gets all food's comments.
     *
     * @param foodId food's id.
     * @return {@code List<Pair<User, Comment>>} list of user's comments.
     * @throws ServiceException if any service exception occurred during processing.
     */
    List<Pair<User, Comment>> getComments(Long foodId) throws ServiceException;

    /**
     * This method promotes deleting user's comment by admin.
     *
     * @param commentId comment's id.
     * @throws ServiceException if any service exception occurred during processing.
     */
    void deleteComment(Long commentId) throws ServiceException;
}
