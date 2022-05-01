package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String LOGIN_PAGE = "/login.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        LOGGER.debug("Start forwarding login page");
        return CommandResult.forward(LOGIN_PAGE);
    }
}
