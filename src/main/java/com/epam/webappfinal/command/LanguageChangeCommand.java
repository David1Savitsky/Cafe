package com.epam.webappfinal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageChangeCommand implements Command {

    private static final String LOCALE_PARAMETER = "locale";
    private static final String REFERER_TEXT_REPRESENTATION = "referer";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String newLocale = req.getParameter(LOCALE_PARAMETER);
        HttpSession session = req.getSession();
        session.setAttribute(LOCALE_PARAMETER, newLocale);
        String requestedPage = req.getHeader(REFERER_TEXT_REPRESENTATION);
        return CommandResult.redirect(requestedPage);
    }
}
