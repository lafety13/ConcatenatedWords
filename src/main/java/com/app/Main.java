package com.app;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path;
        if ((path = System.getProperty("path")) == null) {
            path = "words.txt";
        }

        try {
            List<String> stringList = FileUtils.readFile(path);
            Parser parser = new Parser();
            long start = System.currentTimeMillis();
            parser.insert(stringList);
            long end = System.currentTimeMillis();

            System.out.println("Time taken: " + (end - start) + "ms");
            System.out.println("Total concatenated words are " + parser.getTotalConcatWord());
            System.out.println("The first longest concatenated word is " + parser.getFirstLongest() + " - " + parser.getFirstLongest().length());
            System.out.println("The second longest concatenated word is " + parser.getSecondLongest() + " - " + parser.getSecondLongest().length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
