package commands;

import interfaces.Command;
import models.TransDto;
import workers.ClientWorker;

import java.io.IOException;

public class Exit implements Command {
    @Override
    public String[] getArgumentsDescription() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return "Leave the server.";
    }

    @Override
    public void execute(TransDto dto, ClientWorker clientWorker) throws IOException {
        clientWorker.write(new TransDto(true,"exit",dto.getSource(),"You are not logged in!"));
        clientWorker.setClientOnline(false);
    }
}
