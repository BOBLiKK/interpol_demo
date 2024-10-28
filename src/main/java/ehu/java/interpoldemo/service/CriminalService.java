package ehu.java.interpoldemo.service;

import ehu.java.interpoldemo.model.Criminal;

import java.util.List;

public interface CriminalService {
    void addCriminal(Criminal criminal);
    List<Criminal> getAllCriminals();
}
