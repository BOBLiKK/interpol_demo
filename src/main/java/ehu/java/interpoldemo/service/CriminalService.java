package ehu.java.interpoldemo.service;

import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.Criminal;

import java.time.LocalDate;
import java.util.List;

public interface CriminalService {
    boolean addCriminal(String name, String surname, LocalDate dateOfBirth, String citizenship, String description, double reward) throws ServiceException;
    boolean editCriminal(Criminal criminal) throws ServiceException;
    boolean deleteCriminal(int id) throws ServiceException;
    List<Criminal> findAllCriminals() throws ServiceException;
    Criminal findCriminalById (int id) throws ServiceException;

}
