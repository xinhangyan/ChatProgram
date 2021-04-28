import database.UserDatabase;
import models.User;
import utils.CustomLogger;
import workers.ServerWorker;
import workers.ShutdownWorker;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 *  This is server class.
 */

public class ChatServer {
    private final static CustomLogger logger = CustomLogger.getSingleton();
    private static UserDatabase database = UserDatabase.getSingleton();
    private static ServerSocket serverSocket;

    public ChatServer() {
        this("0.0.0.0", 6667);
    }

    public ChatServer(String bindAddress, int port) {
        try {
            logger.info("Trying to load users from database...");
            // read files from database
            database.loadProfiles();
        } catch (Exception e) {
            logger.error(e.getMessage());
            StringBuilder stacktrace = new StringBuilder();
            Arrays.stream(e.getStackTrace()).forEach(st -> stacktrace.append(st + System.lineSeparator()));
            logger.fatal(stacktrace.toString());
        }

        Runtime.getRuntime().addShutdownHook(new ShutdownWorker());

        try {
            serverSocket = new ServerSocket(port, 0, InetAddress.getByName(bindAddress));
            ServerWorker serviceWorker = new ServerWorker(serverSocket);
            serviceWorker.start();
        } catch (IOException e) {
            logger.fatal(e.getMessage());
        }

        logger.info("Listening on " + bindAddress + ":" + port);
    }
}
