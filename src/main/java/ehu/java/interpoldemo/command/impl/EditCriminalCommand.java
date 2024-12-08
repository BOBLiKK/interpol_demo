package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.Criminal;
import ehu.java.interpoldemo.service.CriminalService;
import ehu.java.interpoldemo.service.impl.CriminalServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import static ehu.java.interpoldemo.constants.PageNameConstant.ADMIN_DASHBOARD;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;
import static ehu.java.interpoldemo.constants.PageAttributeConstant.*;

public class EditCriminalCommand implements Command {

    private final CriminalService criminalService = new CriminalServiceImpl();
    private static final Logger logger = LogManager.getLogger(EditCriminalCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        try {
            int criminalId = Integer.parseInt(request.getParameter(ID));
            String name = request.getParameter(NAME);
            String surname = request.getParameter(SURNAME);
            String dateOfBirth = request.getParameter(DATE_OF_BIRTH_MODEL);
            String citizenship = request.getParameter(CITIZENSHIP);
            String description = request.getParameter(DESCRIPTION);
            double reward = Double.parseDouble(request.getParameter(REWARD));

            Criminal criminal = new Criminal.CriminalBuilder(name, surname)
                    .setId(criminalId)
                    .setDateOfBirth(LocalDate.parse(dateOfBirth))
                    .setCitizenship(citizenship)
                    .setDescription(description)
                    .setReward(reward)
                    .build();

            if (criminalService.editCriminal(criminal)) {
                request.setAttribute(MESSAGE, CRIMINAL_UPDATED);
            } else {
                request.setAttribute(MESSAGE, CRIMINAL_NOT_UPDATED);
            }

        } catch (NumberFormatException e) {
            logger.error("Invalid input format.", e);
            throw new CommandException("Invalid input format.", e);
        } catch (ServiceException e) {
            logger.error("Error updating criminal.", e);
            throw new CommandException("Failed to update criminal.", e);
        }
        return ADMIN_DASHBOARD;
    }
}
