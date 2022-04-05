package com.epam.webappfinal.command;

import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.FoodService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommentItemCommand implements Command {

    private static final String COMMENT_ID_TEXT_REPRESENTATION = "commentId";
    private static final String RATING_PAGE_COMMAND = "controller?command=rating";

    private final FoodService foodService;

    public DeleteCommentItemCommand(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String commentIdStr = req.getParameter(COMMENT_ID_TEXT_REPRESENTATION);
        Long commentId = Long.parseLong(commentIdStr);
        foodService.deleteComment(commentId);
        return CommandResult.redirect(RATING_PAGE_COMMAND);
    }
}
