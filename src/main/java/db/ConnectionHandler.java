package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectionHandler {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private Connection connection;

    public ConnectionHandler() throws SQLException {
        this.connection = DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public int executeUpdateQuery(String query) {
        try (Connection connection = this.connection;
             Statement statement =connection.createStatement()
        ){
            statement.executeUpdate(query, statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.getGeneratedKeys();
            int setId = 0;
            while (result.next()) {
                setId = Integer.parseInt(result.getArray(1).toString());
            }
            statement.close();
            connection.close();
            return setId;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList executeSelectQuery(String query) {
        try (Connection connection = this.connection;
             Statement statement =connection.createStatement()
        ){
            ResultSet result = statement.executeQuery(query);
            ArrayList data = new ArrayList();
            while (result.next()) {
                HashMap row = new HashMap();
                for (int i=1; i<=result.getMetaData().getColumnCount(); i++) {
                    row.put(result.getMetaData().getColumnName(i), result.getArray(i));
                }
                data.add(row);
            }
            result.close();
            statement.close();
            connection.close();
            return data;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}