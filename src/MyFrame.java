import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;


public class MyFrame extends JFrame {

    public MyFrame() {

        setTitle("Prosty Edytor: ");

        JPanel p = new JPanel();


        BorderLayout bl = new BorderLayout();
        setLayout(bl);
        setPreferredSize(new Dimension(800, 500));
        //setLayout(new GridLayout(4,1,3,3));

        MyMenu mm = new MyMenu();
        add(mm, BorderLayout.PAGE_START);

        MyTextArea mta = new MyTextArea();

        JScrollPane jsp = new JScrollPane(mta);
        //add(jsp, BorderLayout.LINE_END);

        add(jsp);


        mm.setMTA(mta);
        mm.setMFrame(this);

    }

    public void setThisTitle(String title) {
        setTitle("Prosty Edytor: " + title);
    }
}
