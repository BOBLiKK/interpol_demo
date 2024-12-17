package ehu.java.interpoldemo.dao;

import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.model.Request;

import java.util.List;

public interface RequestDao {
    boolean updateStatus(int requestId, String status) throws DaoException;
    Request findById(int requestId) throws DaoException;
    List<Request> findByUserId(int userId) throws DaoException;
    List<Request> findAll() throws DaoException;
}
