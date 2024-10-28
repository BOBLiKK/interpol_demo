package ehu.java.interpoldemo.dao;
import ehu.java.interpoldemo.model.Criminal;
import java.util.List;

public interface CriminalDao {
    void saveCriminal(Criminal criminal);
    List<Criminal> getAllCriminals();
}
