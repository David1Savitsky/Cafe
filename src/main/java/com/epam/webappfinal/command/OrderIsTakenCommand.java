package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OperationType;
import com.epam.webappfinal.service.OrderService;
import com.epam.webappfinal.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderIsTakenCommand implements Command {

    private static final String ORDER_ID_TEXT_REPRESENTATION = "orderId";
    private static final String USER_ID_TEXT_REPRESENTATION = "userId";
    private static final String TOTAL_AMOUNT_TEXT_REPRESENTATION = "totalAmount";
    private static final String ORDERS_PAGE_COMMAND = "controller?command=orders";

    private final OrderService orderService;
    private final UserService userService;

    public OrderIsTakenCommand(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String userIdStr = req.getParameter(USER_ID_TEXT_REPRESENTATION);
        Long userId = Long.parseLong(userIdStr);
        String totalAmountStr = req.getParameter(TOTAL_AMOUNT_TEXT_REPRESENTATION);
        int totalAmount = Integer.parseInt(totalAmountStr);
        userService.rewardUser(userId, totalAmount);

        String orderIdStr = req.getParameter(ORDER_ID_TEXT_REPRESENTATION);
        Long orderId = Long.parseLong(orderIdStr);
        orderService.placeAnOrder(orderId, OperationType.ACCEPT);

        return CommandResult.redirect(ORDERS_PAGE_COMMAND);
    }
}
