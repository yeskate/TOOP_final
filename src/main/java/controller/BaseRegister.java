package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseRegister {
    Path path = null;
    static List<String> lines = new ArrayList<>();

    public BaseRegister(String path) {
        System.out.println(path);
        this.path = Paths.get(path);
        try {
            if (Files.exists(this.path)) {
                lines = Files.readAllLines(this.path, StandardCharsets.UTF_8);
            } else {
                lines = Collections.emptyList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String fragment) {
        for (String line : lines) {
            if (line.contains(fragment)) {
                return line;
            }
        }
        return null;
    }

    public List<String> getAllString() {
        return lines;
    }

    public void addString(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()))) {
            try {
                writer.write(line + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //todo удалить строку из файла
    }
}

