package com.epam.webappfinal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    private static final String LOGIN_AFTER_LOGOUT_COMMAND = "controller?command=loginAfterLogout";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return CommandResult.redirect(LOGIN_AFTER_LOGOUT_COMMAND);
    }
}
