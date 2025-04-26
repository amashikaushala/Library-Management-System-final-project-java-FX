package service.custom;

import dto.custom.BookDTO;
import service.CrudService;
import util.exception.custom.BookException;

public interface BookService extends CrudService<BookDTO,Integer> {


    boolean delete(Integer integer) throws BookException;
}
