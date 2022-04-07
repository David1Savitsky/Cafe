package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OrderService;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class OrdersCommand implements Command {

    private static final String ACCOUNT_MONEY_TEXT_REPRESENTATION = "accountMoney";
    private static final String ORDER_LIST_SIZE_TEXT_REPRESENTATION = "orderListSize";
    private static final String ORDER_LIST_TEXT_REPRESENTATION = "orderList";
    private static final String ORDER_PAGE = "/orders.jsp";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final OrderService orderService;

    public OrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            List<ImmutableTriple<Order, List<Food>, BigDecimal>> orderList;
            if (user.isAdmin()) {
                orderList = orderService.getOrdersWithFood();
            } else {
                BigDecimal accountMoney = user.getAmount();
                req.setAttribute(ACCOUNT_MONEY_TEXT_REPRESENTATION, accountMoney);
                orderList = orderService.getOrdersWithFood(user.getId());
            }

            req.setAttribute(ORDER_LIST_TEXT_REPRESENTATION, orderList);
            req.setAttribute(ORDER_LIST_SIZE_TEXT_REPRESENTATION, orderList.size());
            return CommandResult.forward(ORDER_PAGE);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
