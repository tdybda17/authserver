package org.mediabump.db.mysql.migrations;
import org.mediabump.db.mysql.connector.DbConnection;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.SortedSet;
import java.util.TreeSet;

public class Migrate {

    private final String migrationFilesFolder = "migrations";

    public static void main(String[] args) {
        new Migrate().migrate(DbConnection.getConnection());
    }

    public void migrate(Connection connection) {
        try {
            SortedSet<MigrationFile> migrationFiles = new Migrate().getMigrationFiles();
            for (MigrationFile file : migrationFiles) {
                try {
                    Statement stmt = connection.createStatement();
                    stmt.execute(file.getQuery());
                    System.out.println(file.getFilename() + " OK");
                } catch (SQLException e) {
                    System.out.println("Note: See stacktrace below...");
                    System.out.println("SQL Error in file: " + file.toString());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SortedSet<MigrationFile> getMigrationFiles() throws IOException {
        SortedSet<MigrationFile> migrationFiles = new TreeSet<>();
        try (InputStream in = getResourceAsStream(migrationFilesFolder);
             BufferedReader br = new BufferedReader(new InputStreamReader(in)))
        {
            String resource;
            while ((resource = br.readLine()) != null) {
                File file = new File(Paths.get( "db", "src", "main", "resources", "migrations", resource).toFile().getAbsolutePath());
                migrationFiles.add(new MigrationFile(
                        file.getName(),
                        filenameToLocalDate(file.getName()),
                        Files.readString(file.toPath())
                ));
            }
        }
        return migrationFiles;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);
        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private LocalDateTime filenameToLocalDate(String filename) {
        try {
            String dateString = filename.substring(0, filename.indexOf("_")).replace('.', ':');
            char[] chars = dateString.toCharArray();
            chars[19] = '.';
            return LocalDateTime.parse(String.valueOf(chars), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new RuntimeException("Migrations files needs to follow naming convention ISO-DATE-TIME + '_' + name\n" +
                                       "Use . instead of : in ISO-DATE-TIME");
        }
    }
}
