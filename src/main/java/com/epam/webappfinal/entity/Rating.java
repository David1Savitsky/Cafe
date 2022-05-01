package com.epam.webappfinal.entity;

import java.io.Serializable;

public class Rating implements Identifiable, Serializable {

    public static final String TABLE_NAME = "ratings";

    public static final String ID = "id";
    public static final String RATING = "rating";
    public static final String USER_ID = "user_id";
    public static final String FOOD_ID = "food_id";

    private final Long id;
    private final Integer rating;
    private final Long userId;
    private final Long foodId;

    public Rating(Long id, Integer rating, Long userId, Long foodId) {
        this.id = id;
        this.rating = rating;
        this.userId = userId;
        this.foodId = foodId;
    }

    public Integer getRating() {
        return rating;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getFoodId() {
        return foodId;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", rating=" + rating +
                ", userId=" + userId +
                ", foodId=" + foodId +
                '}';
    }
}
