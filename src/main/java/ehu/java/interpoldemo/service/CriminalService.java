package ehu.java.interpoldemo.service;

import ehu.java.interpoldemo.model.Criminal;

import java.util.List;

public interface CriminalService {
    boolean addCriminal(Criminal criminal);
    List<Criminal> findAllCriminals();
}
