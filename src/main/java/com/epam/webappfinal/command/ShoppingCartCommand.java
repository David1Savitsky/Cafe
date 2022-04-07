package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.OrderService;
import javafx.util.Pair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartCommand implements Command {

    private static final String SHOPPING_CART_PAGE = "/shopping_cart.jsp";
    private static final String ACCOUNT_MONEY_VARIABLE_REPRESENTATION = "accountMoney";
    private static final String FOOD_LIST_TEXT_REPRESENTATION = "foodList";
    private static final String FOOD_LIST_SIZE_TEXT_REPRESENTATION = "foodListSize";
    private static final String TOTAL_AMOUNT_TEXT_REPRESENTATION = "totalAmount";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final OrderService orderService;

    public ShoppingCartCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            BigDecimal accountMoney = user.getAmount();
            req.setAttribute(ACCOUNT_MONEY_VARIABLE_REPRESENTATION, accountMoney);

            List<Pair<Food, Integer>> foodList = orderService.getFoodInShoppingCart(user.getId());
            BigDecimal totalAmount = orderService.getTotalAmount(foodList, user.getLoyaltyPoints());

            req.setAttribute(FOOD_LIST_TEXT_REPRESENTATION, foodList);
            req.setAttribute(FOOD_LIST_SIZE_TEXT_REPRESENTATION, foodList.size());
            req.setAttribute(TOTAL_AMOUNT_TEXT_REPRESENTATION, totalAmount);

            return CommandResult.forward(SHOPPING_CART_PAGE);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
