package com.epam.webappfinal.controller.service;

public class UserServiceImpl implements UserService {

    public boolean login(String login, String password) {
        return "admin".equals(login) && "admin".equals(password);
    }
}
