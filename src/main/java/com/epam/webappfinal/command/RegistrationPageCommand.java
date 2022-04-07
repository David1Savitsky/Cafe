package com.epam.webappfinal.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationPageCommand implements Command {

    private static final String REGISTRATION_PAGE = "/registration.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return CommandResult.forward(REGISTRATION_PAGE);
    }
}
