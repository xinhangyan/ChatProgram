package works;

import listner.BaseListener;
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
    public static BufferedWriter writer;
    public static BufferedReader reader;
    public static Socket sock;
    public static User user;
    public static HashMap<String, BaseListener> listenerMap = new HashMap<>();


    private void handle(InputStream input, OutputStream output) throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        System.out.println("[server] " + reader.readLine());
        AllFrame.loginFrame = new LoginFrame();
        for (;;) {
            String line = reader.readLine();
            String[] args = line.split("\\|");
            BaseListener baseListener = listenerMap.get(args[0]);
            if(Objects.nonNull(baseListener)){
                baseListener.callBack(args[1]);
            }
            System.out.println("callback:"+line);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
