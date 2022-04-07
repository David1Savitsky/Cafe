package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteFoodItemCommand implements Command {

    private static final String MAIN_PAGE_COMMAND = "controller?command=mainPage";
    private static final String FOOD_ID_TEXT_REPRESENTATION = "foodId";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final FoodService foodService;

    public DeleteFoodItemCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String foodIdStr = req.getParameter(FOOD_ID_TEXT_REPRESENTATION);
            Long foodId = Long.parseLong(foodIdStr);
            foodService.deleteFood(foodId);
            return CommandResult.redirect(MAIN_PAGE_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
