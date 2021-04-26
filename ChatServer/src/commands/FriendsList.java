package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
import models.User;
import workers.ClientWorker;

import java.io.IOException;

public class FriendsList implements Command {
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
        User[] users = userDatabase.getFriends(clientWorker.getUser());
        TransDto transDto = new TransDto(true, "friendlist", dto.getSource(), "");
        transDto.setUsers(users);
        clientWorker.write(transDto);
    }
}
