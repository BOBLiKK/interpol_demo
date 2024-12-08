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
import java.util.List;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.CRIMINALS;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;

public class ViewCriminalListCommand implements Command {
    private final CriminalService criminalService = new CriminalServiceImpl();
    private static final Logger logger = LogManager.getLogger(ViewCriminalListCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            List<Criminal> criminals = criminalService.findAllCriminals();
            request.setAttribute(CRIMINALS, criminals);
            return ADMIN_CRIMINAL_LIST;
        } catch (ServiceException e) {
            logger.info("Error retrieving criminals list. ");
            throw new CommandException( e);
        }
    }



}