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

    private final OrderService orderService;

    public DeleteFromShoppingCartCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String foodIdStr = req.getParameter("foodId");
        Long foodId = Long.parseLong(foodIdStr);
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(User.TABLE_NAME);

        orderService.operateWithFoodFromShoppingCart(foodId, user.getId(), OperationType.DELETE);

        return CommandResult.redirect(SHOPPING_CART_PAGE_COMMAND);
    }
}
