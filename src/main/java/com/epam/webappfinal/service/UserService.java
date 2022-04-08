package com.epam.webappfinal.service;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * This interface declares the methods that will interact with Command and Dao
 * layer. Methods interact with {@code User}.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface UserService {

    /**
     * This method transfers data for logging in to Dao layer.
     *
     * @param login    user's login.
     * @param password user's password.
     * @return {@code Optional<User>} user founded by login and password.
     * @throws ServiceException if any service exception occurred during processing.
     */
    Optional<User> login(String login, String password) throws ServiceException;

    /**
     * The purpose of this method is to create user.
     *
     * @param name              user's name.
     * @param surname           user's surname.
     * @param login             user's login.
     * @param password          user's password.
     * @param confirmedPassword user's confirmedPassword.
     * @param rules             user-accepted terms of use.
     * @return {@code Optional<User>} registered user.
     * @throws ServiceException if any service exception occurred during processing.
     * */
    Optional<User> register(String name, String surname, String login,
                              String password, String confirmedPassword, String rules) throws  ServiceException;

    /**
     * The purpose of this method is to deposit money {@code money} into account of user {@code user}.
     *
     * @param user  user who is going to top up his account.
     * @param card  user's card.
     * @param money user's money to top up.
     * @return {@code true} if the operation is held successfully.
     * @throws ServiceException if any service exception occurred during processing.
     * */
    boolean isDeposited(User user, String card, String money) throws ServiceException;

    /**
     * Purpose of this method is to get specific amount of users from database.
     *
     * @return {@code List<User>} list of all found users.
     * @throws ServiceException if any service exception occurred during processing.
     */
    List<User> getUsers() throws ServiceException;

    /**
     * This method let admin change user's block.
     *
     * @param id user's id.
     * @throws ServiceException if any service exception occurred during processing.
     * */
    void changeBlock(Long id) throws ServiceException;

    /**
     * This method let admin change user's loyalty points.
     *
     * @param id            user's id.
     * @param loyaltyPoints loyalty point value to be set.
     * @throws ServiceException if any service exception occurred during processing.
     * */
    void changeLoyaltyPoints(Long id, Integer loyaltyPoints) throws ServiceException;

    /**
     * The purpose of this method is to punish user if he doesn't pick the order.
     *
     * @param userId user's id
     * @throws ServiceException if any service exception occurred during processing.
     * */
    void fineUser(Long userId) throws ServiceException;

    /**
     * The purpose of this method is to reward user by adding {@code loyaltyPoints} if he picks the order.
     *
     * @param id            user's id
     * @param loyaltyPoints loyalty point's amount to be added.
     * @throws ServiceException if any service exception occurred during processing.
     * */
    void rewardUser(Long id, int loyaltyPoints) throws ServiceException;
}
