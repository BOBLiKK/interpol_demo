package ehu.java.interpoldemo.command.impl;
import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.service.UserService;
import ehu.java.interpoldemo.service.impl.UserServiceImpl;
import ehu.java.interpoldemo.validator.impl.ValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;
import static ehu.java.interpoldemo.constants.PageAttributeConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

public class RegisterUserWithEmailCommand implements Command {
    private final UserService userService = new UserServiceImpl();
    private static final Logger logger = LogManager.getLogger(RegisterUserWithEmailCommand.class);
    private final ValidatorImpl validator = new ValidatorImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String page;

        Map<String, String> validationErrors = validateInput(email, password);

        if (!validationErrors.isEmpty()) {
            request.setAttribute(VALIDATION_ERRORS, validationErrors);
            return REGISTER_USER;
        }

        try {
            boolean registrationSuccessful = userService.registerUserWithEmail(email, password);

            if (registrationSuccessful) {
                request.setAttribute(MESSAGE, REGISTRATION_SUCCESSFUL);
                page = LOGIN_PAGE;
            } else {
                page = REGISTER_USER;
            }

        } catch (ServiceException e) {
            throw new CommandException("Registration with email failed.", e);
        }
        return page;
    }

    private Map<String, String> validateInput(String email, String password) {
        Map<String, String> errors = new HashMap<>();
        if (!validator.isValidEmail(email)) {
            errors.put(EMAIL, INVALID_EMAIL_VALUE);
        }
        if (!validator.isValidPassword(password)) {
            errors.put(PASSWORD, INVALID_PASSWORD_VALUE);
        }
        return errors;
    }
}
