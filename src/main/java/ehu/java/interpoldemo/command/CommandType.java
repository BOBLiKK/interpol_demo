package ehu.java.interpoldemo.command;

import ehu.java.interpoldemo.command.impl.*;
import java.util.logging.Logger;

public enum CommandType {

    REGISTER_USER(new RegisterUserCommand()),
    REGISTER_USER_WITH_EMAIL(new RegisterUserWithEmailCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    ADD_CRIMINAL(new AddCriminalCommand()),
    EDIT_CRIMINAL(new EditCriminalCommand()),
    EDIT_CRIMINAL_FORM(new EditCriminalFormCommand()),
    DELETE_CRIMINAL(new DeleteCriminalCommand()),
    ADD_CRIMINAL_FORM (new AddCriminalFormCommand()),
    VIEW_CRIMINAL_LIST (new ViewCriminalListCommand()),
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