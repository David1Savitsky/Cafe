package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeBlockCommand implements Command {

    private static final String USERS_PAGE_COMMAND = "controller?command=users";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final UserService userService;

    public ChangeBlockCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            String userIdStr = req.getParameter("userId");
            Long userId = Long.parseLong(userIdStr);
            userService.changeBlock(userId);
            return CommandResult.redirect(USERS_PAGE_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }

    }
}
