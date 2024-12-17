package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import static ehu.java.interpoldemo.constants.PageNameConstant.ADD_REQUEST;

public class AddRequestFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return ADD_REQUEST;
    }
}
