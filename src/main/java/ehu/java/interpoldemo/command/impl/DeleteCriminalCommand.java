package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.service.CriminalService;
import ehu.java.interpoldemo.service.impl.CriminalServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static ehu.java.interpoldemo.constants.PageAttributeConstant.*;
import static ehu.java.interpoldemo.constants.PageNameConstant.ADMIN_DASHBOARD;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.ID;

public class DeleteCriminalCommand implements Command {
    private final CriminalService criminalService = new CriminalServiceImpl();
    private static final Logger logger = LogManager.getLogger(DeleteCriminalCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try{
            int criminalId = Integer.parseInt(request.getParameter(ID));
            if(criminalService.deleteCriminal(criminalId)){
                request.setAttribute(MESSAGE, CRIMINAL_DELETED);
            }else{
                request.setAttribute(MESSAGE, CRIMINAL_NOT_DELETED);
            }
        }catch(ServiceException e){
            throw new CommandException(e.getMessage());
        }
        return ADMIN_DASHBOARD;
    }
}
