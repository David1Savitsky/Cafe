package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.Rating;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.mapper.RatingRowMapper;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RatingDaoImpl extends AbstractDao<Rating> implements RatingDao {

    private static final String GET_RATING_BY_FOOD_AND_USER_ID = "select * from ratings where food_id = ? and user_id = ?";
    private static final String INSERT_RATING_BY_FOOD_AND_USER_ID = "insert into ratings set rating = 0, food_id = ?, user_id = ?; ";

    public RatingDaoImpl(Connection connection) {
        super(connection, new RatingRowMapper(), Rating.TABLE_NAME);
    }

    @Override
    protected Map<String, Object> getFields(Rating item) {
        Map<String, Object> fields = new LinkedHashMap<>();
        fields.put(Rating.ID, item.getId());
        fields.put(Rating.RATING, item.getRating());
        fields.put(Rating.USER_ID, item.getUserId());
        fields.put(Rating.FOOD_ID, item.getFoodId());
        return fields;
    }

    @Override
    public Rating getRatingByFoodId(Long foodId, Long userId) throws DaoException {
        List<Rating> ratingList = executeQuery(GET_RATING_BY_FOOD_AND_USER_ID, foodId, userId);
        Rating rating;
        if (ratingList.isEmpty()) {
            executeUpdate(INSERT_RATING_BY_FOOD_AND_USER_ID, foodId, userId);
            ratingList = executeQuery(GET_RATING_BY_FOOD_AND_USER_ID, foodId, userId);
            rating = ratingList.get(0);
        } else {
            rating = ratingList.get(0);
        }
        return rating;
    }
}
