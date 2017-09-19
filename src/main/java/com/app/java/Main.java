package com.app.java;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        List<String> stringList = null;

//        try {
//            stringList = main.readFile();
//            Parser parser = new Parser();
//            int i = 0;
//            while (i < stringList.size()) {
//                parser.insert(stringList.get(i));
//                i++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(stringList.size());
    }



    public List<String> readFile() throws IOException {
        long startReadFile =  System.currentTimeMillis();
        List<String> stringList = Files.readAllLines(Paths.get("words.txt"), StandardCharsets.UTF_8);
        long endReadFile =  System.currentTimeMillis();
        System.out.println(" " + (endReadFile - startReadFile));

        return stringList;
    }

}
