package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseRegister {
    Path path = null;
    List<String> lines = new ArrayList<>();

    public BaseRegister(String path) {
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

    public String getString(String fragment) {
        List<String> answer = lines.stream()
                .filter(s -> s.contains(fragment))
                .limit(1)
                .collect(Collectors.toList());
        if (answer.size() == 1) {
            return answer.get(0);
        } else {
            return null;
        }
    }

    //todo удалить строку из файла

    //todo добавить строку в файл
}

