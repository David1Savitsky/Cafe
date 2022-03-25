package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class MainPageCommand implements Command {

    private static final String ADMIN_TEXT_REPRESENTATION = "admin";
    private static final String ACCOUNT_MONEY_TEXT_REPRESENTATION = "accountMoney";
    private static final String FOOD_LIST_SIZE_TEXT_REPRESENTATION = "foodListSize";
    private static final String FOOD_LIST_TEXT_REPRESENTATION = "foodList";
    private static final String MAIN_PAGE = "/main.jsp";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final FoodService foodService;

    public MainPageCommand(FoodService foodService) {
        this.foodService = foodService;
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

            List<Food> foodList = foodService.getFood(FoodType.ALL);
            req.setAttribute(FOOD_LIST_SIZE_TEXT_REPRESENTATION, foodList.size());
            req.setAttribute(FOOD_LIST_TEXT_REPRESENTATION, foodList);

            return CommandResult.forward(MAIN_PAGE);
        }
        return CommandResult.forward(LOGIN_PAGE);
    }
}
