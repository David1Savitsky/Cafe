package com.epam.webappfinal.mapper;

import com.epam.webappfinal.entity.Rating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingRowMapper implements RowMapper<Rating> {
    @Override
    public Rating map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Rating.ID);
        Integer rating = resultSet.getInt(Rating.RATING);
        Long userId = resultSet.getLong(Rating.USER_ID);
        Long foodId = resultSet.getLong(Rating.FOOD_ID);

        return new Rating(id, rating, userId, foodId);
    }
}
