package commands;

import database.UserDatabase;
import interfaces.Command;
import models.User;
import workers.ClientWorker;

import java.io.IOException;

public class FriendByUsername implements Command {
    @Override
    public String[] getArgumentsDescription() {
        return new String[]{ "<Username>" };
    }

    @Override
    public String getDescription() {
        return "Add friend by username.";
    }

    @Override
    public void execute(String argument, ClientWorker clientWorker) throws IOException {
        User user = clientWorker.getUser();
        if (user == null) {
            clientWorker.writeLine("You are not logged in!");
            return;
        }
        if (user.getUsername().equals(argument)) {
            clientWorker.writeLine("You cannot add yourself as friend!");
            return;
        }
        UserDatabase userDatabase = UserDatabase.getSingleton();
        boolean result = userDatabase.friendUserByUsername(user, argument);
        clientWorker.writeLine(result ? "Friend request sent." : "Sorry, but maybe you already added them?");

        for (ClientWorker activeSession : userDatabase.getActiveSessions()) {
            User targetUser = activeSession.getUser();
            if (targetUser != null && targetUser.getUsername().equals(argument)) {
                activeSession.writeLine(user.getUsername() + " sent you a friend request!");
            }
        }
    }
}
