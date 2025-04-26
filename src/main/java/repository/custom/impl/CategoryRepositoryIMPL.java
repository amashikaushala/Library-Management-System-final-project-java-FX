package repository.custom.impl;

import entity.custom.Category;
import repository.custom.CategoryRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryIMPL implements CategoryRepository {
    @Override
    public boolean save(Category category) throws SQLException, ClassNotFoundException {
       String sql="INSERT INTO Category(name) VALUES(?)";
       return CrudUtil.execute(sql,category.getName());
    }

    @Override
    public boolean update(Category category) throws SQLException, ClassNotFoundException {
    String sql="UPDATE Category SET name=? WHERE id=?";
    return CrudUtil.execute(sql,category.getName(),category.getId());
    }

    @Override
    public Optional<Category> search(Integer id) throws SQLException, ClassNotFoundException {
    String sql="SELECT * FROM Category WHERE id=?";
        ResultSet execute=CrudUtil.execute(sql,id);
        if (execute.next()){
            Category category=new Category();
            category.setId(execute.getInt(1));
            category.setName(execute.getString(2));
            return Optional.of(category);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) throws SQLException, ClassNotFoundException {
        String sql="DELETE  FROM Category WHERE id=?";
       return CrudUtil.execute(sql,id);

    }

    @Override
    public List<Category> getAll() throws SQLException, ClassNotFoundException {
      String sql="SELECT * FROM Category";
      ResultSet execute= CrudUtil.execute(sql);
      List<Category> categories= new ArrayList<>();
      while (execute.next()){
          Category category= new Category();
          category.setId(execute.getInt(1));
          category.setName(execute.getString(2));
          categories.add(category);
      }
        return categories;
    }
}
