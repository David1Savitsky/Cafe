package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OrderService;
import com.mysql.cj.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddToShoppingCartCommand implements Command {

    private static final String MAIN_PAGE_COMMAND = "controller?command=mainPage";

    private final OrderService orderService;

    public AddToShoppingCartCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String foodIdStr = req.getParameter("foodId");
        Long foodId = Long.parseLong(foodIdStr);
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute(User.TABLE_NAME);

        orderService.addFoodToShoppingCart(foodId, user.getId());
        //session.setAttribute("is_added", "true");

        return CommandResult.redirect(MAIN_PAGE_COMMAND);
    }
}
