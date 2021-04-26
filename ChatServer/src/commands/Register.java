package commands;

import database.UserDatabase;
import interfaces.Command;
import models.User;
import workers.ClientWorker;

import java.io.IOException;

public class Register implements Command {
    @Override
    public String[] getArgumentsDescription() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return "Register a new account.";
    }

    private final String callBackName = "RegisterListener|";

    @Override
    public void execute(String argument, ClientWorker clientWorker) throws IOException {
        if (clientWorker.getUser() != null) {
            clientWorker.writeLine(callBackName+"You are already logged in!");
            return;
        }
        String[] arguments = argument.split(" ");
        if (arguments.length != 2) {
            clientWorker.writeLine(callBackName+"Invalid arguments.");
            return;
        }

        if (argument.contains(",")) {
            clientWorker.writeLine(callBackName+"Arguments contain invalid characters.");
            return;
        }

        String username = arguments[0];
        String password = arguments[1];

        UserDatabase userDatabase = UserDatabase.getSingleton();

        if (!userDatabase.isUserWithUsernamePresent(username)) {
            User user = userDatabase.addUser(username, password);
            clientWorker.writeLine(callBackName+user.getUsername()+" added.");
            clientWorker.setUser(user);
        } else {
            clientWorker.writeLine(callBackName+"Unable to add user.");
        }
    }
}
