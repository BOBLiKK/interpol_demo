package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.model.Criminal;
import ehu.java.interpoldemo.service.CriminalService;
import ehu.java.interpoldemo.service.impl.CriminalServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import static ehu.java.interpoldemo.command.CommandConstants.CRIMINAL_LIST_PAGE;

public class AddCriminalCommand implements Command {
    private final CriminalService criminalService = new CriminalServiceImpl();
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String citizenship = request.getParameter("citizenship");
        String description = request.getParameter("description");
        double reward = Double.parseDouble(request.getParameter("reward"));
        boolean isArrested = Boolean.parseBoolean(request.getParameter("isArrested"));

        Criminal criminal = new Criminal();
        criminal.setName(name);
        criminal.setSurname(surname);
        criminal.setDateOfBirth(dateOfBirth);
        criminal.setCitizenship(citizenship);
        criminal.setDescription(description);
        criminal.setReward(reward);
        criminal.setArrested(isArrested);
        criminalService.addCriminal(criminal);
        List<Criminal> criminals = criminalService.getAllCriminals();
        request.setAttribute("criminals", criminals);
        return CRIMINAL_LIST_PAGE;
    }
}
