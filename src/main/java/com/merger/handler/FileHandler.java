package com.merger.handler;

import com.merger.exception.UnableToReadFileLinesException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private final String ERROR = "Произошла ошибка при попытки чтении из %s. Возможно файла не существует. \n";
    private final String ILLEGAL_ARG = "Файл %s содержит недопустимые символы. Ожидаются только %s. \n";
    private final String IGNORED = "Найдена строка - %s. Недопустимый формат. \n";
    private final char INVALID_SYMBOL_STRING = ' ';
    private final char DIGIT_ZERO = 48;
    private final char DIGIT_NINE = 57;

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
        List<Integer> arraysNumbers = new ArrayList<>();

        for (String fileName : fileNames) {
            try (FileInputStream fis = new FileInputStream("C://MergeSort/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

                while (reader.ready()) {
                    String line = reader.readLine();
                    try {
                        arraysNumbers.add(checkForMissCharAndParse(line));
                    } catch (IllegalArgumentException e) {
                        System.out.printf(ILLEGAL_ARG, fileName, "целые числа");
                        System.out.printf(IGNORED, line);
                    }
                }
            } catch (UnableToReadFileLinesException e) {
                System.out.println("Не удалось прочитать количество строк в файле " + fileName);
            } catch (IOException e) {
                System.out.printf(ERROR, fileName);
            }
        }
        return arraysNumbers.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public String[] readToArrayStrings(String[] fileNames) {
        List<String> arrayStrings = new ArrayList<>();

        for (String fileName : fileNames) {
            try (FileInputStream fis = new FileInputStream("C://MergeSort/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

                while (reader.ready()) {
                    String line = reader.readLine();
                    try {
                        arrayStrings.add(checkForMissSpace(line));
                    } catch (IllegalArgumentException e) {
                        System.out.printf(ILLEGAL_ARG, fileName, "символы без пробелов");
                        System.out.printf(IGNORED, line);
                    }
                }
            } catch (IOException e) {
                System.out.printf(ERROR, fileName);
            }
        }
        return arrayStrings.toArray(new String[0]);
    }

    private int checkForMissCharAndParse(String line) throws IllegalArgumentException {
        for (char ch : line.toCharArray()) {
            if (ch < DIGIT_ZERO || ch > DIGIT_NINE) {
                throw new IllegalArgumentException();
            }
        }
        return Integer.parseInt(line);
    }

    private String checkForMissSpace(String line) throws IllegalArgumentException {
        for (char ch : line.toCharArray()) {
            if (ch == INVALID_SYMBOL_STRING) {
                throw new IllegalArgumentException();
            }
        }
        return line;
    }
}