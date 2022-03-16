package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class ShoppingCartCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            BigDecimal accountMoney = user.getAmount();
            req.setAttribute("accountMoney", accountMoney);
        }

        return CommandResult.forward("/shopping_cart.jsp");
    }
}
