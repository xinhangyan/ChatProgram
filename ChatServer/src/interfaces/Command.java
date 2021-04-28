package interfaces;

import models.TransDto;
import workers.ClientWorker;

import java.io.IOException;

/** This is a command interface
 *
 */

public interface Command {
    String[] getArgumentsDescription();
    String getDescription();
    void execute(TransDto dto, ClientWorker clientWorker) throws IOException;
}
