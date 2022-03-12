package com.epam.webappfinal.command;

import com.epam.webappfinal.entity.User;
import com.epam.webappfinal.exception.ServiceException;
import com.epam.webappfinal.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FillUpMoneyCommand implements Command {

    private final UserService userService;

    public FillUpMoneyCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String cardNumber = req.getParameter("card");
        String money = req.getParameter("money");
        boolean isSuccess = userService.isDeposited(user, cardNumber, money);
        CommandResult result;
        if (!isSuccess) {
            session.setAttribute("errorFillUp", "Invalid input data");
            result = CommandResult.redirect("controller?command=mainPage#popup");
        } else {
            session.setAttribute("errorFillUp", null);
            result = CommandResult.redirect("controller?command=mainPage");
        }
        return result;
    }
}
