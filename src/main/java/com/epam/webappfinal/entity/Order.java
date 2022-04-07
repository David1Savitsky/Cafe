package com.epam.webappfinal.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Identifiable, Serializable {

    public static final String TABLE_NAME = "orders";

    public static final String ID = "id";
    public static final String VISITING_TIME = "visiting_time";
    public static final String USER_ID = "user_id";
    public static final String PAYMENT_TYPE = "payment_type";
    public static final String ORDER_STATUS = "status";

    private final Long id;
    private final LocalDateTime visitingTime;
    private final Long userId;
    private final PaymentType paymentType;
    private final OrderStatus orderStatus;

    public Order(Long id, LocalDateTime visitingTime, Long userId, PaymentType paymentType, OrderStatus orderStatus) {
        this.id = id;
        this.visitingTime = visitingTime;
        this.userId = userId;
        this.paymentType = paymentType;
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getVisitingTime() {
        return visitingTime;
    }

    public String getDate() {
        String items[] = visitingTime.toString().split("T");
        return items[0];
    }

    public String getTime() {
        String items[] = visitingTime.toString().split("T");
        return items[1];
    }

    public Long getUserId() {
        return userId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    @Override
    public Long getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", visitingTime=" + visitingTime +
                ", userId=" + userId +
                ", paymentType=" + paymentType +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
