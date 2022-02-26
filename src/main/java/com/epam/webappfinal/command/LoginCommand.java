package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> user = userService.login(login, password);
        CommandResult result;
        if (user.isPresent()) {
            req.getSession().setAttribute("user", user.get());
            result = CommandResult.redirect("controller?command=mainPage");
        } else {
            req.setAttribute("errorMessage", "Invalid login or password");
            result = CommandResult.forward("/login.jsp");
        }
        return result;
    }

//    public String execute(HttpServletRequest req, HttpServletResponse resp) {
//        String login = req.getParameter("login");
//        String password = req.getParameter("password");
//        if (userService.login(login, password)) {
//            //req.getSession().setAttribute("user", "admin");
//
//            //Подгружать с БД его данные
//
//            return "main.jsp";
//        } else {
//            req.setAttribute("errorMessage", "Invalid credentials");
//            return "login.jsp";
//        }
//    }
}
