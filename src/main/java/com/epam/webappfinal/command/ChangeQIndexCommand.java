package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeQIndexCommand implements Command {

    private static final String LOCALE_Q_INDEX = "q_input";
    private static final String Q_INDEX_MINUS = "minus";
    private static final String QUANTITY_NAME = "quantity";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String quantity = req.getParameter(QUANTITY_NAME);
        Integer quantityInt = Integer.parseInt(quantity);
        String newQIndex = req.getParameter("type");
        if (Q_INDEX_MINUS.equals(newQIndex)) {
            if (quantityInt > 1) {
                quantityInt--;
            }
        } else
        {
            quantityInt++;
        }
        HttpSession session = req.getSession();
        session.setAttribute(LOCALE_Q_INDEX, quantityInt);
        return CommandResult.redirect("controller?command=mainPage");
    }
}
