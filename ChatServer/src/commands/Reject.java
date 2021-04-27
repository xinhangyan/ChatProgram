package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
import workers.ClientWorker;

import java.io.IOException;

public class Reject implements Command {
    @Override
    public String[] getArgumentsDescription() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void execute(TransDto dto, ClientWorker clientWorker) throws IOException {
        boolean b = UserDatabase.getSingleton().removePending(clientWorker, dto.getUsername());
        TransDto transDto = new TransDto(b, "reject", dto.getSource(), "");
        transDto.setUser(clientWorker.getUser());
        clientWorker.write(transDto);
    }
}
