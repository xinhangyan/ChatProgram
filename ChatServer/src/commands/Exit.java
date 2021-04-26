package commands;

import interfaces.Command;
import workers.ClientWorker;

import java.io.IOException;

public class Exit implements Command {
    private final String callBackName = "ExitListener|";

    @Override
    public String[] getArgumentsDescription() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return "Leave the server.";
    }

    @Override
    public void execute(String argument, ClientWorker clientWorker) throws IOException {
        clientWorker.writeLine("Bye!");
        clientWorker.setClientOnline(false);
    }
}
