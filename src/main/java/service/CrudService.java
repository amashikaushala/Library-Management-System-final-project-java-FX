package service;

import util.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CrudService<T,ID>{
    boolean add(T t)throws ServiceException;
    boolean update(T t)throws ServiceException;
    boolean delete(T t)throws ServiceException;
    Optional<T>search(ID id);
    List<T> getAll()throws ServiceException;
}
