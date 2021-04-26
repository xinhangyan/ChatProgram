package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
import models.User;
import workers.ClientWorker;

import java.io.IOException;
import java.util.ArrayList;

public class List implements Command {
    @Override
    public String[] getArgumentsDescription() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return "List online users.";
    }

    @Override
    public void execute(TransDto dto, ClientWorker clientWorker) throws IOException {
        UserDatabase userDatabase = UserDatabase.getSingleton();
        User[] users = userDatabase.getOnlineUserArray();
        TransDto transDto = new TransDto(true, "list", dto.getSource(), "");
        transDto.setUsers(users);
        clientWorker.write(transDto);
    }
}
