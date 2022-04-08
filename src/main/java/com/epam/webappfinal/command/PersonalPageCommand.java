package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class PersonalPageCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String PERSONAL_PAGE = "/personal.jsp";
    private static final String ACCOUNT_MONEY_TEXT_REPRESENTATION = "accountMoney";
    private static final String LOGIN_PAGE = "/login.jsp";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        LOGGER.debug("Start accessing personal page");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null ) {
            BigDecimal accountMoney = user.getAmount();
            req.setAttribute(ACCOUNT_MONEY_TEXT_REPRESENTATION, accountMoney);
            return CommandResult.forward(PERSONAL_PAGE);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
