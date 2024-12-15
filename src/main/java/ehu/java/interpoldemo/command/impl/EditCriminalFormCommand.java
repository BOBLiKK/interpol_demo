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
import static ehu.java.interpoldemo.constants.PageNameConstant.EDIT_CRIMINAL;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.CRIMINAL;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.ID;

public class EditCriminalFormCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditCriminalFormCommand.class);
    private final CriminalService criminalService = new CriminalServiceImpl();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String idParam = request.getParameter(ID);
        if (idParam == null || idParam.isEmpty()) {
            throw new CommandException("Criminal ID is missing. ");
        }
        try {
            int criminalId = Integer.parseInt(idParam);
            Criminal criminal = criminalService.findCriminalById(criminalId);
            if (criminal == null) {
                throw new CommandException("Criminal not found for ID: " + criminalId);
            }
            request.setAttribute(CRIMINAL, criminal);
        } catch (NumberFormatException e) {
            throw new CommandException("Invalid criminal ID format. ", e);
        } catch (ServiceException e) {
            throw new CommandException("Failed to fetch criminal data. ", e);
        }
        return EDIT_CRIMINAL;
    }
}
