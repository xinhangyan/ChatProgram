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
                clientWorker.writeLine("User does not exist.");
                return;
            }
        } else {
            user = clientWorker.getUser();
        }

        String sessionUserName = clientWorker.getUser() != null ? clientWorker.getUser().getUsername() :
                "Anonymous User from " + clientWorker.getRemoteAddress();

        clientWorker.writeLine("================================");
        clientWorker.writeLine("User: ");
        clientWorker.writeLine(user != null ? user.getUsername() : "Anonymous User from " + clientWorker.getRemoteAddress());
        if (user != null && (!user.isPrivate() || user.getUsername().equals(sessionUserName))) {
            clientWorker.writeLine("Real name:");
            clientWorker.writeLine(user.getRealName());
            clientWorker.writeLine("Is private account?");
            clientWorker.writeLine(user.isPrivate() ? "Yes" : "No");
            clientWorker.writeLine("Description:");
            clientWorker.writeLine(user.getDescription());
            clientWorker.writeLine("Friends List:");
            clientWorker.writeLine(userDatabase.getUsernamesByIds(user.getFriends()).toString());
            clientWorker.writeLine("Incoming friend requests from:");
            clientWorker.writeLine(userDatabase.getUsernamesByIds(user.getPendingFriendRequests()).toString());
        }
        clientWorker.writeLine("================================");
    }
}
