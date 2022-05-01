package com.epam.webappfinal.entity;

import java.io.Serializable;

public class Comment implements Identifiable, Serializable {

    public static final String TABLE_NAME = "comments";

    public static final String ID = "id";
    public static final String COMMENT = "comment";
    public static final String USER_ID = "user_id";
    public static final String FOOD_ID = "food_id";

    private Long id;
    private final String comment;
    private final Long userId;
    private final Long foodId;

    public Comment(Long id, String comment, Long userId, Long foodId) {
        this.id = id;
        this.comment = comment;
        this.userId = userId;
        this.foodId = foodId;
    }

    public String getComment() {
        return comment;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getFoodId() {
        return foodId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", userId=" + userId +
                ", foodId=" + foodId +
                '}';
    }
}
