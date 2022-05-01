package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeRatingCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String RATING_TEXT_REPRESENTATION = "rating";
    private static final String FOOD_ID_TEXT_REPRESENTATION = "foodId";
    private static final String RATING_PAGE_COMMAND = "controller?command=rating";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final FoodService foodService;

    public ChangeRatingCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        LOGGER.debug("Start changing food rating");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String foodIdStr = req.getParameter(FOOD_ID_TEXT_REPRESENTATION);
            Long foodId = Long.parseLong(foodIdStr);
            String ratingStr = req.getParameter(RATING_TEXT_REPRESENTATION);
            int rating = Integer.parseInt(ratingStr);
            foodService.changeRating(foodId, user.getId(), rating);
            return CommandResult.redirect(RATING_PAGE_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
