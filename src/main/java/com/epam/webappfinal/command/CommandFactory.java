package com.epam.webappfinal.command;

import com.epam.webappfinal.service.UserRegistrationServiceImpl;
import com.epam.webappfinal.service.UserServiceImpl;

public class CommandFactory {

    public Command createCommand(String command) {
        switch (command){
            case "login":
                return new LoginCommand(new UserServiceImpl());
            case "registration":
                return new RegistrationCommand(new UserRegistrationServiceImpl());
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
