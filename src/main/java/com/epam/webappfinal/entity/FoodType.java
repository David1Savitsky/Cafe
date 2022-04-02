package com.epam.webappfinal.entity;

import java.io.Serializable;

public class FoodType implements Identifiable, Serializable {

    public static final String TABLE_NAME = "types";

    public static final String ID = "id";
    public static final String NAME = "name";

    private final Long id;
    private final String name;

    public FoodType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
