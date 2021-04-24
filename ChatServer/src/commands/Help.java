package commands;

import interfaces.Command;
import workers.ClientWorker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Help implements Command {
    private HashMap<String, Command> commandHashMap = new HashMap<>();

    @Override
    public String[] getArgumentsDescription() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return "List all commands and their usage.";
    }

    @Override
    public void execute(String argument, ClientWorker clientWorker) throws IOException {
        clientWorker.writeLine("Registered commands:");
        for (Map.Entry<String, Command> entry : commandHashMap.entrySet()) {
            clientWorker.writeLine(entry.getKey() + " - " + entry.getValue().getDescription());
        }
    }

    public void setCommandList(HashMap<String, Command> commandHashMap) {
        this.commandHashMap = commandHashMap;
    }
}
