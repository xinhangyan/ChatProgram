package commands;

import database.UserDatabase;
import interfaces.Command;
import models.User;
import workers.ClientWorker;

import java.io.IOException;

public class Delete implements Command {
    private final String callBackName = "LoginListener|";

    @Override
    public String[] getArgumentsDescription() {
        return new String[] { "<Confirm Username>" };
    }

    @Override
    public String getDescription() {
        return "Delete current account.";
    }

    @Override
    public void execute(String argument, ClientWorker clientWorker) throws IOException {
        User user = clientWorker.getUser();
        if (user == null) {
            clientWorker.writeLine("You are not logged in!");
            return;
        }
        if (!user.getUsername().equals(argument)) {
            clientWorker.writeLine("You must confirm your username first to delete your account!");
            return;
        }

        UserDatabase userDatabase = UserDatabase.getSingleton();

        userDatabase.deleteUser(user);

        clientWorker.writeLine("Your account has been deleted.");
        clientWorker.setClientOnline(false);
    }
}
