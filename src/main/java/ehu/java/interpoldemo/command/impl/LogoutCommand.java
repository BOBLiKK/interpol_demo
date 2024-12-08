package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import static ehu.java.interpoldemo.constants.PageNameConstant.INDEX_PAGE;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
       HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return INDEX_PAGE;
    }
}
