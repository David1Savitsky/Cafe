package com.epam.webappfinal.service;

import com.epam.webappfinal.entity.*;
import com.epam.webappfinal.exception.ServiceException;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.List;

public interface FoodService {

    List<Food> getFood() throws ServiceException;

    List<Food> getFood(Long typeId) throws ServiceException;

    List<FoodType> getTypeList() throws ServiceException;

    void deleteFood(Long foodId) throws ServiceException;

    void saveFood(Long id, String name, Long typeId, BigDecimal price) throws ServiceException;

    void addFood(String name, String type, BigDecimal price) throws ServiceException;

    Rating getRating(Long foodId, Long userId) throws ServiceException;

    void changeRating(Long foodId, Long userId, int rating) throws ServiceException;

    Food getFoodById(Long id) throws ServiceException;

    void addComment(Long foodId, Long userId, String comment) throws ServiceException;

    List<Pair<User, Comment>> getComments(Long foodId) throws ServiceException;

    void deleteComment(Long commentId) throws ServiceException;
}
