package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.PaymentType;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public class PayOrderCommand implements Command {

    private final OrderService orderService;

    public PayOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);

        String dateStr = req.getParameter("date");

        String paymentTypeStr = req.getParameter("type");
        PaymentType paymentType = PaymentType.valueOf(paymentTypeStr.toUpperCase(Locale.ROOT));
        String totalAmountStr = req.getParameter("total");
        BigDecimal totalAmount = BigDecimal.valueOf(Double.valueOf(totalAmountStr));

        Boolean isPayed = orderService.payOrder(user, totalAmount, dateStr, paymentType);
        if (!isPayed) {
            session.setAttribute("orderError", "Invalid input data");
        } else {
            session.setAttribute("orderError", "");
        }
        return CommandResult.redirect("controller?command=shoppingCart");
    }
}
