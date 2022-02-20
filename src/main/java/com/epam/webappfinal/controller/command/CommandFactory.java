package com.epam.webappfinal.controller.command;

import com.epam.webappfinal.controller.service.UserRegistrationServiceImpl;
import com.epam.webappfinal.controller.service.UserServiceImpl;

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
