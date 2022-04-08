package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.Comment;
import com.epam.webappfinal.entity.Food;
import com.epam.webappfinal.entity.Rating;
import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class RatingCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String RATING_PAGE = "/rating.jsp";
    private static final String FOOD_ID_TEXT_REPRESENTATION = "foodId";
    private static final String FOOD_TEXT_REPRESENTATION = "food";
    private static final String RATING_ITEM_TEXT_REPRESENTATION = "rating";
    private static final String ACCOUNT_MONEY_VARIABLE_REPRESENTATION = "accountMoney";
    private static final String COMMENT_LIST_REPRESENTATION = "commentList";
    private static final String COMMENT_LIST_SIZE_REPRESENTATION = "commentListSize";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final FoodService foodService;

    public RatingCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        LOGGER.debug("Start changing food rating");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            BigDecimal accountMoney = user.getAmount();
            req.setAttribute(ACCOUNT_MONEY_VARIABLE_REPRESENTATION, accountMoney);

            String foodIdStr = req.getParameter(FOOD_ID_TEXT_REPRESENTATION);
            if (foodIdStr == null) {
                foodIdStr = (String) session.getAttribute(FOOD_ID_TEXT_REPRESENTATION);
            } else {
                session.setAttribute(FOOD_ID_TEXT_REPRESENTATION, foodIdStr);
            }
            Long foodId = Long.parseLong(foodIdStr);
            Food food = foodService.getFoodById(foodId);
            Rating rating = foodService.getRating(foodId, user.getId());

            req.setAttribute(FOOD_TEXT_REPRESENTATION, food);
            req.setAttribute(FOOD_ID_TEXT_REPRESENTATION, foodId);
            req.setAttribute(RATING_ITEM_TEXT_REPRESENTATION, rating);

            List<Pair<User, Comment>> commentList = foodService.getComments(foodId);
            req.setAttribute(COMMENT_LIST_REPRESENTATION, commentList);
            req.setAttribute(COMMENT_LIST_SIZE_REPRESENTATION, commentList.size());

            return CommandResult.forward(RATING_PAGE);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }

    }
}
