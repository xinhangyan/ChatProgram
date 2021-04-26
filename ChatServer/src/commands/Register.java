package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
import models.User;
import workers.ClientWorker;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Register implements Command {
    @Override
    public String[] getArgumentsDescription() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return "Register a new account.";
    }
    
    @Override
    public void execute(TransDto dto, ClientWorker clientWorker) throws IOException {
        if (clientWorker.getUser() != null) {
            clientWorker.write(new TransDto(false,"register",dto.getSource(),"You are already logged in!"));
            return;
        }
        if (Objects.isNull(dto.getUsername()) || Objects.isNull(dto.getPassword()) || dto.getUsername().length()==0 || dto.getPassword().length()==0) {
            clientWorker.write(new TransDto(false,"register",dto.getSource(),"Invalid arguments."));
            return;
        }

        if (dto.getUsername().length()<3 || dto.getUsername().length()>13) {
            clientWorker.write(new TransDto(false,"register",dto.getSource(),"name length must between 3 and 13"));
            return;
        }

        if (dto.getPassword().length()<3 || dto.getPassword().length()>13) {
            clientWorker.write(new TransDto(false,"register",dto.getSource(),"password length must between 3 and 13"));
            return;
        }



        UserDatabase userDatabase = UserDatabase.getSingleton();

        if (!userDatabase.isUserWithUsernamePresent(dto.getUsername())) {
            User user = userDatabase.addUser(dto.getUsername(), dto.getPassword());
            TransDto register = new TransDto(true, "register", dto.getSource(), dto.getUsername() + " added.");
            register.setUser(user);
            clientWorker.write(register);
            clientWorker.setUser(user);
        } else {
            clientWorker.write(new TransDto(false,"register",dto.getSource(),"Unable to add user."));
        }
    }
}
