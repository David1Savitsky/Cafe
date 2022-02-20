package com.epam.webappfinal.controller.command;

import com.epam.webappfinal.controller.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (userService.login(login, password)) {
            //req.getSession().setAttribute("user", "admin");

            //Подгружать с БД его данные

            return "main.jsp";
        } else {
            req.setAttribute("errorMessage", "Invalid credentials");
            return "login.jsp";
        }
    }
}
