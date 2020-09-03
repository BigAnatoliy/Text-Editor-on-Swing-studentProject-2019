import javax.swing.JFrame;

public class Zadanie18 {

    public static void main(String[] args) {
        createGUI();
    }

    public static void createGUI() {

        // utworzenie okna
        MyFrame jf = new MyFrame();

        // obsluga zamkniecia okna JFrame
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // okreslenie polojenia okna
        jf.setLocation(50, 50);

        // uniemozliwienie zmiany rozmiarow okna
        jf.setResizable(true);

        //utworzenie obszaru

        // upakowanie okna
        jf.pack();

        // wyswietlenie okna
        jf.setVisible(true);
    }

}
