package commands;

import database.UserDatabase;
import interfaces.Command;
import models.User;
import workers.ClientWorker;

import java.io.IOException;

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
    public void execute(String argument, ClientWorker clientWorker) throws IOException {
        if (clientWorker.getUser() != null) {
            clientWorker.writeLine("You are already logged in!");
            return;
        }
        String[] arguments = argument.split(" ");
        if (arguments.length != 2) {
            clientWorker.writeLine("Invalid arguments.");
            return;
        }
        String username = arguments[0];
        String password = arguments[1];

        UserDatabase userDatabase = UserDatabase.getSingleton();

        if (userDatabase.getOnlineUsers().contains(username)) {
            clientWorker.writeLine("This user is already online.");
            return;
        }

        User user = userDatabase.authenticateAndGetUser(username, password);

        if (user != null) {
            clientWorker.setUser(user);
            clientWorker.writeLine("You are now logged in!");
        } else {
            clientWorker.writeLine("Unable to login.");
        }
    }
}
