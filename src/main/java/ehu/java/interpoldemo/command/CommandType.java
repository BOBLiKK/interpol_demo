package ehu.java.interpoldemo.command;

import ehu.java.interpoldemo.command.impl.*;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    ADD_CRIMINAL(new AddCriminalCommand()),
    DEFAULT(new DefaultCommand());
    Command command;

    public Command getCommand() {
        return command;
    }
    CommandType(Command command) {
        this.command = command;
    }
    public static Command define(String commandStr){
        CommandType current  = CommandType.valueOf(commandStr.toUpperCase()); //todo
        return current.command;
    }
}