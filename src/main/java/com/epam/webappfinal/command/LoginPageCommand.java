package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageCommand implements Command {

    private static final String LOGIN_PAGE = "/login.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        return CommandResult.forward(LOGIN_PAGE);
    }
}
