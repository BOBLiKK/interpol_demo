package ehu.java.interpoldemo.command.impl;
import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.service.UserService;
import ehu.java.interpoldemo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import static ehu.java.interpoldemo.command.CommandConstants.*;
public class LoginCommand implements Command {
    private final UserService userService = new UserServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String page;

        if (userService.authenticate(login, password)) {
            request.setAttribute("user", login);
            page = MAIN_PAGE;
        } else {
            request.setAttribute("errorLoginPassMessage", "Incorrect login or password");
            page = LOGIN_PAGE;
        }
        return page;
    }
}
