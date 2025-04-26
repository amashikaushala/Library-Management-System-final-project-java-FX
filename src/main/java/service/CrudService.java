package service;

import dto.SuperDTO;
import entity.SuperEntity;
import service.custom.BookException;
import util.exception.ServiceException;
import util.exception.custom.MemberException;

import java.util.List;
import java.util.Optional;

public interface CrudService<T extends SuperDTO,ID>{
    boolean save(T t)throws ServiceException;
    boolean update(T t)throws ServiceException;
    boolean delete(T t)throws ServiceException;
    Optional<T>search(ID id) throws ServiceException ;
    List<T> getAll()throws ServiceException;
}
