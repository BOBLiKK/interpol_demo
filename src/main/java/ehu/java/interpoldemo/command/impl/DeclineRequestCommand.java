package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.*;
import ehu.java.interpoldemo.service.RequestService;
import ehu.java.interpoldemo.service.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;

public class DeclineRequestCommand implements Command {
    private final RequestService requestService = new RequestServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            int requestId = Integer.parseInt(request.getParameter(ID));
            requestService.updateRequestStatus(requestId, REJECTED);
            return ADMIN_DASHBOARD;
        } catch (ServiceException | NumberFormatException e) {
            throw new CommandException("Failed to reject request. ", e);
        }
    }
}
