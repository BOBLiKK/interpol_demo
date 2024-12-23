package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.Request;
import ehu.java.interpoldemo.service.RequestService;
import ehu.java.interpoldemo.service.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;


public class ViewRequestListCommand implements Command {

    private final RequestService requestService = new RequestServiceImpl();
    private static final Logger logger = LogManager.getLogger(ViewRequestListCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try{
            List<Request> requests = requestService.findAllRequests();
            request.setAttribute(REQUESTS, requests);
            return REQUEST_LIST;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
