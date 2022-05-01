package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Locale;

public class AddToShoppingCartCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String LOGIN_PAGE = "/login.jsp";

    private final OrderService orderService;

    public AddToShoppingCartCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        LOGGER.debug("Start adding food to shopping cart");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String foodIdStr = req.getParameter("foodId");
            Long foodId = Long.parseLong(foodIdStr);

            orderService.addFoodToShoppingCart(foodId, user.getId());
            return CommandResult.redirect("controller?command=mainPage");
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
