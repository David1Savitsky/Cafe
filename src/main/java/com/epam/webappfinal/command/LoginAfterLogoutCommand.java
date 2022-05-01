package com.epam.webappfinal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAfterLogoutCommand implements Command {

    private static final String LOGIN_PAGE = "/login.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        return CommandResult.forward(LOGIN_PAGE);
    }
}
