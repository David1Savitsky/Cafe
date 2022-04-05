package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddCommentCommand implements Command {

    private static final String COMMENT_TEXT_REPRESENTATION = "comment";
    private static final String FOOD_ID_TEXT_REPRESENTATION = "foodId";
    private static final String RATING_PAGE_COMMAND = "controller?command=rating";

    private final FoodService foodService;

    public AddCommentCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String foodIdStr = req.getParameter(FOOD_ID_TEXT_REPRESENTATION);
        Long foodId = Long.parseLong(foodIdStr);
        String comment = req.getParameter(COMMENT_TEXT_REPRESENTATION);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        foodService.addComment(foodId, user.getId(), comment);

        return CommandResult.redirect(RATING_PAGE_COMMAND);
    }
}
