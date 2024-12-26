package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.service.CriminalService;
import ehu.java.interpoldemo.service.impl.CriminalServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import static ehu.java.interpoldemo.constants.PageAttributeConstant.*;
import static ehu.java.interpoldemo.constants.PageNameConstant.*;
import static ehu.java.interpoldemo.constants.ParameterNameConstant.*;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,  // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class AddCriminalCommand implements Command {
    private final CriminalService criminalService = new CriminalServiceImpl();
    private static final Logger logger = LogManager.getLogger(AddCriminalCommand.class);
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
        double reward = Double.parseDouble(request.getParameter(REWARD));
        byte[] image = null;

        try {
            Part filePart = request.getPart(IMAGE); // Получение файла из запроса
            if (filePart != null) {
                logger.debug("File name: " + filePart.getSubmittedFileName());
                logger.debug("File size: " + filePart.getSize());
                try (InputStream inputStream = filePart.getInputStream()) {
                    image = inputStream.readAllBytes();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (criminalService.addCriminal(name, surname, dateOfBirth, citizenship, description, reward, image)) {
                request.setAttribute(MESSAGE, CRIMINAL_ADDED_SUCCESSFULLY);
            } else {
                request.setAttribute(MESSAGE, CRIMINAL_NOT_ADDED);
            }
        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException("Error adding criminal to the database.", e);
        }
        return ADMIN_DASHBOARD;
    }
}
