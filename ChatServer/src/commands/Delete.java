package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
import models.User;
import workers.ClientWorker;

import java.io.IOException;

/**
 *  Delete current account.
 */

public class Delete implements Command {
    @Override
    public String[] getArgumentsDescription() {
        return new String[] { "<Confirm Username>" };
    }

    @Override
    public String getDescription() {
        return "Delete current account.";
    }

    @Override
    public void execute(TransDto dto, ClientWorker clientWorker) throws IOException {
        User user = clientWorker.getUser();
        if (user == null) {
            clientWorker.write(new TransDto(false,"delete",dto.getSource(),"You are not logged in!"));
            return;
        }
        if (!user.getUsername().equals(dto.getUsername())) {
            clientWorker.write(new TransDto(false,"delete",dto.getSource(),"You must confirm your username first to delete your account!"));
            return;
        }

        UserDatabase userDatabase = UserDatabase.getSingleton();

        userDatabase.deleteUser(user);

        clientWorker.write(new TransDto(true,"delete",dto.getSource(),"Your account has been deleted."));
        clientWorker.setClientOnline(false);
    }
}
