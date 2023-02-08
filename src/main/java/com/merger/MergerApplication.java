package com.merger;

import com.merger.control.Management;
import com.merger.handler.CommandHandler;

public class MergerApplication {
    public static void main(String[] args) {
        CommandHandler handler = new CommandHandler(args);
        Management management = new Management(handler);
        management.start();
    }
}
