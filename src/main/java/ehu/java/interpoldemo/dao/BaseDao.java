package ehu.java.interpoldemo.dao;

import ehu.java.interpoldemo.model.AbstractModel;
import ehu.java.interpoldemo.exception.DaoException;

import java.util.List;

//todo
public abstract class BaseDao <T extends AbstractModel>{

    public abstract boolean insert(T model) throws DaoException;
    public abstract boolean update(T model) throws DaoException;
    public abstract boolean delete(T model) throws DaoException;
    public abstract List<T> findAll() throws DaoException;
}
