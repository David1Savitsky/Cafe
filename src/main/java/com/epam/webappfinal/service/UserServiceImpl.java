package com.epam.webappfinal.service;

import com.epam.webappfinal.dao.Dao;
import com.epam.webappfinal.dao.DaoHelper;
import com.epam.webappfinal.dao.DaoHelperFactory;
import com.epam.webappfinal.dao.UserDao;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Integer cardNumberLength = 16;
    private static final Integer minLoyaltyPointNumber = 0;
    private static final Integer maxLoyaltyPointNumber = 100;

    private DaoHelperFactory daoHelperFactory;

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        Optional<User> user;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            user = dao.findUserByLoginAndPassword(login, password);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean isDeposited(User user, String card, String money) throws ServiceException {
        Integer moneyInt;
        Integer cardIntFirstPart;
        Integer cardIntSecondPart;

        try {
            moneyInt = Integer.parseInt(money);
            if (card.length() != cardNumberLength) {
                return false;
            }
            cardIntFirstPart = Integer.parseInt(card.substring(0, cardNumberLength / 2 - 1));
            cardIntSecondPart = Integer.parseInt(card.substring(cardNumberLength / 2), cardNumberLength - 1);
        } catch (NumberFormatException e) {
            return false;
        }
        if (moneyInt <= 0 || cardIntFirstPart <= 0 || cardIntSecondPart <= 0) {
            return false;
        }

        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            user.setAmount((BigDecimal.valueOf(moneyInt).add(user.getAmount())));
            UserDao dao = helper.createUserDao();
            dao.save(user);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public List<User> getUsers() throws ServiceException {
        List<User> userList;
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            userList = userDao.getUsers();
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userList;
    }

    @Override
    public void changeBlock(Long id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            User user = userDao.getById(id).get();
            user.setBlocked(!user.isBlocked());
            userDao.save(user);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeLoyaltyPoints(Long id, Integer loyaltyPoints) throws ServiceException {
        if (loyaltyPoints < minLoyaltyPointNumber || loyaltyPoints > maxLoyaltyPointNumber) {
            return;
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            userDao.changeLoyaltyPoints(id, loyaltyPoints);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}