package workers;

import database.UserDatabase;
import utils.CustomLogger;

import java.io.IOException;

public class ShutdownWorker extends Thread {
    private final UserDatabase userDatabase = UserDatabase.getSingleton();
    private final CustomLogger logger = CustomLogger.getSingleton();

    public ShutdownWorker() {
        super("Shutdown Worker");
    }

    @Override
    public void run() {
        logger.info("Saving user data...");
        try {
            userDatabase.saveProfiles();
        } catch (IOException e) {
            logger.fatal("Unable to save user data.");
        }
    }
}
