package com.merger.handler;

import com.merger.control.Command;
import com.merger.exception.CommandIsNotEnteredException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CommandHandler {
    private Command sorting = Command.ASCENDING;
    private Command typeData;
    private String resultFileName;
    private String[] fileNames;
    private int currentIdx = 0;

    private final String[] incomingCommand;

    public CommandHandler(String[] args) {
        this.incomingCommand = args;
    }

    public void init() {
        checkSorting();
        checkTypeData();
        checkResultFileName();
        separateIncomingFiles();
    }

    private void checkSorting() {
        for (int idx = 0; idx < 2; idx++) {
            if ("-d".equals(incomingCommand[idx])) {
                sorting = Command.DESCENDING;
                currentIdx = idx > 0 ? 0 : 1;
                break;
            } else if ("-a".equals(incomingCommand[idx])) {
                currentIdx = idx > 0 ? 0 : 1;
            }
        }
    }

    private void checkTypeData() {
        if ("-i".equals(incomingCommand[currentIdx])) {
            typeData = Command.INTEGER;
            currentIdx++;
        } else if ("-s".equals(incomingCommand[currentIdx])) {
            typeData = Command.STRING;
            currentIdx++;
        } else {
            System.out.println("Отсутствует команда для типа данных");
            throw new CommandIsNotEnteredException();
        }
        if ("-a".equals(incomingCommand[1]) || "-d".equals(incomingCommand[1])) {
            currentIdx++;
        }
    }

    private void checkResultFileName() {
        Path directory = Path.of("C://MergeSort/Result");
        if (!Files.exists(directory)) {
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        resultFileName = incomingCommand[currentIdx];
        Path resultPath = Path.of("C://MergeSort/Result/" + resultFileName);
        try {
            if (Files.exists(resultPath)) {
                Files.delete(resultPath);
            }
            Files.createFile(resultPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        currentIdx++;
    }

    private void separateIncomingFiles() {
        String[] fileNames = new String[incomingCommand.length - currentIdx];
        for (int idx = 0; idx < fileNames.length; idx++) {
            fileNames[idx] = incomingCommand[currentIdx++];
        }
        this.fileNames = fileNames;
    }

    public Command getSorting() {
        return sorting;
    }

    public Command getTypeData() {
        return typeData;
    }

    public String getResultFileName() {
        return resultFileName;
    }

    public String[] getFileNames() {
        return fileNames;
    }
}
