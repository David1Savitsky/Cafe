package com.epam.webappfinal.entity;

public class User implements Identifiable {

    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String NAME = "name";

    private Long id;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }



    @Override
    public Long getId() {
        return id;
    }
}
