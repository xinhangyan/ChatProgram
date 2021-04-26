package commands;

import database.UserDatabase;
import interfaces.Command;
import models.TransDto;
import models.User;
import utils.CustomLogger;
import workers.ClientWorker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public class Accept implements Command {
    @Override
    public String[] getArgumentsDescription() {
        return new String[]{ "<Username>" };
    }

    @Override
    public String getDescription() {
        return "Accept friend requests.";
    }

    @Override
    public void execute(TransDto dto, ClientWorker clientWorker) throws IOException {
        User user = clientWorker.getUser();
        if (user == null) {
            clientWorker.write(new TransDto(false,"accept",dto.getSource(),"You are not logged in!"));
            return;
        }
        if (Objects.isNull(dto.getUsername()) || dto.getUsername().length()==0) {
            clientWorker.write(new TransDto(false,"accept",dto.getSource(),"You did not specify the user to accept."));
            return;
        }
        UserDatabase userDatabase = UserDatabase.getSingleton();
        if (userDatabase.getUsernamesByIds(user.getPendingFriendRequests()).contains(dto.getUsername())) {
            User targetUser = userDatabase.getUser(dto.getUsername());
            if (targetUser == null) {
                clientWorker.write(new TransDto(false,"accept",dto.getSource(),"Internal error occurred."));
                CustomLogger logger = CustomLogger.getSingleton();
                logger.warning("User not found! Database may be broken!");
                return;
            }
            TreeSet<Integer> targetFriends = targetUser.getFriends();
            targetFriends.add(user.getId());
            TreeSet<Integer> friends = user.getFriends();
            friends.add(targetUser.getId());
            targetUser.setFriends(targetFriends);
            user.setFriends(friends);
            user.getPendingFriendRequests().remove(targetUser.getId());
            clientWorker.write(new TransDto(true,"accept",dto.getSource(),"Friend request accepted."));
        }
    }
}
