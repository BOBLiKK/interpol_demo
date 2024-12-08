package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import static ehu.java.interpoldemo.constants.PageNameConstant.ADD_CRIMINAL;

public class AddCriminalFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return ADD_CRIMINAL;
    }
}
