package com.example.demolistview.repositories;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class PersonFileRepository {
    private final Path filePath = Paths.get("data", "persons.csv");

    private void ensureFileExist() throws IOException{
        if(Files.notExists(filePath)){
            Files.createFile(filePath);

        }
    }

    public List<String> readAllLines() throws IOException{
        ensureFileExist();
        return Files.readAllLines(filePath, StandardCharsets.UTF_8);

    }
    public void appendNewLine(String line) throws IOException {
        Files.writeString(filePath, line+System.lineSeparator(), StandardCharsets.UTF_8, StandardOpenOption.APPEND);

    }
}
