package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is an interface for basic request and response processing.
 *
 * @author David Savitsky
 * @version 1.0
 * @since 1.0
 */
public interface Command {
    /**
     * This method is responsible for request and response processing. It takes
     * {@code HttpServletRequest} and {@code HttpServletResponse} as parameters and
     * returns {@code CommandResult}.
     *
     * @param req  request which is given for processing.
     * @param resp response which is given for processing.
     * @return CommandResult object that declares page to which user will be
     *         redirected or forwarded.
     * @throws ServiceException if any service exception occurred during processing.
     *
     */
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException;
}
