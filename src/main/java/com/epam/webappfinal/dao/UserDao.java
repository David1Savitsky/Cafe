package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;

import java.util.Optional;

public interface UserDao {

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

}