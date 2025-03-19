package repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository <T,ID>{
    boolean save(T t)throws SQLException,ClassNotFoundException;
    boolean update(T t)throws  SQLException,ClassNotFoundException;
    Optional<T>search(ID id)throws  SQLException,ClassNotFoundException;
    boolean delete(ID id)throws  SQLException,ClassNotFoundException;
    List<T>getAll() throws  SQLException,ClassNotFoundException;
}
