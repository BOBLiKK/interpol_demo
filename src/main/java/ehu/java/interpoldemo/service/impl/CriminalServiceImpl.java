package ehu.java.interpoldemo.service.impl;

import ehu.java.interpoldemo.dao.CriminalDao;
import ehu.java.interpoldemo.dao.impl.CriminalDaoImpl;
import ehu.java.interpoldemo.model.Criminal;
import ehu.java.interpoldemo.service.CriminalService;
import java.util.List;

public class CriminalServiceImpl implements CriminalService {
    private final CriminalDao criminalDao;
    public CriminalServiceImpl() {
        this.criminalDao = new CriminalDaoImpl();
    }


    //todo
    @Override
    public boolean addCriminal(Criminal criminal) {
        if (criminal.getName() == null || criminal.getName().isEmpty()) {
            //throw new IllegalArgumentException("Criminal name cannot be null or empty");
            return false;
        }
        criminalDao.saveCriminal(criminal);
        return true;

    }

    public List<Criminal> findAllCriminals() {
        return criminalDao.findAllCriminals();
    }
}
