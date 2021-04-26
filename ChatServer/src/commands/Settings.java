package commands;

import interfaces.Command;
import models.User;
import workers.ClientWorker;

import java.io.IOException;

public class Settings implements Command {
    private final String callBackName = "LoginListener|";

    @Override
    public String[] getArgumentsDescription() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return "User settings.";
    }

    @Override
    public void execute(String argument, ClientWorker clientWorker) throws IOException {
        String[] arguments = argument.split(" ");
        if (arguments.length < 2) {
            clientWorker.writeLine("Invalid arguments.");
            return;
        }
        if (!hasUserLoggedIn(clientWorker)) {
            clientWorker.writeLine("You must log in first!");
            return;
        }
        switch (arguments[0].toLowerCase()) {
            case "desc":
                String description = argument.substring(arguments[0].length() + 1);
                if (description.contains(",")) {
                    clientWorker.writeLine("Description contains invalid characters.");
                    return;
                }
                clientWorker.getUser().setDescription(description);
                clientWorker.writeLine("Description changed to: " + description);
                break;
            case "private":
                boolean changeToStatus;
                if ("true".equalsIgnoreCase(arguments[1])) {
                    changeToStatus = true;
                } else if ("false".equalsIgnoreCase(arguments[1])) {
                    changeToStatus = false;
                } else {
                    clientWorker.writeLine("Invalid argument.");
                    return;
                }
                clientWorker.getUser().setPrivate(changeToStatus);
                clientWorker.writeLine("Private status changed to: " + changeToStatus);
                break;
            case "photo":
                if (arguments[1].contains(",")) {
                    clientWorker.writeLine("Photo URL contains invalid characters.");
                    return;
                }
                clientWorker.getUser().setPhotoURL(arguments[1]);
                clientWorker.writeLine("Avatar changed to: " + arguments[1]);
            default:
                clientWorker.writeLine("Unknown subcommands.");
        }
    }

    private boolean hasUserLoggedIn(ClientWorker clientWorker) {
        return clientWorker.getUser() != null;
    }
}
