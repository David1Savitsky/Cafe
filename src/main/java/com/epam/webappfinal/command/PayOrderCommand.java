package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.PaymentType;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Locale;

public class PayOrderCommand implements Command {

    private static final String DATE_TEXT_REPRESENTATION = "date";
    private static final String TYPE_TEXT_REPRESENTATION = "type";
    private static final String TOTAL_TEXT_REPRESENTATION = "total";
    private static final String ERROR_VARIABLE_TEXT_REPRESENTATION = "orderError";
    private static final String ERROR_TEXT_REPRESENTATION = "Invalid input data";
    private static final String SHOPPING_CART_COMMAND = "controller?command=shoppingCart";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final OrderService orderService;

    public PayOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String dateStr = req.getParameter(DATE_TEXT_REPRESENTATION);

            String paymentTypeStr = req.getParameter(TYPE_TEXT_REPRESENTATION);
            PaymentType paymentType = PaymentType.valueOf(paymentTypeStr.toUpperCase(Locale.ROOT));
            String totalAmountStr = req.getParameter(TOTAL_TEXT_REPRESENTATION);
            BigDecimal totalAmount = BigDecimal.valueOf(Double.valueOf(totalAmountStr));

            Boolean isPayed = orderService.payOrder(user, totalAmount, dateStr, paymentType);
            if (!isPayed) {
                session.setAttribute(ERROR_VARIABLE_TEXT_REPRESENTATION, ERROR_TEXT_REPRESENTATION);
            } else {
                session.setAttribute(ERROR_VARIABLE_TEXT_REPRESENTATION, "");
            }
            return CommandResult.redirect(SHOPPING_CART_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
