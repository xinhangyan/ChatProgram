package utils;

import interfaces.Command;
import models.TransDto;
import workers.ClientWorker;

import java.io.IOException;

public class CommandHandler {
    private final ClientWorker clientWorker;
    private final Commands commands = Commands.getSingleton();

    public CommandHandler(ClientWorker clientWorker) {
        this.clientWorker = clientWorker;
    }

    public void onCommand(TransDto dto) throws IOException {
        Command registeredCommand = commands.getCommand(dto.getTarget());
        if (registeredCommand != null) {
            registeredCommand.execute(dto, clientWorker);
        } else {
            TransDto transDto = new TransDto(false);
            transDto.setMsg("Command not found. Use help command to list all commands and their usage.");
            clientWorker.write(transDto);
        }
    }
}
