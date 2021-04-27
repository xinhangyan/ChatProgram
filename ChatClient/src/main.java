import works.ChatClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class main {
    public static void main(String[] args) throws InterruptedException, IOException {
        ChatClient chatClient = new ChatClient();
        chatClient.start();
//        JFrame jFrame = new JFrame();
//        jFrame.setBounds(100,100,500,300);//设置窗体坐标和大小
//        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);//设置窗体关闭规则，关闭窗口时关闭程序
//Container c=jFrame.getContentPane();
//JTextArea area=new JTextArea("1233333333333");//文本域
//JScrollPane sp=new JScrollPane(area);//创建滚动面板，给文本域添加滚动条
//c.add(sp);//容器添加滚动面板
//        jFrame.setVisible(true);//设置窗体可见

    }
}
