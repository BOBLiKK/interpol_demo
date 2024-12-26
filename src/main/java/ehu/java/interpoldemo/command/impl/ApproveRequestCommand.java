package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.dao.*;
import ehu.java.interpoldemo.dao.impl.*;
import ehu.java.interpoldemo.model.Criminal;
import ehu.java.interpoldemo.model.Request;
import ehu.java.interpoldemo.service.CriminalService;
import ehu.java.interpoldemo.service.RequestService;
import ehu.java.interpoldemo.service.impl.CriminalServiceImpl;
import ehu.java.interpoldemo.service.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import ehu.java.interpoldemo.exception.*;

import static ehu.java.interpoldemo.constants.PageAttributeConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;

//todo

public class ApproveRequestCommand implements Command {

    private final RequestService requestService = new RequestServiceImpl();
    private final CriminalService criminalService = new CriminalServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            int requestId = Integer.parseInt(request.getParameter(ID));
            Request pendingRequest = requestService.findRequestById(requestId);
            Criminal newCriminal = pendingRequest.getCriminal();
            criminalService.addCriminal(newCriminal);
            if(requestService.updateRequestStatus(requestId, APPROVED)){
                request.setAttribute(MESSAGE,STATUS_CHANGED );
            }else{
                request.setAttribute(MESSAGE,STATUS_NOT_CHANGED );
            }
            return ADMIN_DASHBOARD;
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Failed to approve request ", e);
        }
    }
}
