package com.epam.webappfinal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return CommandResult.redirect("controller?command=loginAfterLogout");
    }
}
