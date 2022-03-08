package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.FoodType;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainPageCommand implements Command {

    private final FoodService foodService;

    public MainPageCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null || session.getAttribute("admin") != null) {

            List<Food> foodList = foodService.getFood(FoodType.ALL);
            req.setAttribute("foodListSize", foodList.size());
            req.setAttribute("foodList", foodList);

            return CommandResult.forward("/main.jsp");
        }
        return CommandResult.forward("/login.jsp");
    }
}
