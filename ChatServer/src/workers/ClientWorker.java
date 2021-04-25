package workers;

import database.UserDatabase;
import models.User;
import utils.CommandHandler;
import utils.CustomLogger;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ClientWorker extends Thread {
    private final Socket socket;
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;
    private final CommandHandler commandHandler = new CommandHandler(this);

    private final CustomLogger logger = CustomLogger.getSingleton();

    private volatile boolean isClientOnline = true;
    private User user;

    public ClientWorker(Socket socket, int workerId) throws IOException {
        this.setName("Client Worker #" + workerId);
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        UserDatabase userDatabase = UserDatabase.getSingleton();
        userDatabase.addSession(this);
        logger.info(socket.getRemoteSocketAddress() + " connected.");
        try {
            bufferedWriter.write("Welcome to the Chat Server!" + System.lineSeparator());
            bufferedWriter.flush();
        } catch (Exception e) {
            isClientOnline = false;
        }
        while (isClientOnline) {
            try {
                String line = bufferedReader.readLine();
                if(Objects.isNull(line)){
                    logger.info("读取输入流为空 ");
                    continue;
                }
                commandHandler.onCommand(line);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                isClientOnline = false;
            }
        }

        try {
            socket.close();
        } catch (IOException ignored) {
        } finally {
            userDatabase.removeSession(this);
            String addressOrUser = user != null ? user.getUsername() : String.valueOf(socket.getRemoteSocketAddress());
            logger.info(addressOrUser + " has logged off.");
        }
    }

    public String getRemoteAddress() {
        return socket.getRemoteSocketAddress().toString();
    }

    public void setClientOnline(boolean clientOnline) {
        isClientOnline = clientOnline;
    }

    public Socket getSocket() {
        return socket;
    }

    public void writeLine(String line) throws IOException {
        bufferedWriter.write(line + System.lineSeparator());
        bufferedWriter.flush();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
