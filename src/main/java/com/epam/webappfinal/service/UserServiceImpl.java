package com.epam.webappfinal.service;

import com.epam.webappfinal.dao.DaoHelper;
import com.epam.webappfinal.dao.DaoHelperFactory;
import com.epam.webappfinal.dao.UserDao;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;

import java.math.BigDecimal;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Integer cardNumberLength = 3;

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
        Integer cardInt;
        try {
            moneyInt = Integer.parseInt(money);
            if (card.length() != cardNumberLength) {
                return false;
            }
            cardInt = Integer.parseInt(card);
        } catch (Exception e) {
            return false;
        }
        if (moneyInt <= 0 || cardInt <= 0) {
            return false;
        }
        user.setAmount((BigDecimal.valueOf(moneyInt).add(user.getAmount())));

        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao dao = helper.createUserDao();
            dao.save(user);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

}