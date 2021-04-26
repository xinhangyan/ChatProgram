package commands;

import database.UserDatabase;
import interfaces.Command;
import models.User;
import workers.ClientWorker;

import java.io.IOException;

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
    public void execute(String argument, ClientWorker clientWorker) throws IOException {
        UserDatabase userDatabase = UserDatabase.getSingleton();
        User user;
        if (!"".equals(argument)) {
            user = userDatabase.getUser(argument);
            if (user == null) {
                clientWorker.writeLine(callBackName+"User does not exist.");
                return;
            }
        } else {
            user = clientWorker.getUser();
        }

        if (user != null) {
            clientWorker.writeLine(callBackName+user.getSaveString());
        }
    }
}
