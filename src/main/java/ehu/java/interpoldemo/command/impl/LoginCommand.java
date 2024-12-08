package ehu.java.interpoldemo.command.impl;
import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.service.UserService;
import ehu.java.interpoldemo.service.impl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static ehu.java.interpoldemo.constants.PageAttributeConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;

public class LoginCommand implements Command {
    private final UserService userService = new UserServiceImpl();
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String page;
        try{
            if (userService.authenticate(login, password)) {
               HttpSession session = request.getSession(true);
                String role = userService.findRole(login);
                session.setAttribute(USER, login);
                session.setAttribute(ROLE, role);

                Cookie userCookie = new Cookie(USER, login);
                Cookie roleCookie = new Cookie(ROLE, role);
                userCookie.setMaxAge(3600);
                roleCookie.setMaxAge(3600);
                HttpServletResponse response = (HttpServletResponse) request.getAttribute(RESPONSE);
                response.addCookie(userCookie);
                response.addCookie(roleCookie);

                if (role.equals(ADMIN)) {
                    page = ADMIN_DASHBOARD;
                }else{
                    page = MAIN_PAGE;
                }
            } else {
                request.setAttribute(MESSAGE, INCORRECT_LOGIN_OR_PASSWORD);
                page = LOGIN_PAGE;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
