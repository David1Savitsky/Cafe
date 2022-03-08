package com.epam.webappfinal.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageChangeCommand implements Command {

    private static final String LOCALE_PARAMETER = "locale";
    private static final String MAIN_PAGE_COMMAND = "controller?command=mainPage";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String newLocale = req.getParameter(LOCALE_PARAMETER);
        HttpSession session = req.getSession();
        session.setAttribute(LOCALE_PARAMETER, newLocale);
        return CommandResult.redirect(MAIN_PAGE_COMMAND);
    }
}
