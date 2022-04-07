package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final String LOGIN_TEXT_REPRESENTATION = "login";
    private static final String PASSWORD_TEXT_REPRESENTATION = "password";
    private static final String MAIN_PAGE_COMMAND = "controller?command=mainPage";
    private static final String LOGIN_PAGE = "/login.jsp";
    private static final String ERROR_MESSAGE_VARIABLE_REPRESENTATION = "errorMessage";
    private static final String ERROR_MESSAGE_TEXT_REPRESENTATION = "Invalid login or password";

    private final UserService userService;

    public LoginCommand(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String login = req.getParameter(LOGIN_TEXT_REPRESENTATION);
        String password = req.getParameter(PASSWORD_TEXT_REPRESENTATION);
        Optional<User> user = userService.login(login, password);
        CommandResult result;
        if (user.isPresent()) {
            req.getSession().setAttribute(User.TABLE_NAME, user.get());
            result = CommandResult.redirect(MAIN_PAGE_COMMAND);
        } else {
            req.setAttribute(ERROR_MESSAGE_VARIABLE_REPRESENTATION, ERROR_MESSAGE_TEXT_REPRESENTATION);
            result = CommandResult.forward(LOGIN_PAGE);
        }
        return result;
    }
}
