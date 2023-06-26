package com.seat.mower.insfraestructure.file;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Service
public class FileService {

    public List<String> readFileAndGetCommands(String urlFile) {

        Path path = Paths.get(urlFile);
        var commands = new ArrayList<String>();
        try {
            commands = (ArrayList<String>) Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return commands;
    }
}
