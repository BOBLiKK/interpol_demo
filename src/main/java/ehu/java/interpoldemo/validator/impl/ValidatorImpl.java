package ehu.java.interpoldemo.validator.impl;

import ehu.java.interpoldemo.validator.Validator;

import java.util.regex.Pattern;

public class ValidatorImpl implements Validator {

    private static final String LOGIN_PATTERN = "^(?=.*[a-zA-Z])[a-zA-Z]{4,10}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,12}$";
    @Override
    public boolean isValidLogin(String login) {
        if (login == null) {
            return false;
        }
        String sanitizedString = login.replaceAll("[<>]", " ");
        return Pattern.matches(LOGIN_PATTERN, sanitizedString);
    }

    @Override
    public boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        String sanitizedString = password.replaceAll("[<>]", " ");
        return Pattern.matches(PASSWORD_PATTERN, sanitizedString);
    }
}
