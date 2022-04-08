package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String LOGIN_AFTER_LOGOUT_COMMAND = "controller?command=loginAfterLogout";
    private static final String LOGIN_PAGE = "/login.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("Start logging out");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            req.getSession().invalidate();
            return CommandResult.redirect(LOGIN_AFTER_LOGOUT_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
