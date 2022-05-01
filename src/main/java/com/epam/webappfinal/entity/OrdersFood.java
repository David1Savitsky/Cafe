package com.epam.webappfinal.entity;

import java.io.Serializable;

public class OrdersFood implements Identifiable, Serializable {

    public static final String TABLE_NAME = "orders_food";

    public static final String ID = "id";
    public static final String ORDER_ID = "order_id";
    public static final String FOOD_ID = "food_id";
    public static final String COUNT = "count";

    private final Long id;
    private final Long orderId;
    private final Long foodId;
    private final Integer count;

    public OrdersFood(Long id, Long orderId, Long foodId, Integer count) {
        this.id = id;
        this.orderId = orderId;
        this.foodId = foodId;
        this.count = count;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "OrdersFood{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", foodId=" + foodId +
                ", count=" + count +
                '}';
    }
}
