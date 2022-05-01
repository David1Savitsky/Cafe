package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddCommentCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String COMMENT_TEXT_REPRESENTATION = "comment";
    private static final String FOOD_ID_TEXT_REPRESENTATION = "foodId";
    private static final String RATING_PAGE_COMMAND = "controller?command=rating";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final FoodService foodService;

    public AddCommentCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        LOGGER.debug("Start adding comment");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String foodIdStr = req.getParameter(FOOD_ID_TEXT_REPRESENTATION);
            Long foodId = Long.parseLong(foodIdStr);
            String comment = req.getParameter(COMMENT_TEXT_REPRESENTATION);
            foodService.addComment(foodId, user.getId(), comment);
            return CommandResult.redirect(RATING_PAGE_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
