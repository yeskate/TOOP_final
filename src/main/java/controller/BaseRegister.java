package controller;

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
        this.path = Paths.get(path).toAbsolutePath();
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

    public List<String> getAllString() {
        return lines;
    }

    public abstract void rewrite();
}

