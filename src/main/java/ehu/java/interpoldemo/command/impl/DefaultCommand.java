package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import static ehu.java.interpoldemo.command.CommandConstants.INDEX_PAGE;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return INDEX_PAGE;
    }
}
