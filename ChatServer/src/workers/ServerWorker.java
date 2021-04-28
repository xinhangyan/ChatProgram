package workers;

import utils.CustomLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  This class defines a thread that can implement to multiple users.
 */

public class ServerWorker extends Thread {
    private final ServerSocket serverSocket;
    private final CustomLogger logger = CustomLogger.getSingleton();
    private AtomicInteger workerCounter = new AtomicInteger();

    public ServerWorker(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.setName("Server Socket Worker");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientWorker clientWorker = new ClientWorker(socket, workerCounter.getAndIncrement());
                clientWorker.start();
            } catch (IOException e) {
                logger.fatal(e.getMessage());
            } finally {
                if (serverSocket.isClosed()) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        logger.info("Closing server...");
                    }
                }
            }
        }
    }
}