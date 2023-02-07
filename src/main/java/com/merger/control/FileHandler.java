package com.merger.control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    public int[] toArrayDigits(String[] fileNames) {
        int[][] arraysDigits = new int[fileNames.length][0];
        int fileCount = 0;
        int totalDigits = 0;

        for (String fileName : fileNames) {
            try (FileInputStream fis = new FileInputStream("C://" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
                arraysDigits[fileCount] = new int[getLinesCount(fileName)];

                for (int idx = 0; idx < arraysDigits[fileCount].length; idx++) {
                    try {
                        String line = reader.readLine();
                        arraysDigits[fileCount][idx] = checkForNumAndParse(line);
                        totalDigits++;
                    } catch (IllegalArgumentException e) {
                        idx--;
                        System.out.println("Файл " + fileName + " содержит другие символы кроме чисел. Ожидаются только целые числа.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Произошла ошибка при попытки чтении из " + fileName + ".");
            }
            fileCount++;
        }
        return mergeArrays(arraysDigits, totalDigits);
    }

    private int[] mergeArrays(int[][] arrays, int totalDigits) {
        int[] arrayDigits = new int[totalDigits];
        int idx = 0;

        for (int[] array : arrays) {
            for (int digit : array) {
                if (!(idx > array.length / 1.5 && digit == 0)) {
                    arrayDigits[idx++] = digit;
                }
            }
        }
        return arrayDigits;
    }

    private int checkForNumAndParse(String line) {
        try {
            if (!line.matches("[\\d]+")) {
                throw new IllegalArgumentException();
            }
        } catch (NullPointerException e) {
            return 0;
        }
        return Integer.parseInt(line);
    }

    private int getLinesCount(String fileName) throws IOException {
        Path path = Paths.get("C://" + fileName);
        return (int) Files.lines(path).count();
    }
}
