package org.mediabump.db.mysql.connector;

import org.mediabump.auth.domain.tools.provider.Credentials;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.mediabump.auth.domain.tools.provider.Credentials.Setting.*;

public class DbConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://" + Credentials.getString(DB_HOST_NAME) + ":" +
                        Credentials.getInt(DB_PORT) + "/" + Credentials.getString(DB_SCHEMA);
                connection = DriverManager
                        .getConnection(url, Credentials.getString(DB_USERNAME), Credentials.getString(DB_PASSWORD));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
