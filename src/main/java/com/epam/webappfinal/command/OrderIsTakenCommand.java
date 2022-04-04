package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderIsTakenCommand implements Command {

    private static final String ORDER_ID_TEXT_REPRESENTATION = "orderId";
    private static final String ORDERS_PAGE_COMMAND = "controller?command=orders";

    private final OrderService orderService;

    public OrderIsTakenCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String orderIdStr = req.getParameter(ORDER_ID_TEXT_REPRESENTATION);
        Long orderId = Long.parseLong(orderIdStr);
        orderService.placeAnOrder(orderId);

        return CommandResult.redirect(ORDERS_PAGE_COMMAND);
    }
}
