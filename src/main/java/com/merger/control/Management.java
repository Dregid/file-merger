package com.merger.control;

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
        } else {
            workWithStrings();
        }
    }

    private void workWithDigits() {
        int[] arrayDigits = fileHandler.readToArrayDigits(commandHandler.getFileNames());
        if (0 == arrayDigits[arrayDigits.length - 1]) {
            arrayDigits = clearArrayFromErrors(arrayDigits);
        }
        arrayDigits = mergeSort.mergeSortDigits(arrayDigits, 0, arrayDigits.length - 1);
        fileHandler.writeToFileDigits(arrayDigits, commandHandler.getResultFileName());
    }

    private void workWithStrings() {
        String[] arrayStrings = fileHandler.readToArrayStrings(commandHandler.getFileNames());
    }

    private int[] clearArrayFromErrors(int[] array) {
        int count = 0;
        for (int idx = array.length - 1; idx > 0; idx--) {
            if (0 == array[idx]) {
                count++;
            }
        }
        int [] result = new int[array.length - count];
        System.arraycopy(array, 0, result, 0, result.length);

        return result;
    }
}
