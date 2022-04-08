package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OperationType;
import com.epam.webappfinal.service.OrderService;
import com.epam.webappfinal.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderIsRejectedCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String ORDER_ID_TEXT_REPRESENTATION = "orderId";
    private static final String USER_ID_TEXT_REPRESENTATION = "userId";
    private static final String ORDERS_PAGE_COMMAND = "controller?command=orders";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final OrderService orderService;
    private final UserService userService;

    public OrderIsRejectedCommand(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        LOGGER.debug("Admin start rejecting the order");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String userIdStr = req.getParameter(USER_ID_TEXT_REPRESENTATION);
            Long userId = Long.parseLong(userIdStr);
            userService.fineUser(userId);


            String orderIdStr = req.getParameter(ORDER_ID_TEXT_REPRESENTATION);
            Long orderId = Long.parseLong(orderIdStr);
            orderService.placeAnOrder(orderId, OperationType.DELETE);

            return CommandResult.redirect(ORDERS_PAGE_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}

