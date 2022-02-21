package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException;
}
