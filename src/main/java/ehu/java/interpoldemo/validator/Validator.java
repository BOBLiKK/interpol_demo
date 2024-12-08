package ehu.java.interpoldemo.validator;

public interface Validator {
    boolean isValidLogin(String login);
    boolean isValidPassword(String password);
    boolean isValidEmail(String email);
}
