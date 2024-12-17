package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.Criminal;
import ehu.java.interpoldemo.model.Request;
import ehu.java.interpoldemo.model.Status;
import ehu.java.interpoldemo.service.RequestService;
import ehu.java.interpoldemo.service.impl.RequestServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import static ehu.java.interpoldemo.constants.PageAttributeConstant.*;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

//todo

public class AddRequestCommand implements Command {

    private final RequestService requestService = new RequestServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        int day = Integer.parseInt(request.getParameter(DAY));
        int month = Integer.parseInt(request.getParameter(MONTH));
        int year = Integer.parseInt(request.getParameter(YEAR));
        LocalDate dateOfBirth = LocalDate.of(year, month, day);
        String citizenship = request.getParameter(CITIZENSHIP);
        String description = request.getParameter(DESCRIPTION);
        String reward = request.getParameter(REWARD);
        String comments = request.getParameter(COMMENT);
        try {
            int userId = (int) request.getSession().getAttribute(USER_ID);

            Criminal criminal = new Criminal.CriminalBuilder(name, surname)
                    .setDateOfBirth(dateOfBirth)
                    .setCitizenship(citizenship)
                    .setDescription(description)
                    .setReward(Double.parseDouble(reward))
                    .build();

            Request newRequest = new Request.RequestBuilder()
                    .setUserId(userId)
                    .setCriminal(criminal)
                    .setComment(comments)
                    .setStatus(Status.PENDING)
                    .build();

            if(requestService.createRequest(newRequest)){
                request.setAttribute(MESSAGE, REQUEST_ADDED_SUCCESFULLY);
            }else{
                request.setAttribute(MESSAGE, REQUEST_NOT_ADDED);
            }
        } catch (NumberFormatException | ServiceException e) {
            throw new CommandException(e.getMessage());
        }
        return MAIN_PAGE;
    }
}
