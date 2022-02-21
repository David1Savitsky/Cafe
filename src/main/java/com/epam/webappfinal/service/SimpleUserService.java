package com.epam.webappfinal.service;

import com.epam.webappfinal.dao.UserDao;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;

import java.util.Optional;

public class SimpleUserService {

    private UserDao dao;

    public SimpleUserService(UserDao dao) {
        this.dao = dao;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            Optional<User> user = dao.findUserByLoginAndPassword(login, password);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
