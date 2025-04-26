package repository.custom.impl;

import entity.custom.Author;
import repository.custom.AuthorRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorRepositoryIMPL implements AuthorRepository {
    @Override
    public boolean save(Author author) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Author(name,contact) VALUES(?,?)";
        boolean execute=CrudUtil.execute(sql, author.getId(),author.getName());
        return execute;
    }

    @Override
    public boolean update(Author author) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Author SET name=?, contact=?  WHERE id=?";
        boolean execute =CrudUtil.execute(sql,author.getName(),author.getContact(),author.getId());
        return execute;
    }

    @Override
    public Optional<Author> search(Integer integer) throws SQLException, ClassNotFoundException {
       String sql = "SELECT * FROM Author WHERE id=?";
        ResultSet execute= CrudUtil.execute(sql);
        if (execute.next()){
            Author author= new Author();
            author.setId(execute.getInt(1));
            author.setName(execute.getString(2));
            author.setContact(execute.getString(3));
            return Optional.of(author);
        }
       return Optional.empty();
    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
      String sql="DELETE FROM Author WHERE id=?";
      return CrudUtil.execute(sql,integer);
    }

    @Override
    public List<Author> getAll() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM Author";
        ResultSet rs=CrudUtil.execute(sql);
        List<Author> authors=new ArrayList<>();
        while (rs.next()){
            Author author=new Author();
            author.setId(rs.getInt(1));
            author.setName(rs.getString(2));
            author.setContact(rs.getString(3));
            authors.add(author);
        }
        return authors;
    }
}
