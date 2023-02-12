package com.merger.control;

import com.merger.handler.CommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ManagementTest {
    @Mock
    private CommandHandler commandHandler;
    @InjectMocks
    private Management management;

    @Test
    public void shouldStartWorkWithStrings() {
        String[] arg = {"-s", "result.exe", "in1.txt"};

        commandHandler.setIncomingCommand(arg);
        management.start();
    }

    @Test
    public void shouldStartWorkWithNumbers() {
        String[] arg = {"-i", "result.exe", "in1.txt"};

        commandHandler.setIncomingCommand(arg);
        management.start();
    }
}
