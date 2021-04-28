package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
import models.User;
import workers.ClientWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Online users list.
 */

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
        User[] allUsers = userDatabase.getAllUsers();
        User[] users = userDatabase.getOnlineUserArray();
        User[] returnUsers = Arrays.stream(allUsers)
                .filter(x->x.getId()!=clientWorker.getUser().getId())
                .peek(user -> user.setOnline(Arrays.stream(users).anyMatch(x -> x.getId() == user.getId())))
                .toArray(User[]::new);

        TransDto transDto = new TransDto(true, "list", dto.getSource(), "");
        transDto.setUsers(returnUsers);
        transDto.setUser(userDatabase.getUser(clientWorker.getUser().getUsername()));
        clientWorker.write(transDto);
    }
}
