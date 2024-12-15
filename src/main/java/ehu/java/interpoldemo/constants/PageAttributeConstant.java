package ehu.java.interpoldemo.constants;

public class PageAttributeConstant {

    private PageAttributeConstant() {
    }

    public static final String MESSAGE = "message";
    public static final String VALIDATION_ERRORS = "validationErrors";

    //Action messages
    public static final String INCORRECT_LOGIN_OR_PASSWORD = "Incorrect login or password";
    public static final String REGISTRATION_SUCCESSFUL = "Registration successful. Please log in";
    public static final String USER_ALREADY_EXISTS = "User already exists.";
    public static final String CRIMINAL_ADDED_SUCCESSFULLY = "Criminal successfully added!";
    public static final String CRIMINAL_NOT_ADDED = "Failed to add criminal.";
    public static final String INVALID_LOGIN_VALUE = "Invalid login. Must be 4-10 alphabetic characters.";
    public static final String INVALID_PASSWORD_VALUE = "Invalid password. " +
            "Must be 8-12 characters, including upper and lower case, " +
            "a number, and a special character.";
    public static final String INVALID_EMAIL_VALUE = "Invalid email value";

    public static final String CRIMINAL_UPDATED = "Criminal updated successfully.";
    public static final String CRIMINAL_NOT_UPDATED = "Failed to update criminal.";

    public static final String CRIMINAL_DELETED = "Criminal was deleted successfully.";
    public static final String CRIMINAL_NOT_DELETED = "Criminal was not deleted.";

    public static final String RESPONSE = "response";
}
