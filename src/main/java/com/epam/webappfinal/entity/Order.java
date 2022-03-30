package com.epam.webappfinal.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Order implements Identifiable, Serializable {

    public static final String TABLE_NAME = "orders";

    public static final String ID = "id";
    public static final String VISITING_TIME = "visiting_time";
    public static final String USER_ID = "user_id";
    public static final String PAYMENT_TYPE = "payment_type";
    public static final String RATING = "rating";
    public static final String COMMENT = "comment";
    public static final String IS_TAKEN = "is_taken";
    public static final String IS_ORDERED = "is_ordered";

    private final Long id;
    private final LocalDateTime visitingTime;
    private final Long userId;
    private final PaymentType paymentType;
    private final Integer rating;
    private final String comment;
    private final boolean isTaken;
    private final boolean isOrdered;

    public Order(Long id, LocalDateTime visitingTime, Long userId, PaymentType paymentType, Integer rating, String comment, boolean isTaken, boolean isOrdered) {
        this.id = id;
        this.visitingTime = visitingTime;
        this.userId = userId;
        this.paymentType = paymentType;
        this.rating = rating;
        this.comment = comment;
        this.isTaken = isTaken;
        this.isOrdered = isOrdered;
    }

    public LocalDateTime getVisitingTime() {
        return visitingTime;
    }

    public Long getUserId() {
        return userId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    @Override
    public Long getId() {
        return id;
    }
}
