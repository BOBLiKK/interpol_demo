package ehu.java.interpoldemo.service.impl;

import ehu.java.interpoldemo.dao.CriminalDao;
import ehu.java.interpoldemo.dao.impl.CriminalDaoImpl;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.Criminal;
import ehu.java.interpoldemo.service.CriminalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDate;
import java.util.List;

public class CriminalServiceImpl implements CriminalService {


    private final CriminalDaoImpl criminalDaoImpl = new CriminalDaoImpl();
    private final CriminalDao criminalDao = new CriminalDaoImpl();
    private static final Logger logger = LogManager.getLogger(CriminalServiceImpl.class);

    @Override
    public boolean addCriminal(String name, String surname, LocalDate dateOfBirth, String citizenship, String description, double reward) throws ServiceException {
        boolean addedSuccessfully = false;
        try{
            Criminal criminal = new Criminal.CriminalBuilder(name, surname).
                    setDateOfBirth(dateOfBirth).
                    setCitizenship(citizenship).
                    setDescription(description).
                    setReward(reward).build();
            addedSuccessfully = criminalDaoImpl.insert(criminal);
        } catch (DaoException e){
            logger.error("Error saving criminal on service level");
            throw new ServiceException(e);
        }
        return addedSuccessfully;
    }

    @Override
    public boolean editCriminal(Criminal criminal) throws ServiceException {
        boolean editedSuccessfully = false;
        try{
            editedSuccessfully = criminalDaoImpl.update(criminal);
        } catch (DaoException e){
            logger.error("Error editing criminal on service level");
            throw new ServiceException(e);
        }
        return editedSuccessfully;
    }

    @Override
    public boolean deleteCriminal(int id) throws ServiceException {
        boolean deletedSuccessfully = false;
        try{
            Criminal criminal = findCriminalById(id);
            deletedSuccessfully = criminalDaoImpl.delete(criminal);
        } catch(DaoException e){
            logger.error("Error deleting criminal on service level");
            throw new ServiceException(e);
        }
        return deletedSuccessfully;
    }



    public List<Criminal> findAllCriminals() throws ServiceException {
       try{
           List<Criminal> criminals = criminalDao.findAll();
           return criminals;
       }catch (DaoException e){
           throw new ServiceException(e);
       }
    }

    @Override
    public Criminal findCriminalById(int id) throws ServiceException {
        try{
            return criminalDao.findCriminalById(id);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
