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

    @Override
    public void addCriminal(Criminal criminal) {
        if (criminal.getName() == null || criminal.getName().isEmpty()) {
            throw new IllegalArgumentException("Criminal name cannot be null or empty");
        }
        criminalDao.saveCriminal(criminal);
    }

    @Override
    public List<Criminal> getAllCriminals() {
        return criminalDao.getAllCriminals();
    }
}
