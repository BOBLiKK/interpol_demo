package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import static ehu.java.interpoldemo.command.CommandConstants.INDEX_PAGE;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return INDEX_PAGE;
    }
}
