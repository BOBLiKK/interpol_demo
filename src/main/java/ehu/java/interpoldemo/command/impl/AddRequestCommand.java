package ehu.java.interpoldemo.command.impl;

import ehu.java.interpoldemo.command.Command;
import ehu.java.interpoldemo.exception.CommandException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.Criminal;
import ehu.java.interpoldemo.model.Request;
import ehu.java.interpoldemo.model.Status;
import ehu.java.interpoldemo.service.RequestService;
import ehu.java.interpoldemo.service.impl.RequestServiceImpl;
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
public class AddRequestCommand implements Command {

    private final RequestService requestService = new RequestServiceImpl();
    private static final Logger logger = LogManager.getLogger(AddRequestCommand.class);

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
        byte[] image = null;
        String comment = request.getParameter(COMMENT);

        try{
            Part filePart = request.getPart(IMAGE); // Получение файла из запроса
            if (filePart != null) {
                try (InputStream inputStream = filePart.getInputStream()) {
                    image = inputStream.readAllBytes();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            int userId = (int) request.getSession().getAttribute(USER_ID);
            Criminal criminal = new Criminal.CriminalBuilder(name, surname)
                    .setDateOfBirth(dateOfBirth)
                    .setCitizenship(citizenship)
                    .setDescription(description)
                    .setReward(Double.parseDouble(reward))
                    .setImage(image)
                    .build();

            Request newRequest = new Request.RequestBuilder()
                    .setUserId(userId)
                    .setCriminal(criminal)
                    .setComment(comment)
                    .setStatus(Status.PENDING)
                    .build();

            if(requestService.createRequest(newRequest)){
                request.setAttribute(MESSAGE, REQUEST_ADDED_SUCCESFULLY);
            }else{
                request.setAttribute(MESSAGE, REQUEST_NOT_ADDED);
            }

        }catch(ServiceException | IOException | ServletException e){
            throw new CommandException(e.getMessage() + e.getCause() + e.getStackTrace());
        }
        return MAIN_PAGE;
    }
}
