package org.mediabump.db.mysql.migrations;

import java.time.LocalDateTime;

public class MigrationFile implements Comparable<MigrationFile> {
    private String filename;
    private LocalDateTime createdDate;
    private String query;

    public MigrationFile(String filename, LocalDateTime createdDate, String query) {
        this.filename = filename;
        this.createdDate = createdDate;
        this.query = query;
    }

    @Override
    public int compareTo(MigrationFile o) {
        return this.createdDate.compareTo(o.createdDate);
    }

    public String getFilename() {
        return filename;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public String toString() {
        return "MigrationFile{" +
                "filename='" + filename + '\'' +
                ", createdDate=" + createdDate +
                ", query='" + query + '\'' +
                '}';
    }
}
