package com.epam.webappfinal;

import com.epam.webappfinal.command.Command;
import com.epam.webappfinal.command.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String command = req.getParameter("command");
        CommandFactory commandFactory = new CommandFactory();
        Command action = commandFactory.createCommand(command);
        try{
            CommandResult result = action.execute(req, resp);
            dispatch(req, resp, result);
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getMessage());
            dispatch(req, resp, CommandResult.forward("/error.jsp"));
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CommandResult result) throws IOException, ServletException {
        String page = result.getPage();
        if (!result.isRedirect()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(page);
        }
    }

//    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String commandLine = req.getParameter("command");
//        CommandFactory commandFactory = new CommandFactory();
//        Command command = commandFactory.createCommand(commandLine);
//        String page = command.execute(req, resp);
//        req.getRequestDispatcher(page).forward(req, resp);
//    }
}
