package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.model.Criminal;
import ehu.java.interpoldemo.service.CriminalService;
import ehu.java.interpoldemo.service.impl.CriminalServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

public class AddCriminalCommand implements Command {
    private final CriminalService criminalService = new CriminalServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter(DATE_OF_BIRTH));
        String citizenship = request.getParameter(CITIZENSHIP);
        String description = request.getParameter(DESCRIPTION);
        double reward = Double.parseDouble(request.getParameter(REWARD));
        boolean isArrested = Boolean.parseBoolean(request.getParameter(IS_ARRESTED));

        Criminal criminal = new Criminal();
        criminal.setName(name);
        criminal.setSurname(surname);
        criminal.setDateOfBirth(dateOfBirth);
        criminal.setCitizenship(citizenship);
        criminal.setDescription(description);
        criminal.setReward(reward);
        criminal.setArrested(isArrested);
        criminalService.addCriminal(criminal);
        List<Criminal> criminals = criminalService.findAllCriminals();
        request.setAttribute(CRIMINALS, criminals);
        return CRIMINAL_LIST_PAGE;
    }
}
