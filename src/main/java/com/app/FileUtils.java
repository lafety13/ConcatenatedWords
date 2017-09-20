package com.app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {
    public static List<String> readFile(String path) throws IOException {
        return Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
    }
}
