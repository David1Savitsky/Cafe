package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Locale;

public class AddFoodItemCommand implements Command {

    private static final String MAIN_PAGE_COMMAND = "controller?command=mainPage";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final FoodService foodService;

    public AddFoodItemCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String name = req.getParameter("name");
            String type = req.getParameter("type").toLowerCase(Locale.ROOT);
            BigDecimal price = new BigDecimal(req.getParameter("price"));
            foodService.addFood(name, type, price);
            return CommandResult.redirect(MAIN_PAGE_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }

    }
}
