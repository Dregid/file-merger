package com.merger.control;

import com.merger.MergeSorting;
import com.merger.handler.ArrayHandler;
import com.merger.handler.CommandHandler;
import com.merger.handler.FileHandler;

public class Management {
    private final MergeSorting mergeSort = new MergeSorting();
    private final FileHandler fileHandler = new FileHandler();
    private final CommandHandler commandHandler;

    public Management(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public void start() {
        commandHandler.init();
        if (Command.INTEGER == commandHandler.getTypeData()) {
            workWithDigits();
        } else if (Command.STRING == commandHandler.getTypeData()) {
            workWithStrings();
        }
    }

    private void workWithDigits() {
        int[] arrayDigits = fileHandler.readToArrayDigits(commandHandler.getFileNames());
        arrayDigits = mergeSort.mergeSortDigits(arrayDigits, 0,
                arrayDigits.length - 1, commandHandler.getSorting());

        if (999999999 == arrayDigits[arrayDigits.length - 1]) {
            arrayDigits = ArrayHandler.clearAscArrayFromErrors(arrayDigits);
        } else if (999999999 == arrayDigits[0]) {
            arrayDigits = ArrayHandler.clearDescArrayFromErrors(arrayDigits);
        }
        fileHandler.writeToFileDigits(arrayDigits, commandHandler.getResultFileName());
    }

    private void workWithStrings() {
        String[] arrayStrings = fileHandler.readToArrayStrings(commandHandler.getFileNames());
        arrayStrings = mergeSort.mergeSortStrings(arrayStrings, 0,
                arrayStrings.length - 1, commandHandler.getSorting());

        if ("⌂".equals(arrayStrings[arrayStrings.length - 1])) {
            arrayStrings = ArrayHandler.clearAscArrayFromErrors(arrayStrings);
        } else if ("⌂".equals(arrayStrings[0])) {
            arrayStrings = ArrayHandler.clearDescArrayFromErrors(arrayStrings);
        }
        fileHandler.writeToFileStrings(arrayStrings, commandHandler.getResultFileName());
    }
}
