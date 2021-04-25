package works;

import view.LoginFrame;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient extends Thread{
    public static BufferedWriter writer;
    public static BufferedReader reader;
    private void handle(InputStream input, OutputStream output) throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        System.out.println("[server] " + reader.readLine());
        for (;;) {
            System.out.print(">>> "); // 打印提示
            String s = scanner.nextLine(); // 读取一行输入
            writer.write(s);
            writer.newLine();
            writer.flush();
            System.out.println("<<< " + reader.readLine());
        }
//        writer.write("login 123 123");
//        writer.newLine();
//        writer.flush();
//        System.out.println(reader.readLine());
//        new LoginFrame();
    }

    @Override
    public void run() {
        Socket sock = null; // 连接指定服务器和端口
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
        System.out.println("disconnected.");
    }
}
