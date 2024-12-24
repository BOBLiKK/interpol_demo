package ehu.java.interpoldemo.service.impl;

import ehu.java.interpoldemo.dao.RequestDao;
import ehu.java.interpoldemo.dao.impl.RequestDaoImpl;
import ehu.java.interpoldemo.exception.DaoException;
import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.Request;
import ehu.java.interpoldemo.service.RequestService;

import java.util.List;

public class RequestServiceImpl implements RequestService {
    private final RequestDao requestDao = new RequestDaoImpl();
    private final RequestDaoImpl requestDaoImpl = new RequestDaoImpl();

    public boolean createRequest(Request request) throws ServiceException {
        try {
            return requestDaoImpl.insert(request);
        } catch (DaoException e) {
            throw new ServiceException("Error creating request", e);
        }
    }

    public boolean updateRequestStatus(int requestId, String status) throws ServiceException {
        try {
            return requestDao.updateStatus(requestId, status);
        } catch (DaoException e) {
            throw new ServiceException("Error updating request status", e);
        }
    }

    public Request findRequestById(int requestId) throws ServiceException {
        try {
            return requestDao.findById(requestId);
        } catch (DaoException e) {
            throw new ServiceException("Error retrieving request by ID", e);
        }
    }

    public List<Request> findRequestsByUserId(int userId) throws ServiceException {
        try {
            return requestDao.findByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Error retrieving requests for user", e);
        }
    }

    public List<Request> findAllRequests() throws ServiceException {
        try {
            return requestDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Error retrieving all requests", e);
        }
    }
}
