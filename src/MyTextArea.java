import java.awt.*;

import javax.swing.*;

public class MyTextArea extends JTextArea {

    String fontName = this.getName();

    public MyTextArea() {

    }

    public void setTAForeground(Color col) {
        setForeground(col);
    }

    public void setTABackground(Color col) {
        setBackground(col);
    }

    public void setTAFontSize(int size) {
        setFont(new Font(fontName, Font.PLAIN, size));
    }

    public void getSource(String source) {
        setText(source);
    }

}
