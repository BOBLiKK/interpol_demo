package ehu.java.interpoldemo.service;

import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.Request;
import java.util.List;

public interface RequestService {
    public boolean createRequest(Request request) throws ServiceException;
    public boolean updateRequestStatus(int requestId, String status) throws ServiceException;
    public Request findRequestById(int requestId) throws ServiceException;
    public List<Request> findRequestsByUserId(int userId) throws ServiceException;
    public List<Request> findAllRequests() throws ServiceException;
}
