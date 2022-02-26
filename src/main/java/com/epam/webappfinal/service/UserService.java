package com.epam.webappfinal.service;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;

import java.util.Optional;

public interface UserService {

    Optional<User> login(String login, String password) throws ServiceException;
}
