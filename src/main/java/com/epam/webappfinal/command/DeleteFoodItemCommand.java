package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFoodItemCommand implements Command {

    private static final String MAIN_PAGE_COMMAND = "controller?command=mainPage";

    private final FoodService foodService;

    public DeleteFoodItemCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String foodIdStr = req.getParameter("foodId");
        Long foodId = Long.parseLong(foodIdStr);
        foodService.deleteFood(foodId);
        return CommandResult.redirect(MAIN_PAGE_COMMAND);
    }
}
