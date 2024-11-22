package ehu.java.interpoldemo.controller;

import java.io.*;

import ehu.java.interpoldemo.exception.CommandException;
import ehu.java.interpoldemo.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static ehu.java.interpoldemo.constants.PageAttributeConstant.MESSAGE;
import static ehu.java.interpoldemo.constants.PageNameConstant.ERROR_500_PAGE;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.COMMAND;


@WebServlet(name = "controller", value = "/controller")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);


    public void init() {
        logger.info("Controller initialized");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            String commandStr = request.getParameter(COMMAND);
            Command command = CommandType.define(commandStr);
            String page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
        } catch (CommandException e) {
            logger.error("Command execution failed: ", e);
            request.setAttribute(MESSAGE, e.getMessage());
            request.getRequestDispatcher(ERROR_500_PAGE).forward(request, response);
        }
    }
        public void destroy() {
        //todo destroypool
        logger.info("Controller destroyed");
    }
}