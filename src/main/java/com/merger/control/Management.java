package com.merger.control;

import com.merger.MergeSorting;
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
            workWithNumbers();
        } else if (Command.STRING == commandHandler.getTypeData()) {
            workWithStrings();
        }
    }

    public void workWithNumbers() {
        int[] arrayNumbers = fileHandler.readToArrayNumbers(commandHandler.getFileNames());
        arrayNumbers = mergeSort.mergeSortNumbers(arrayNumbers, 0,
                arrayNumbers.length - 1, commandHandler.getSorting());

        fileHandler.writeToFileNumbers(arrayNumbers, commandHandler.getResultFileName());
    }

    public void workWithStrings() {
        String[] arrayStrings = fileHandler.readToArrayStrings(commandHandler.getFileNames());
        arrayStrings = mergeSort.mergeSortStrings(arrayStrings, 0,
                arrayStrings.length - 1, commandHandler.getSorting());

        fileHandler.writeToFileStrings(arrayStrings, commandHandler.getResultFileName());
    }
}
