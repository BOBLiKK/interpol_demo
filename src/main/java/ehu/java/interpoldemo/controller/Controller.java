package ehu.java.interpoldemo.controller;

import java.io.*;
import ehu.java.interpoldemo.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.command.CommandType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import static ehu.java.interpoldemo.constants.ControllerConstant.*;
import static ehu.java.interpoldemo.constants.PageAttributeConstant.*;
import static ehu.java.interpoldemo.constants.PageNameConstant.ERROR_500_PAGE;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

@WebServlet(name = CONTROLLER, value = CONTROLLER_PATH)
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    public void init() {
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
            response.setContentType(CONTENT_TYPE_TEXT_HTML);
            String commandStr = request.getParameter(COMMAND);
            request.setAttribute(RESPONSE, response);
            Command command = CommandType.define(commandStr);
            String page = command.execute(request);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(USER) || cookie.getName().equals(ROLE) || cookie.getName().equals(LANG)) {
                        logger.info("Cookie Name: " + cookie.getName()
                                + ", Cookie Value: " + cookie.getValue());
                    }
                }
            }
            request.getRequestDispatcher(page).forward(request, response);
        } catch (CommandException e) {
            logger.error("Command execution failed: ", e);
            request.setAttribute(MESSAGE, e.getMessage());
            request.getRequestDispatcher(ERROR_500_PAGE).forward(request, response);
        }
    }

    public void destroy() {
    }
}