package com.epam.webappfinal.service;

public class UserRegistrationServiceImpl {

    public boolean login(String name, String surname, String login, String password, String confirmedPassword) {
        return !name.isEmpty()
                && !surname.isEmpty()
                && !login.isEmpty()
                && !password.isEmpty()
                && password.equals(confirmedPassword);
    }
}
