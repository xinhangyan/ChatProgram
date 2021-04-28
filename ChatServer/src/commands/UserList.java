package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
import models.User;
import workers.ClientWorker;

import java.io.*;

/**
 *  A list of online users.
 */

public class UserList implements Command {
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
        User[] users = userDatabase.getUsers(dto.getIds());
        TransDto transDto = new TransDto(true, "userlist", dto.getSource(), "");
        transDto.setUsers(users);
        transDto.setUser(userDatabase.getUser(clientWorker.getUser().getUsername()));
        clientWorker.write(transDto);
    }
}
