package com.epam.webappfinal.controller.command;

import com.epam.webappfinal.controller.service.UserRegistrationServiceImpl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.openqa.selenium.remote.service.DriverService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    private final UserRegistrationServiceImpl userRegistrationService;

    public RegistrationCommand(UserRegistrationServiceImpl userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String confirmedPassword = req.getParameter("confirmedPassword");

        if (userRegistrationService.login(name, surname, login, password, confirmedPassword)) {
            //req.getSession().setAttribute("user", "admin");

            //Предоставлять начальные значения страницы

            return "main.jsp";
        } else {
            req.setAttribute("errorRegistrationMessage", "Invalid credentials");
            return "registration.jsp";
        }
    }
}
