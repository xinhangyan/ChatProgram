package view;

import javax.swing.*;
import java.awt.*;

/**
 *  This class will show a dialog after clicking specific buttons.
 */

public class BaseDialog extends JDialog {
    private String text;
    public BaseDialog() {
        init();
    }

    public BaseDialog(String text) {
        this.text = text;
        init();
    }

    void init(){
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds((screenWidth - 400)/2+400,(screenHeight-300)/2+100,300,200);
        setLayout(new FlowLayout());
        add(new Label(text));
        setVisible(true);
    }


}
