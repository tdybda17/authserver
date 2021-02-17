package org.mediabump.db.mysql.connector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbConnection {
    private static Connection connection;
    private final static Path PATH_TO_CONFIG = Path.of("db", "src", "main", "resources", "config.json");

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Map<String, String> config = new DbConnection().getConfig();
                try {
                    String url = "jdbc:mysql://" + config.get("db.host") + ":" +
                            config.get("db.port") + "/" + config.get("db.schema");
                    connection = DriverManager
                            .getConnection(url, config.get("db.username"), config.get("db.password"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private Map<String, String> getConfig() throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = Files.newBufferedReader(PATH_TO_CONFIG);
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(sb.toString(), Map.class);
    }
}
