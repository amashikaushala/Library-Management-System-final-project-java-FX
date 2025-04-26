package repository.custom.impl;

import entity.custom.Book;
import repository.custom.BookRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryIMPL implements BookRepository {
    @Override
    public boolean save(Book book) throws SQLException, ClassNotFoundException {
        try {
            String sql = "INSERT INTO book (id, name, isbn, price, author, publisher_id, main_category_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            boolean execute = CrudUtil.execute(sql, book.getId(), book.getName(), book.getIsbn(),
                    book.getPrice(), book.getAuthor(), book.getPublisherId(), book.getMainCategoryId());
            return execute;
        } catch (Exception e) {
            System.out.println("Error saving book: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean update(Book book) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE BOOK set name=?,isbn=?,price=?,publisher_id=?" +
                "main_category_id=? where id=?";
        boolean execute = CrudUtil.execute(sql, book.getIsbn(), book.getPrice(), book.getPublisherId(), book.getMainCategoryId(), book.getId());
        return execute;

    }


    @Override
    public Optional<Book> search(Integer integer) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM book WHERE id = ?";
        ResultSet rs = CrudUtil.execute(sql, integer);
        if (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt(1));
            book.setName(rs.getString(2));
            book.setIsbn(rs.getString(3));
            book.setPrice(rs.getDouble(4));
            book.setPublisherId(rs.getInt(5));
            book.setMainCategoryId(rs.getInt(6));
            return Optional.of(book);
        }
        return Optional.empty();
    }


    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM book WHERE id = ?";
        boolean execute = CrudUtil.execute(sql, integer);
        return execute;
    }


    @Override
    public List<Book> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM book";
        ResultSet rs = CrudUtil.execute(sql);
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt(1));
            book.setName(rs.getString(2));
            book.setIsbn(rs.getString(3));
            book.setPrice(rs.getDouble(4));
            book.setPublisherId(rs.getInt(5));
            book.setMainCategoryId(rs.getInt(6));
            books.add(book);
        }
        return books;
    }
}
