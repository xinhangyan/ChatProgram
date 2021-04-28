package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
import models.User;
import workers.ClientWorker;

import java.io.IOException;
import java.util.Objects;

/**
 *  Login to account if already have one.
 */

public class Login implements Command {
    @Override
    public String[] getArgumentsDescription() {
        return new String[]{ "<Password>" };
    }

    @Override
    public String getDescription() {
        return "Login to account.";
    }

    @Override
    public void execute(TransDto dto, ClientWorker clientWorker) throws IOException {
        if (clientWorker.getUser() != null) {
            clientWorker.write(new TransDto(false,"login",dto.getSource(),"You are already logged in!"));
            return;
        }

        if (Objects.isNull(dto.getUsername()) || Objects.isNull(dto.getPassword()) || dto.getUsername().length()==0 || dto.getPassword().length()==0) {
            clientWorker.write(new TransDto(false,"login",dto.getSource(),"Invalid arguments."));
            return;
        }

        UserDatabase userDatabase = UserDatabase.getSingleton();

        if (userDatabase.getOnlineUsers().contains(dto.getUsername())) {
            clientWorker.write(new TransDto(false,"login",dto.getSource(),"This user is already online."));
            return;
        }

        User user = userDatabase.authenticateAndGetUser(dto.getUsername(), dto.getPassword());
        if (user != null) {
            clientWorker.setUser(user);
            TransDto login = new TransDto(true, "login", dto.getSource(), "you are now logged in!");
            login.setUser(user);
            clientWorker.write(login);
        } else {
            clientWorker.write(new TransDto(false,"login",dto.getSource(),"Unable to login."));
        }
    }
}
