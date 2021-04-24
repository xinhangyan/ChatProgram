import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient extends Thread{
    private void handle(InputStream input, OutputStream output) throws IOException {
        var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(System.in);
        System.out.println("[server] " + reader.readLine());
        for (;;) {
            System.out.print(">>> "); // 打印提示
            String s = scanner.nextLine(); // 读取一行输入
            writer.write(s);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            if (resp.equals("bye")) {
                break;
            }
        }
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
