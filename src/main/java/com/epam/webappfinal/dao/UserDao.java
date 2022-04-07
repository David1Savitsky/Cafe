package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    void register(String name, String surname, String login, String password) throws DaoException;

    List<User> getUsers() throws DaoException;

    void changeLoyaltyPoints(Long id, Integer loyaltyPoints) throws DaoException;
}
