package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Rating;
import com.epam.webappfinal.exception.DaoException;

public interface RatingDao extends Dao<Rating> {

    Rating getRatingByFoodId(Long foodId, Long userId) throws DaoException;
}
