package interfaces;

import workers.ClientWorker;

import java.io.IOException;

public interface Command {
    String[] getArgumentsDescription();
    String getDescription();
    void execute(String argument, ClientWorker clientWorker) throws IOException;
}
