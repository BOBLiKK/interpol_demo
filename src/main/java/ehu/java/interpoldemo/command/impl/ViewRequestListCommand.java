package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;


//todo

public class ViewRequestListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return "";
    }
}
