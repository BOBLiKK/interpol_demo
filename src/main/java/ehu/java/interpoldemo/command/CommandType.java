package ehu.java.interpoldemo.command;

import ehu.java.interpoldemo.command.impl.*;

public enum CommandType {
    REGISTER_USER(new RegisterUserCommand()),
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
        CommandType current  = CommandType.DEFAULT;
        if(commandStr!=null && !commandStr.isEmpty()){
            try{
                current = CommandType.valueOf(commandStr.toUpperCase());
            }catch (IllegalArgumentException e){
                current = CommandType.DEFAULT;
            }
        }
        return current.getCommand();
    }
}