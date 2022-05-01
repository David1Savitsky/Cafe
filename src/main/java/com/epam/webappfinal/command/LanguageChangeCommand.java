package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageChangeCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String LOCALE_PARAMETER = "locale";
    private static final String REFERER_TEXT_REPRESENTATION = "referer";
    private static final String LOGIN_PAGE = "/login.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.debug("Start changing the language");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String newLocale = req.getParameter(LOCALE_PARAMETER);
            session.setAttribute(LOCALE_PARAMETER, newLocale);
            String requestedPage = req.getHeader(REFERER_TEXT_REPRESENTATION);
            return CommandResult.redirect(requestedPage);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
