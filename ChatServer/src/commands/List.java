package commands;

import database.UserDatabase;
import interfaces.Command;
import workers.ClientWorker;

import java.io.IOException;
import java.util.ArrayList;

public class List implements Command {
    private final String callBackName = "LoginListener|";

    @Override
    public String[] getArgumentsDescription() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return "List online users.";
    }

    @Override
    public void execute(String argument, ClientWorker clientWorker) throws IOException {
        UserDatabase userDatabase = UserDatabase.getSingleton();
        ArrayList<String> onlineUsers = userDatabase.getOnlineUsers();
        clientWorker.writeLine(onlineUsers.toString());
    }
}
