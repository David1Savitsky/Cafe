package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRatingCommand implements Command {

    private static final String RATING_TEXT_REPRESENTATION = "rating";
    private static final String ORDER_ID_TEXT_REPRESENTATION = "orderId";
    private static final String ORDERS_PAGE_COMMAND = "controller?command=orders";

    private final OrderService orderService;

    public ChangeRatingCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String orderIdStr = req.getParameter(ORDER_ID_TEXT_REPRESENTATION);
        Long orderId = Long.parseLong(orderIdStr);
        String ratingStr = req.getParameter(RATING_TEXT_REPRESENTATION);
        int rating = Integer.parseInt(ratingStr);
        orderService.changeRating(orderId, rating);

        return CommandResult.redirect(ORDERS_PAGE_COMMAND);
    }
}
