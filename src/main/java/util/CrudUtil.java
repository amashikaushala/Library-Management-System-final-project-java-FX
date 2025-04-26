package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

    public static <T> T execute(String sql, Object... args) throws SQLException, ClassNotFoundException {

        // Get connection
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);

        // Set arguments in the PreparedStatement
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }

        // Handle SELECT queries
        if (sql.toLowerCase().startsWith("select")) {
            ResultSet resultSet = null;
            try {
                resultSet = ps.executeQuery();
                return (T) resultSet;  // Return ResultSet for SELECT queries
            } finally {
                // Do not close ResultSet, it will be handled by the caller
            }
        }

        // Handle UPDATE/INSERT/DELETE queries
        int affectedRows = ps.executeUpdate();
        return (T) Boolean.valueOf(affectedRows > 0);  // Return boolean for DML queries
    }
}
