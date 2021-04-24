package utils;

import interfaces.Command;
import workers.ClientWorker;

import java.io.IOException;

public class CommandHandler {
    private final ClientWorker clientWorker;
    private final Commands commands = Commands.getSingleton();

    public CommandHandler(ClientWorker clientWorker) {
        this.clientWorker = clientWorker;
    }

    public void onCommand(String command) throws IOException {
        command = command.trim();
        String[] tokens = command.split(" ");
        String name = tokens[0].toLowerCase();
        Command registeredCommand = commands.getCommand(name);
        if (registeredCommand != null) {
            registeredCommand.execute(command.substring(name.length()).trim(), clientWorker);
        } else {
            clientWorker.writeLine("Command not found. Use help command to list all commands and their usage.");
        }
    }
}
