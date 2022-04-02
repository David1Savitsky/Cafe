package com.epam.webappfinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Food implements Identifiable, Serializable {

    public static final String TABLE_NAME = "food";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TYPE_ID = "type_id";
    public static final String PRICE = "price";
    public static final String IS_DISABLED = "is_disabled";

    private final Long id;
    private final String name;
    private final Integer typeId;
    private final BigDecimal price;
    private final Boolean isDisabled;

    public Food(Long id, String name, Integer typeId, BigDecimal price, Boolean isDisabled) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.price = price;
        this.isDisabled = isDisabled;
    }

    public String getName() {
        return name;
    }

    public Integer getTypeId() {
        return typeId;
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

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + typeId +
                ", price=" + price +
                ", isDisabled=" + isDisabled +
                '}';
    }
}
