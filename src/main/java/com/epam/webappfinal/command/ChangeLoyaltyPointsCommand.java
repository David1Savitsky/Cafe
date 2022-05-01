package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLoyaltyPointsCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String USERS_PAGE_COMMAND = "controller?command=users";
    private static final String LOGIN_PAGE = "/login.jsp";

    private final UserService userService;

    public ChangeLoyaltyPointsCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        LOGGER.debug("Admin start changing user loyalty points");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(User.TABLE_NAME);
        if (user != null && user.isAdmin()) {
            String userIdStr = req.getParameter("userId");
            Long userId = Long.parseLong(userIdStr);
            String loyaltyPointsStr = req.getParameter("loyaltyPoints");
            Integer loyaltyPoints = Integer.parseInt(loyaltyPointsStr);
            userService.changeLoyaltyPoints(userId, loyaltyPoints);
            return CommandResult.redirect(USERS_PAGE_COMMAND);
        } else {
            return CommandResult.forward(LOGIN_PAGE);
        }

    }
}
