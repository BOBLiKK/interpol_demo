package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.service.UserService;
import ehu.java.interpoldemo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import static ehu.java.interpoldemo.constants.PageAttributeConstant.*;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

public class RegisterUserCommand implements Command {

    private final UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String page;

        try{
            if (userService.registerUser(login, password))  {
                request.setAttribute(MESSAGE, REGISTRATION_SUCCESSFUL);
                page = LOGIN_PAGE;
            } else {
                request.setAttribute(MESSAGE, REGISTRATION_INVALID_SYMBOLS);
                page = REGISTER_USER;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;

    }
}