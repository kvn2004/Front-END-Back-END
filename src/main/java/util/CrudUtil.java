package util;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * --------------------------------------------
 * Author: Vihanga Nimsara(kvn2004)
 * GitHub: https://github.com/kvn2004
 * --------------------------------------------
 * Created: 5/27/2025 10:18 PM
 * Project: crudApp
 * --------------------------------------------
 **/

public class CrudUtil {
    // Generic method for executing SQL queries
    public static <T> T execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            pst.setObject(i + 1, params[i]);
        }

        if (sql.trim().toLowerCase().startsWith("select")) {
            ResultSet resultSet = pst.executeQuery();
            return (T) resultSet;  // Caller must handle ResultSet
        } else {
            int rowsAffected = pst.executeUpdate();
            return (T) (Boolean) (rowsAffected > 0);  // true if successful
        }
    }
}
