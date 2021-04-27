package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
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
    public void execute(TransDto dto, ClientWorker clientWorker) throws IOException {
        User user = clientWorker.getUser();
        if (user == null) {
            clientWorker.write(new TransDto(false,"addfriend",dto.getSource(),"You are not logged in!"));
            return;
        }
        if (user.getUsername().equals(dto.getUsername())) {
            clientWorker.write(new TransDto(false,"addfriend",dto.getSource(),"You cannot add yourself as friend!"));
            return;
        }
        UserDatabase userDatabase = UserDatabase.getSingleton();
        String result = userDatabase.friendUserByUsername(user, dto.getUsername());
        TransDto returnDto = new TransDto(result.length() == 0, "addfriend", dto.getSource(), result.length() == 0 ? "Friend request sent." : result);
        returnDto.setUser(clientWorker.getUser());
        clientWorker.write(returnDto);

        for (ClientWorker activeSession : userDatabase.getActiveSessions()) {
            User targetUser = activeSession.getUser();
            if (targetUser != null && targetUser.getUsername().equals(dto.getUsername())) {
                TransDto transDto = new TransDto(true, "addfriend", "ReceiveAcceptListener", user.getUsername() + " sent you a friend request!");
                transDto.setUser(targetUser);
                activeSession.write(transDto);
            }
        }
    }
}
