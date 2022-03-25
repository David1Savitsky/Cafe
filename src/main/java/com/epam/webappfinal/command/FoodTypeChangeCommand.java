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
import java.util.ArrayList;
import java.util.List;

public class FoodTypeChangeCommand implements Command{

    private static final String TYPE = "foodType";
    private static final String DRINK_TYPE = "drink";
    private static final String MEAL_TYPE = "meal";

    private static final String ACCOUNT_MONEY_TEXT_REPRESENTATION = "accountMoney";
    private static final String FOOD_LIST_SIZE_TEXT_REPRESENTATION = "foodListSize";
    private static final String FOOD_LIST_TEXT_REPRESENTATION = "foodList";
    private static final String MAIN_PAGE = "/main.jsp";

    private final FoodService foodService;

    public FoodTypeChangeCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        List<Food> foodList = new ArrayList<>();
        String selectedFoodType = req.getParameter(TYPE);
        switch (selectedFoodType) {
            case DRINK_TYPE:
                foodList = foodService.getFood(FoodType.DRINK);
                break;
            case MEAL_TYPE:
                foodList = foodService.getFood(FoodType.MEAL);
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            BigDecimal accountMoney = user.getAmount();
            req.setAttribute(ACCOUNT_MONEY_TEXT_REPRESENTATION, accountMoney);
        }
        req.setAttribute(FOOD_LIST_SIZE_TEXT_REPRESENTATION, foodList.size());
        req.setAttribute(FOOD_LIST_TEXT_REPRESENTATION, foodList);
        return CommandResult.forward(MAIN_PAGE);
    }
}
