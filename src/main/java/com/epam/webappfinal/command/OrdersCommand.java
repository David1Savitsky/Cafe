package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.Order;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OrderService;
import javafx.util.Pair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class OrdersCommand implements Command {

    private static final String ADMIN_TEXT_REPRESENTATION = "admin";
    private static final String ACCOUNT_MONEY_TEXT_REPRESENTATION = "accountMoney";

    private final OrderService orderService;

    public OrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null || session.getAttribute(ADMIN_TEXT_REPRESENTATION) != null) {

            if (user != null) {
                BigDecimal accountMoney = user.getAmount();
                req.setAttribute(ACCOUNT_MONEY_TEXT_REPRESENTATION, accountMoney);
            }
        }

        List<ImmutableTriple<Order, List<Food>, BigDecimal>> orderList = orderService.getOrdersWithFood(user.getId());
        req.setAttribute("orderList", orderList);
        System.out.println(orderList);
        return CommandResult.forward("/orders.jsp");
    }
}
