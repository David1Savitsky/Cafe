package com.epam.webappfinal.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Identifiable, Serializable {

    public static final String TABLE_NAME = "users";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String IS_ADMIN = "is_admin";
    public static final String AMOUNT = "amount";
    public static final String LOYALTY_POINTS = "loyalty_points";
    public static final String IS_BLOCKED = "is_blocked";

    private final Long id;
    private final String name;
    private final String surname;
    private final String login;
    private final boolean isAdmin;
    private BigDecimal amount;
    private Integer loyaltyPoints;
    private boolean isBlocked;

    public User(Long id, String name, String surname, String login,
                boolean isAdmin, BigDecimal amount, Integer loyaltyPoints, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.isAdmin = isAdmin;
        this.amount = amount;
        this.loyaltyPoints = loyaltyPoints;
        this.isBlocked = isBlocked;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", isAdmin=" + isAdmin +
                ", amount=" + amount +
                ", loyaltyPoints=" + loyaltyPoints +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
