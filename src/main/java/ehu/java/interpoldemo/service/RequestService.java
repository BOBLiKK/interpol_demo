package ehu.java.interpoldemo.service;

import ehu.java.interpoldemo.exception.ServiceException;
import ehu.java.interpoldemo.model.Request;
import java.util.List;

public interface RequestService {
    public boolean createRequest(Request request) throws ServiceException;
    public boolean updateRequestStatus(int requestId, String status) throws ServiceException;
    public Request getRequestById(int requestId) throws ServiceException;
    public List<Request> getRequestsByUserId(int userId) throws ServiceException;
    public List<Request> getAllRequests() throws ServiceException;
}
