import view.LoginFrame;
import works.ChatClient;

public class main {
    public static void main(String[] args) throws InterruptedException {
        ChatClient chatClient = new ChatClient();
        chatClient.start();
    }
}
