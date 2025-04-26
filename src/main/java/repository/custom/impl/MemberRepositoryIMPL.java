package repository.custom.impl;

import entity.custom.Member;
import repository.custom.MemberRepository;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MemberRepositoryIMPL implements MemberRepository {

   @Override
    public boolean save(Member member) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        if (connection == null) {
            throw new SQLException("Database connection is null!");
        }

        String sql = "INSERT INTO member(id, name, address, email, contact) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, member.getId());
            ps.setString(2, member.getName());
            ps.setString(3, member.getAddress());
            ps.setString(4, member.getEmail());
            ps.setString(5, member.getContact());
            return ps.executeUpdate() > 0;
        }
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        if (connection == null) {
            throw new SQLException("Database connection is null!");
        }
        String sql = "DELETE FROM member WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Member> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean update(Member member) throws SQLException, ClassNotFoundException {
        Connection  connection =DBConnection.getInstance().getConnection();
        PreparedStatement ps =connection.prepareStatement("UPDATE member set name=?,address=?,email=?,contact=? where id=?");
        ps.setString(1,member.getName());
        ps.setString(2, member.getAddress());
        ps.setString(3, member.getEmail());
        ps.setString(4, member.getContact());
        ps.setString(5, member.getId());
        return ps.executeUpdate()>0;
    }
@Override
    public Optional<Member> search(String customerid) throws SQLException, ClassNotFoundException {
        Connection connection =DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=  connection.prepareStatement("SELECT * FROM member WHERE id=?");
        preparedStatement.setString(1,customerid);
        ResultSet rs=preparedStatement.executeQuery();
        if (rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            String address=rs.getString(3);
            String email=rs.getString(4);
            String contact=rs.getString(5);
            Member ob =new Member(id,name,address,email,contact);
            return  Optional.of(ob);
        }
        return  Optional.empty();
    }
}
