package com.epam.webappfinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Identifiable, Serializable {

    public static final String TABLE = "user";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String IS_ADMIN = "is_admin";
    public static final String AMOUNT = "amount";
    public static final String LOYALTY_POINTS = "loyalty_points";
    public static final String IS_BLOCKED = "is_blocked";

    private Integer id;
    private String name;
    private String surname;
    private String login;
    //private String password;
    private boolean isAdmin;
    private BigDecimal amount;
    private Integer loyaltyPoints;
    private boolean isBlocked;

    public User(Integer id, String name, String surname, String login,
                boolean isAdmin, BigDecimal amount, Integer loyaltyPoints, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        //this.password = password;
        this.isAdmin = isAdmin;
        this.amount = amount;
        this.loyaltyPoints = loyaltyPoints;
        this.isBlocked = isBlocked;
    }

    public String getName() {
        return name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

//    public String getPassword() {
//        return password;
//    }


}
