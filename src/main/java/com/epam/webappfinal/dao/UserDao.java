package com.epam.webappfinal.dao;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.DaoException;
import com.epam.webappfinal.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * This interface declares the methods that will interact with database.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface UserDao extends Dao<User> {

    /**
     * Purpose of this method is to get specific user from database, knowing login
     * and password.
     *
     * @param login    user's login.
     * @param password user's password.
     * @return {@code Optional<User>} user which was found by login and password.
     * @throws DaoException if any dao exception occurred during processing.
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * The purpose of this method is to create user.
     *
     * @param name              user's name.
     * @param surname           user's surname.
     * @param login             user's login.
     * @param password          user's password.
     * @throws DaoException if any dao exception occurred during processing.
     * */
    void register(String name, String surname, String login, String password) throws DaoException;

    /**
     * Purpose of this method is to get specific amount of users from database.
     *
     * @return {@code List<User>} list of all found users.
     * @throws DaoException if any dao exception occurred during processing.
     */
    List<User> getUsers() throws DaoException;

    /**
     * This method let admin change user's loyalty points.
     *
     * @param id            user's id.
     * @param loyaltyPoints loyalty point value to be set.
     * @throws DaoException if any dao exception occurred during processing.
     * */
    void changeLoyaltyPoints(Long id, Integer loyaltyPoints) throws DaoException;
}
