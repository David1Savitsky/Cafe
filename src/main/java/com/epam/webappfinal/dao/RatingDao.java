package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Rating;
import com.epam.webappfinal.exception.DaoException;

/**
 * This interface declares the methods that will interact with database.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface RatingDao extends Dao<Rating> {

    /**
     * This method gets food's rating of the user.
     *
     * @param foodId food's id.
     * @param userId  user's id.
     * @return {@code Rating} food's rating.
     * @throws DaoException if any dao exception occurred during processing.
     */
    Rating getRatingByFoodId(Long foodId, Long userId) throws DaoException;
}
