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

    private final String callBackName = "LoginListener|";
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
        String username = arguments[0];
        String password = arguments[1];

        UserDatabase userDatabase = UserDatabase.getSingleton();

        if (userDatabase.getOnlineUsers().contains(username)) {
            clientWorker.writeLine(callBackName+"This user is already online.");
            return;
        }

        User user = userDatabase.authenticateAndGetUser(username, password);

        if (user != null) {
            clientWorker.setUser(user);
            clientWorker.writeLine(callBackName+user.getUsername()+" are now logged in!");
        } else {
            clientWorker.writeLine(callBackName+"Unable to login.");
        }
    }
}
