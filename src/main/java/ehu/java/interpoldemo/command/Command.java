package ehu.java.interpoldemo.command;

import ehu.java.interpoldemo.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String execute (HttpServletRequest request) throws CommandException;
    default void refresh(){}
}
