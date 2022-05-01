package com.epam.webappfinal.service;

import com.epam.webappfinal.dao.*;
import com.epam.webappfinal.entity.*;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class FoodServiceImpl implements FoodService {

    private final Logger LOGGER = LogManager.getLogger();

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
            LOGGER.debug("List of food has got");
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
            LOGGER.debug("List of food has got using typeId");
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
            LOGGER.debug("List of food type has got");
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
            LOGGER.debug("Food item is deleted by id");
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
            LOGGER.debug("Food item is saved by id");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addFood(String name, String type, BigDecimal price) throws ServiceException {
        if (price.compareTo(new BigDecimal(0)) < 0) {
            return;
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            FoodTypeDao foodTypeDao = helper.createFoodTypeDao();
            Long typeId = foodTypeDao.getTypeId(type);
            if (typeId == null) {
                typeId = foodTypeDao.createTypeId(type);
            }
            FoodDao foodDao = helper.createFoodDao();
            foodDao.save(new Food(null, name, typeId, price, false));
            LOGGER.debug("Food item is added by id");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rating getRating(Long foodId, Long userId) throws ServiceException {
        Rating rating;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            RatingDao ratingDao = helper.createRatingDao();
            rating = ratingDao.getRatingByFoodId(foodId, userId);
            LOGGER.debug("Food rating has got by id");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return rating;
    }

    @Override
    public void changeRating(Long foodId, Long userId, int rating) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            FoodDao foodDao = helper.createFoodDao();
            foodDao.updateRating(foodId, userId, rating);
            LOGGER.debug("Food rating is changed by id");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Food getFoodById(Long id) throws ServiceException {
        Food food = null;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            FoodDao foodDao = helper.createFoodDao();
            Optional<Food> foodEnt = foodDao.getById(id);
            if (foodEnt.isPresent()) {
                food = foodEnt.get();
            }
            LOGGER.debug("Food item has got by id");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return food;
    }

    @Override
    public void addComment(Long foodId, Long userId, String comment) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CommentDao commentDao = helper.createCommentDao();
            commentDao.insertComment(foodId, userId, comment);
            LOGGER.debug("Food comment is added by id");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Pair<User, Comment>> getComments(Long foodId) throws ServiceException {
        List<Pair<User, Comment>> commentList;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CommentDao commentDao = helper.createCommentDao();
            commentList = commentDao.getComments(foodId);
            LOGGER.debug("List of food has got by food id");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return commentList;
    }

    @Override
    public void deleteComment(Long commentId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            CommentDao commentDao = helper.createCommentDao();
            commentDao.removeById(commentId);
            LOGGER.debug("Food comment is deleted by comment id");
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
