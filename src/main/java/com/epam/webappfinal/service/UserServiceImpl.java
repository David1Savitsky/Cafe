package com.epam.webappfinal.service;

import com.epam.webappfinal.dao.DaoHelper;
import com.epam.webappfinal.dao.DaoHelperFactory;
import com.epam.webappfinal.dao.UserDao;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private DaoHelperFactory daoHelperFactory;

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

//    public boolean login(String login, String password) {
//
//        //Добавить проверку на наличие такого пользователя в БД
//        return "admin".equals(login) && "admin".equals(password);
//    }

    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> user;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            user = dao.findUserByLoginAndPassword(login, password);
            helper.endTransaction();
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}