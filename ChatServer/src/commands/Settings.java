package commands;

import interfaces.Command;
import models.TransDto;
import models.User;
import workers.ClientWorker;

import java.io.IOException;

public class Settings implements Command {
    @Override
    public String[] getArgumentsDescription() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return "User settings.";
    }

    @Override
    public void execute(TransDto dto, ClientWorker clientWorker) throws IOException {

        if (!hasUserLoggedIn(clientWorker)) {
            clientWorker.write(new TransDto(false,"set",dto.getSource(),"You must log in first!"));
            return;
        }

        User user = clientWorker.getUser();
        User modify = dto.getUser();
        user.setDescription(modify.getDescription());
        user.setPhotoURL(modify.getPhotoURL());
        user.setPrivate(modify.isPrivate());
        user.setEmail(modify.getEmail());
        user.setFavorite(modify.getFavorite());
        TransDto transDto = new TransDto(true, "set", dto.getSource(), "success!");
        transDto.setUser(user);
        clientWorker.write(transDto);

    }

    private boolean hasUserLoggedIn(ClientWorker clientWorker) {
        return clientWorker.getUser() != null;
    }
}
