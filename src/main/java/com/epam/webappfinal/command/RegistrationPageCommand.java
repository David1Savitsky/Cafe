package com.epam.webappfinal.command;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationPageCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String REGISTRATION_PAGE = "/registration.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("Start accessing registration page");
        return CommandResult.forward(REGISTRATION_PAGE);
    }
}
