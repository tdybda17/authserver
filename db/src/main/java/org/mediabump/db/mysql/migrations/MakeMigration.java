package org.mediabump.db.mysql.migrations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

public class MakeMigration {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print(">> Enter migration name: ");
        String name = keyboard.nextLine();
        System.out.print(">> Enter description of the migration: ");
        String description = keyboard.nextLine();
        new MakeMigration().makeNewMigrationFile(name, description);
    }

    public void makeNewMigrationFile(String input, String description) {
        String name = input.replace(' ', '_').toLowerCase();
        String now = LocalDateTime.now().toString().replace(':', '.');
        String filename = now + "_" + name + ".sql";
        Path path = Paths.get("db", "src", "main", "resources", "migrations", filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("-- SQL Migration ");
            writer.write(now);
            writer.write(" ");
            writer.write(name);
            writer.write("\n");
            writer.write("-- ");
            writer.write(description);
            writer.write("\n\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("________________________________");
        System.out.println("\u001B[32m" + "Migration added to resources/migrations");
        System.out.println(description);
        System.out.println(filename + "\u001B[0m");
        System.out.println("________________________________");
    }

}
