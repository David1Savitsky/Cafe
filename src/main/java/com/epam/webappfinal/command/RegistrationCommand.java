package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RegistrationCommand implements Command {

    private final Logger LOGGER = LogManager.getLogger();

    private static final String NAME_TEXT_REPRESENTATION = "name";
    private static final String SURNAME_TEXT_REPRESENTATION = "surname";
    private static final String LOGIN_TEXT_REPRESENTATION = "login";
    private static final String PASSWORD_TEXT_REPRESENTATION = "password";
    private static final String CONFIRMED_PASSWORD_TEXT_REPRESENTATION = "confirmedPassword";
    private static final String RULES_TEXT_REPRESENTATION = "rules";
    private static final String MAIN_PAGE_COMMAND = "controller?command=mainPage";
    private static final String REGISTRATION_PAGE_COMMAND = "/registration.jsp";

    private static final String ERROR_MESSAGE_VARIABLE_REPRESENTATION = "errorRegistrationMessage";
    private static final String ERROR_MESSAGE_TEXT_REPRESENTATION = "Invalid input information";

    private final UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        LOGGER.debug("Start registering");
        String name = req.getParameter(NAME_TEXT_REPRESENTATION);
        String surname = req.getParameter(SURNAME_TEXT_REPRESENTATION);
        String login = req.getParameter(LOGIN_TEXT_REPRESENTATION);
        String password = req.getParameter(PASSWORD_TEXT_REPRESENTATION);
        String confirmedPassword = req.getParameter(CONFIRMED_PASSWORD_TEXT_REPRESENTATION);
        String rules = req.getParameter(RULES_TEXT_REPRESENTATION);

        Optional<User> user = userService.register(name, surname, login, password, confirmedPassword, rules);
        CommandResult result;
        if (user.isPresent()) {
            req.getSession().setAttribute(User.TABLE_NAME, user.get());
            result = CommandResult.redirect(MAIN_PAGE_COMMAND);
        } else {
            req.setAttribute(ERROR_MESSAGE_VARIABLE_REPRESENTATION, ERROR_MESSAGE_TEXT_REPRESENTATION);
            result = CommandResult.forward(REGISTRATION_PAGE_COMMAND);
        }
        return result;
    }
}
