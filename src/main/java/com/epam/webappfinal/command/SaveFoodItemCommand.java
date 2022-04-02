package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class SaveFoodItemCommand implements Command {

    private static final String MAIN_PAGE_COMMAND = "controller?command=mainPage";

    private final FoodService foodService;

    public SaveFoodItemCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String foodIdStr = req.getParameter("foodId");
        Long foodId = Long.parseLong(foodIdStr);
        String name = req.getParameter("name");
        String typeIdStr = req.getParameter("typeId");
        Long typeId = Long.parseLong(typeIdStr);
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        if (price.compareTo(new BigDecimal(0)) > 0) {
            foodService.saveFood(foodId, name, typeId, price);
        }
        return CommandResult.redirect(MAIN_PAGE_COMMAND);
    }
}