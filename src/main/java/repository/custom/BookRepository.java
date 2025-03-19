package repository.custom;

import entity.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();

    boolean update(Book book);

    boolean save(Book book);

    void search(Integer integer);

    boolean delete(Object integer);
}
