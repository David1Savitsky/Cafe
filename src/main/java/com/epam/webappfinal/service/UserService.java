package com.epam.webappfinal.service;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> login(String login, String password) throws ServiceException;

    boolean isDeposited(User user, String card, String money) throws ServiceException;

    List<User> getUsers() throws ServiceException;

    void changeBlock(Long id) throws ServiceException;

    void changeLoyaltyPoints(Long id, Integer loyaltyPoints) throws ServiceException;

    void fineUser(Long userId) throws ServiceException;

    void rewardUser(Long id, int loyaltyPoints) throws ServiceException;
}
