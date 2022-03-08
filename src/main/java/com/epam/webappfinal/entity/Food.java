package com.epam.webappfinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Food implements Identifiable, Serializable {

    public static final String TABLE_NAME = "food";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String PRICE = "price";
    public static final String IS_DISABLED = "is_disabled";

    private final Long id;
    private final String name;
    private final FoodType type;
    private final BigDecimal price;
    private final Boolean isDisabled;

    public Food(Long id, String name, FoodType type, BigDecimal price, Boolean isDisabled) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.isDisabled = isDisabled;
    }

    public String getName() {
        return name;
    }

    public FoodType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getDisabled() {
        return isDisabled;
    }

    @Override
    public Long getId() {
        return id;
    }
}
