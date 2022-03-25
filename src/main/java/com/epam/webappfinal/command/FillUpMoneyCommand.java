package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FillUpMoneyCommand implements Command {

    private static final String REFERER_TEXT_REPRESENTATION = "referer";
    private static final String CARD_TEXT_REPRESENTATION = "card";
    private static final String MONEY_TEXT_REPRESENTATION = "money";
    private static final String POP_UP_TEXT_REPRESENTATION = "#popup";
    private static final String INVALID_INPUT = "Invalid input data";
    private static final String INVALID_ERROR_VARIABLE = "errorFillUp";

    private final UserService userService;

    public FillUpMoneyCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        String cardNumber = req.getParameter(CARD_TEXT_REPRESENTATION);
        String money = req.getParameter(MONEY_TEXT_REPRESENTATION);
        boolean isSuccess = userService.isDeposited(user, cardNumber, money);
        String requestedPage = req.getHeader(REFERER_TEXT_REPRESENTATION);
        CommandResult result;
        if (!isSuccess) {
            session.setAttribute(INVALID_ERROR_VARIABLE, INVALID_INPUT);
            result = CommandResult.redirect(requestedPage + POP_UP_TEXT_REPRESENTATION);
        } else {
            session.setAttribute(INVALID_ERROR_VARIABLE, null);
            result = CommandResult.redirect(requestedPage);
        }
        return result;
    }
}
