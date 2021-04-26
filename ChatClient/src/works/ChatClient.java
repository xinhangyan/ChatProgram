package works;

import listner.BaseListener;
import models.TransDto;
import models.User;
import view.AllFrame;
import view.LoginFrame;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class ChatClient extends Thread{
    public static ObjectInputStream ois;
    public static ObjectOutputStream oos;
    public static Socket sock;
    public static User user;
    public static HashMap<String, BaseListener> listenerMap = new HashMap<>();


    private void handle(InputStream input, OutputStream output) throws IOException, ClassNotFoundException {
        oos = new ObjectOutputStream(output);
        ois = new ObjectInputStream(input);
        System.out.println("[server] " +  ois.readObject());
        AllFrame.loginFrame = new LoginFrame();
        for (;;) {
            TransDto transDto = (TransDto) ois.readObject();
            BaseListener baseListener = listenerMap.get(transDto.getTarget());
            if(Objects.nonNull(baseListener)){
                baseListener.callBack(transDto);
            }
            System.out.println("callback:"+transDto.toString());
        }

    }

    @Override
    public void run() {
        try {
            sock = new Socket("localhost", 6667);
            try (InputStream input = sock.getInputStream()) {
                try (OutputStream output = sock.getOutputStream()) {
                    handle(input, output);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
