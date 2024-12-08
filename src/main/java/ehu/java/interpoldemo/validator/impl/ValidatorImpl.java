package ehu.java.interpoldemo.validator.impl;

import ehu.java.interpoldemo.validator.Validator;

import java.util.regex.Pattern;

public class ValidatorImpl implements Validator {

    private static final String LOGIN_PATTERN = "^(?=.*[a-zA-Z])[a-zA-Z]{4,10}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*!])[A-Za-z\\d@#$%^&*!]{8,12}$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    @Override
    public boolean isValidLogin(String login) {
        if (login == null) {
            return false;
        }
        String sanitizedString = sanitizeString(login);
        return Pattern.matches(LOGIN_PATTERN, sanitizedString);
    }

    @Override
    public boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        String sanitizedString = sanitizeString(password);
        return Pattern.matches(PASSWORD_PATTERN, sanitizedString);
    }

    @Override
    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String sanitizedString = sanitizeString(email);
        return Pattern.matches(EMAIL_PATTERN, sanitizedString);
    }


    private String sanitizeString(String str) {
        return str.replaceAll("[<>]", " ");
    }
}
