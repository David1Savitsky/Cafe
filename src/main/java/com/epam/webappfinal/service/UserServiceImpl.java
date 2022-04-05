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

    private static final Integer CARD_NUMBER_LENGTH = 16;
    private static final Integer MIN_LOYALTY_POINTS_NUMBER = 0;
    private static final Integer MAX_LOYALTY_POINTS_NUMBER = 100;
    private static final int FINED_LOYALTY_POINTS = 10;

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
            if (card.length() != CARD_NUMBER_LENGTH) {
                return false;
            }
            cardIntFirstPart = Integer.parseInt(card.substring(0, CARD_NUMBER_LENGTH / 2 - 1));
            cardIntSecondPart = Integer.parseInt(card.substring(CARD_NUMBER_LENGTH / 2), CARD_NUMBER_LENGTH - 1);
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
        if (loyaltyPoints < MIN_LOYALTY_POINTS_NUMBER || loyaltyPoints > MAX_LOYALTY_POINTS_NUMBER) {
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

    @Override
    public void fineUser(Long userId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            User user = userDao.getById(userId).get();
            int loyaltyPoints = user.getLoyaltyPoints();
            if ((loyaltyPoints - FINED_LOYALTY_POINTS) < 0) {
                user.setBlocked(true);
                user.setLoyaltyPoints(MIN_LOYALTY_POINTS_NUMBER);
            } else {
                user.setLoyaltyPoints(loyaltyPoints - FINED_LOYALTY_POINTS);
            }
            userDao.save(user);

            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void rewardUser(Long id, int loyaltyPoints) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            User user = userDao.getById(id).get();
            if (user.getLoyaltyPoints() + loyaltyPoints > MAX_LOYALTY_POINTS_NUMBER) {
                user.setLoyaltyPoints(MAX_LOYALTY_POINTS_NUMBER);
            } else {
                user.setLoyaltyPoints(user.getLoyaltyPoints() + loyaltyPoints);
            }
            userDao.save(user);
            helper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}