package com.epam.webappfinal.controller.command;

import com.epam.webappfinal.controller.service.UserServiceImpl;

public class CommandFactory {

    public Command createCommand(String command) {
        switch (command){
            case "login":
                return new LoginCommand(new UserServiceImpl());
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
