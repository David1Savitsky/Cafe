package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class SaveFoodItemCommand implements Command {

    private static final String MAIN_PAGE_COMMAND = "controller?command=mainPage";
    private static final String FOOD_ID_TEXT_REPRESENTATION = "foodId";
    private static final String NAME_TEXT_REPRESENTATION = "name";
    private static final String TYPE_ID_TEXT_REPRESENTATION = "typeId";
    private static final String PRICE_TEXT_REPRESENTATION = "price";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final FoodService foodService;

    public SaveFoodItemCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String foodIdStr = req.getParameter(FOOD_ID_TEXT_REPRESENTATION);
            Long foodId = Long.parseLong(foodIdStr);
            String name = req.getParameter(NAME_TEXT_REPRESENTATION);
            String typeIdStr = req.getParameter(TYPE_ID_TEXT_REPRESENTATION);
            Long typeId = Long.parseLong(typeIdStr);
            BigDecimal price = new BigDecimal(req.getParameter(PRICE_TEXT_REPRESENTATION));
            if (price.compareTo(new BigDecimal(0)) > 0) {
                foodService.saveFood(foodId, name, typeId, price);
            }
            return CommandResult.redirect(MAIN_PAGE_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }

    }
}
