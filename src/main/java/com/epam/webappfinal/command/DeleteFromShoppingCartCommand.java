package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OperationType;
import com.epam.webappfinal.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteFromShoppingCartCommand implements Command{

    private static final String SHOPPING_CART_PAGE_COMMAND = "controller?command=shoppingCart";
    private static final String FOOD_ID_TEXT_REPRESENTATION = "foodId";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final OrderService orderService;

    public DeleteFromShoppingCartCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String foodIdStr = req.getParameter(FOOD_ID_TEXT_REPRESENTATION);
            Long foodId = Long.parseLong(foodIdStr);

            orderService.operateWithFoodFromShoppingCart(foodId, user.getId(), OperationType.DELETE);

            return CommandResult.redirect(SHOPPING_CART_PAGE_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }

    }
}
