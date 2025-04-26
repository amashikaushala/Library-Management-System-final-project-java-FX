package service.custom.impl;

import dto.custom.BookDTO;
import entity.custom.Book;
import repository.custom.impl.BookRepositoryIMPL;
import service.custom.BookService;
import util.exception.ServiceException;
import util.exception.custom.BookException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceIMPL implements BookService {

    private final BookRepositoryIMPL repository = new BookRepositoryIMPL();


    @Override
    public boolean save(BookDTO bookDTO) throws BookException {
    Book book = convertDtoToEntity(bookDTO);
        try {
            repository.save(book);
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException)e).getErrorCode()==1062){
                throw new BookException("ID Already Exists-Cannot Save.");
            }else if (((SQLException)e).getErrorCode()==1406){
                String message = ((SQLException)e).getMessage();
                String[]s=message.split("");
                throw new BookException("Data is To Large For "+s[1]);

        }
            throw new BookException("Error Occured Developer",e);
        }
        return false;
    }

    @Override
    public boolean update(BookDTO bookDTO) throws BookException {
    Book book= convertDtoToEntity(bookDTO);
        try {
            repository.update(book);
        } catch (SQLException | ClassNotFoundException e) {
            if (((SQLException) e).getErrorCode() == 1406) {
                String message = ((SQLException) e).getMessage();
                String[] s = message.split("");
                throw new BookException("Data is To Large For " + s[1]);
            }
            throw new BookException("Error Occured Developer",e);

        }
            return false;
    }

    @Override
    public boolean delete(BookDTO bookDTO) throws ServiceException {
        return false;
    }


    @Override
    public boolean delete(Integer integer) throws BookException {
        try {
            repository.delete(integer);
        } catch (SQLException | ClassNotFoundException e) {
            throw new BookException("Error Occured Contact Developer",e);
        }
        return false;
    }

    @Override
    public Optional<BookDTO> search(Integer integer) throws BookException {

        try {Optional<Book> search= repository.search(integer);
        if (search.isPresent()){
            Book book=search.get();
            BookDTO bookDTO=convertEntityToDto(book);
            return  Optional.of(bookDTO);
        }
        return Optional.empty();

        } catch (SQLException | ClassNotFoundException e) {
            throw new BookException("Contact Develpoer",e);
        }

    }

    @Override
    public List<BookDTO> getAll() throws BookException {
        try {
            List<Book> all=repository.getAll();
            List<BookDTO>newList=new ArrayList<>();
            for (Book book: all){
                BookDTO bookDTO=convertEntityToDto(book);
                newList.add(bookDTO);
            }
            return newList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new BookException("Contact Developer");
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


