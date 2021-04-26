package workers;

import database.UserDatabase;
import models.TransDto;
import models.User;
import utils.CommandHandler;
import utils.CustomLogger;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class ClientWorker extends Thread {
    private final Socket socket;
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;

    private final CommandHandler commandHandler = new CommandHandler(this);

    private final CustomLogger logger = CustomLogger.getSingleton();

    private volatile boolean isClientOnline = true;
    private User user;

    public ClientWorker(Socket socket, int workerId) throws IOException {
        this.setName("Client Worker #" + workerId);
        this.socket = socket;
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        UserDatabase userDatabase = UserDatabase.getSingleton();
        userDatabase.addSession(this);
        logger.info(socket.getRemoteSocketAddress() + " connected.");
        try {
            oos.writeObject("Welcome to the Chat Server!" + System.lineSeparator());
            oos.flush();
        } catch (Exception e) {
            isClientOnline = false;
        }
        while (isClientOnline) {
            try {
                TransDto transDto = (TransDto) ois.readObject();
                if(Objects.isNull(transDto)){
                    logger.info("读取输入流为空 ");
                    continue;
                }
                commandHandler.onCommand(transDto);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                isClientOnline = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
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

    public void write(TransDto dto) throws IOException {
        oos.writeObject(dto);
        oos.flush();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
