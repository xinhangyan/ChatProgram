package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
import models.User;
import workers.ClientWorker;

import java.io.IOException;

/**
 *  Show information.
 */

public class Info implements Command {

    @Override
    public String[] getArgumentsDescription() {
        return new String[]{ "(Username)"};
    }

    @Override
    public String getDescription() {
        return "See your or others' information.";
    }

    @Override
    public void execute(TransDto dto, ClientWorker clientWorker) throws IOException {
        UserDatabase userDatabase = UserDatabase.getSingleton();
        User user;
        if (!"".equals(dto.getUsername())) {
            user = userDatabase.getUser(dto.getUsername());
            if (user == null) {
                clientWorker.write(new TransDto(false,"info",dto.getSource(),"User does not exist."));
                return;
            }
        } else {
            user = clientWorker.getUser();
        }

        if (user != null) {
            TransDto info = new TransDto(true, "info", dto.getSource(), "");
            info.setUser(user);
            clientWorker.write(info);
        }
    }
}
