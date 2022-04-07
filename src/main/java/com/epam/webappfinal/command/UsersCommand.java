package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class UsersCommand implements Command {

    private static final String USERS_PAGE = "/users.jsp";
    private static final String USER_LIST_TEXT_REPRESENTATION = "userList";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final UserService userService;

    public UsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null) {
            List<User> userList = userService.getUsers();
            req.setAttribute(USER_LIST_TEXT_REPRESENTATION, userList);
            return CommandResult.forward(USERS_PAGE);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}
