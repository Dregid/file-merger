package com.merger.control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    private final String ERROR = "Произошла ошибка при попытки чтении из %s. \n";
    private final String ILLEGAL_ARG = "Файл %s содержит недопустимые символы. Ожидаются только %s. \n";
    private final String IGNORED = "Строка под номером %d, содержит %s. Недопустимый формат. \n";

    public int[] toArrayDigits(String[] fileNames) {
        int fileCount = 0;
        int totalDigits = 0;
        int[][] arraysDigits = new int[fileNames.length][0];

        for (String fileName : fileNames) {
            try (FileInputStream fis = new FileInputStream("C://TestMergeSort/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
                arraysDigits[fileCount] = new int[getLinesCount(fileName)];

                for (int idx = 0; idx < arraysDigits[fileCount].length; idx++) {
                    String line = reader.readLine();
                    try {
                        arraysDigits[fileCount][idx] = checkForMissCharAndParse(line);
                        totalDigits++;
                    } catch (IllegalArgumentException e) {
                        System.out.printf(ILLEGAL_ARG, fileName, "целые числа");
                        System.out.printf(IGNORED, idx, line);
                        idx--;
                    }
                }
            } catch (IOException e) {
                System.out.printf(ERROR, fileName);
            }
            fileCount++;
        }
        return mergeArraysDigits(arraysDigits, totalDigits);
    }

    public String[] toArrayStrings(String[] fileNames) {
        int fileCount = 0;
        int totalWords = 0;
        String[][] arrayWords = new String[fileNames.length][0];

        for (String fileName : fileNames) {
            try (FileInputStream fis = new FileInputStream("C://TestMergeSort/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
                arrayWords[fileCount] = new String[getLinesCount(fileName)];

                for (int idx = 0; idx < arrayWords[fileCount].length; idx++) {
                    String line = reader.readLine();

                    try {
                        arrayWords[fileCount][idx] = checkForMissSpace(line);
                        totalWords++;
                    } catch (IllegalArgumentException e) {
                        System.out.printf(ILLEGAL_ARG, fileName, "символы без пробелов");
                        System.out.printf(IGNORED, idx, line);
                        idx--;
                    }
                }
            } catch (IOException e) {
                System.out.printf(ERROR, fileName);
            }
            fileCount++;
        }
        return mergeArraysStrings(arrayWords, totalWords);
    }

    private int[] mergeArraysDigits(int[][] arrays, int totalDigits) {
        int[] arrayDigits = new int[totalDigits];
        int idx = 0;

        for (int[] array : arrays) {
            for (int digit : array) {
                if (!(idx > (array.length / 1.5) && digit == 0)) {
                    arrayDigits[idx++] = digit;
                }
            }
        }
        return arrayDigits;
    }

    private String[] mergeArraysStrings(String[][] arrays, int totalWords) {
        String[] arrayWords = new String[totalWords];
        int idx = 0;

        for (String[] array : arrays) {
            for (String word : array) {
                arrayWords[idx++] = word;
            }
        }
        return arrayWords;
    }

    private int checkForMissCharAndParse(String line) {
        try {
            for (char ch : line.toCharArray()) {
                if (ch < 48 || ch > 57) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (NullPointerException e) {
            return 0;
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
            return "□";
        }
        return line;
    }

    private int getLinesCount(String fileName) throws IOException {
        Path path = Paths.get("C://TestMergeSort/" + fileName);
        return (int) Files.lines(path).count();
    }
}
