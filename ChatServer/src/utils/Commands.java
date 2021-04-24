package utils;

import commands.*;
import interfaces.Command;

import java.util.HashMap;

public class Commands {
    private static Commands commands;
    private final HashMap<String, Command> commandHashMap = new HashMap<>();

    private Commands() {
        Help helpCommand = new Help();

        commandHashMap.put("accept", new Accept());
        commandHashMap.put("set", new Settings());
        commandHashMap.put("info", new Info());
        commandHashMap.put("list", new List());
        commandHashMap.put("register", new Register());
        commandHashMap.put("login", new Login());
        commandHashMap.put("delete", new Delete());
        commandHashMap.put("addu", new FriendByUsername());
        commandHashMap.put("help", helpCommand);
        commandHashMap.put("exit", new Exit());

        helpCommand.setCommandList(commandHashMap);
    }

    public static Commands getSingleton() {
        if (commands == null) {
            commands = new Commands();
        }
        return commands;
    }

    Command getCommand(String command) {
        return commandHashMap.get(command);
    }
}
