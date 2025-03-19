package service.custom.impl;

import dto.BookDTO;
import entity.Book;
import repository.custom.BookRepository;
import repository.impl.BookRepositoryIMPL;
import service.custom.BookException;
import service.custom.BookService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BookServiceIMPL implements BookService {

    private final BookRepository repository = new BookRepositoryIMPL();

    @Override
    public boolean add(BookDTO bookDTO) throws BookException {
        Book book = convertDtoToEntity(bookDTO);
        try {
            return  repository.save(book);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new BookException("ID Already Exists - Cannot Save.", e);
            } else if (e.getErrorCode() == 1406) {
                throw new BookException("Data is Too Large for a Field.", e);
            } else {
                throw new BookException("Database Error - " + e.getMessage(), e);
            }
        } catch (Exception e) {

            
            throw new BookException("Failed to add book", e);
        }
    }

    @Override
    public boolean update(BookDTO bookDTO) throws BookException {
        Book book = convertDtoToEntity(bookDTO);
        try {
            return repository.update(book);

        } catch (SQLException|ClassNotFoundException e) {
            throw new BookException("Failed to update book", e);
        }
    }

    @Override
    public boolean delete(BookDTO bookDTO) throws BookException {
        try {
            Object integer = null;
            return repository.delete(integer);
        } catch (SQLException|ClassNotFoundException e) {
            throw new BookException("Failed to delete book", e);
        }
    }

    @Override
    public Optional<BookDTO> search(Integer integer) {
        repository.search(integer);
        return Optional.empty();
    }

    @Override
    public List<BookDTO> getAll() throws BookException {
        try {
            List<Book> books = repository.findAll();
            return books.stream().map(this::convertEntityToDto).toList();
        } catch (Exception e) {
            throw new BookException("Failed to retrieve book list", e);
        }
    }

    private Book convertDtoToEntity(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        book.setMainCategoryId(dto.getMainCategoryId());
        book.setPublisherId(dto.getPublisherId());
        return book;
    }

    private BookDTO convertEntityToDto(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPrice(book.getPrice());
        dto.setMainCategoryId(book.getMainCategoryId());
        dto.setPublisherId(book.getPublisherId());
        return dto;
    }
}
