package repository.custom.impl;

import entity.custom.Publisher;
import repository.custom.PublisherRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublisherRepositoryIMPL implements PublisherRepository {
    @Override
    public boolean save(Publisher publisher) throws SQLException, ClassNotFoundException {
    String sql="INSERT INTO publisher(name,location,contact) VALUES(?,?,?)";
    return CrudUtil.execute(sql,publisher.getName(),publisher.getLocation(),publisher.getContact());

    }

    @Override
    public boolean update(Publisher publisher) throws SQLException, ClassNotFoundException {
    String sql= "UPDATE publisher SET name=?,location=?,contact=? WHERE id=?";
    return CrudUtil.execute(sql,publisher.getName(),publisher.getLocation(),publisher.getContact(),publisher.getId());
    }

    @Override
    public Optional<Publisher> search(Integer s) throws SQLException, ClassNotFoundException {
    String sql= "SELECT *FROM publisher WHERE id=?";
        ResultSet execute = CrudUtil.execute(sql,s);
        if (execute.next()){
            Publisher publisher= new Publisher();
            publisher.setId(execute.getInt(1));
            publisher.setName(execute.getString(2));
            publisher.setLocation(execute.getString(3));
            publisher.setContact(execute.getString(4));
            return Optional.of(publisher);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer integer) throws SQLException, ClassNotFoundException {
    String sql= "DELETE FROM publisher WHERE id=?";
        return false;
    }

    @Override
    public List<Publisher> getAll() throws SQLException, ClassNotFoundException {
    String sql="SELECT * FROM publisher";
    ResultSet execute=CrudUtil.execute(sql);
    List<Publisher> publishers=new ArrayList<>();
    while (execute.next()){
        Publisher publisher= new Publisher();
        publisher.setId(execute.getInt(1));
        publisher.setName(execute.getString(2));
        publisher.setLocation(execute.getString(3));
        publisher.setContact(execute.getString(4));
        publishers.add(publisher);
    }
        return publishers;
    }
}
