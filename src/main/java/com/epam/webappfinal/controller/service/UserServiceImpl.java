package com.epam.webappfinal.controller.service;

public class UserServiceImpl implements UserService {

    public boolean login(String login, String password) {

        //Добавить проверку на наличие такого пользователя в БД

        return "admin".equals(login) && "admin".equals(password);
    }
}
