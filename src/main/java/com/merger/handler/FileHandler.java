package com.merger.handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    private final String ERROR = "Произошла ошибка при попытки чтении из %s. Возможно файла не существует. \n";
    private final String ILLEGAL_ARG = "Файл %s содержит недопустимые символы. Ожидаются только %s. \n";
    private final String IGNORED = "Найдена строка - %s. Недопустимый формат. \n";

    public void writeToFileNumbers(int[] array, String fileName) {
        try (FileOutputStream fos = new FileOutputStream("C://MergeSort/Result/" + fileName);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {
            for (int number : array) {
                String parse = String.valueOf(number);
                writer.write(parse);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFileStrings(String[] array, String fileName) {
        try (FileOutputStream fos = new FileOutputStream("C://MergeSort/Result/" + fileName);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {
            for (String word : array) {
                writer.write(word);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int[] readToArrayNumbers(String[] fileNames) {
        int fileCount = 0;
        int totalNumbers = 0;
        int[][] arraysNumbers = new int[fileNames.length][0];

        for (String fileName : fileNames) {
            try (FileInputStream fis = new FileInputStream("C://MergeSort/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
                arraysNumbers[fileCount] = new int[getLinesCount(fileName)];

                for (int idx = 0; idx < arraysNumbers[fileCount].length; idx++) {
                    String line = reader.readLine();
                    try {
                        arraysNumbers[fileCount][idx] = checkForMissCharAndParse(line);
                        totalNumbers++;
                    } catch (IllegalArgumentException e) {
                        System.out.printf(ILLEGAL_ARG, fileName, "целые числа");
                        System.out.printf(IGNORED, line);
                        idx--;
                    }
                }
            } catch (IOException e) {
                System.out.printf(ERROR, fileName);
            }
            fileCount++;
        }
        return ArrayHandler.mergeArraysNumbers(arraysNumbers, totalNumbers);
    }

    public String[] readToArrayStrings(String[] fileNames) {
        int fileCount = 0;
        int totalStrings = 0;
        String[][] arrayStrings = new String[fileNames.length][0];

        for (String fileName : fileNames) {
            try (FileInputStream fis = new FileInputStream("C://MergeSort/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
                arrayStrings[fileCount] = new String[getLinesCount(fileName)];

                for (int idx = 0; idx < arrayStrings[fileCount].length; idx++) {
                    String line = reader.readLine();

                    try {
                        arrayStrings[fileCount][idx] = checkForMissSpace(line);
                        totalStrings++;
                    } catch (IllegalArgumentException e) {
                        System.out.printf(ILLEGAL_ARG, fileName, "символы без пробелов");
                        System.out.printf(IGNORED, line);
                        idx--;
                    }
                }
            } catch (IOException e) {
                System.out.printf(ERROR, fileName);
            }
            fileCount++;
        }
        return ArrayHandler.mergeArraysStrings(arrayStrings, totalStrings);
    }

    private int checkForMissCharAndParse(String line) {
        try {
            for (char ch : line.toCharArray()) {
                if (ch < 48 || ch > 57) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (NullPointerException e) {
            return 999999999;
        }
        return Integer.parseInt(line);
    }

    private String checkForMissSpace(String line) {
        try {
            for (char ch : line.toCharArray()) {
                if (ch == ' ') {
                    throw new IllegalArgumentException();
                }
            }
        } catch (NullPointerException e) {
            return "⌂";
        }
        return line;
    }

    private int getLinesCount(String fileName) throws IOException {
        Path path = Paths.get("C://MergeSort/" + fileName);
        return (int) Files.lines(path).count();
    }
}