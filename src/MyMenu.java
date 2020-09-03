import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.*;

public class MyMenu extends JMenuBar {

    Map<Color, String> colors = new HashMap<>();

    SaveEngine se;
    OpenEngine oe;
    MyTextArea mta;
    MyFrame mf;

    JMenu file = new JMenu("File"), edit = new JMenu("Edit"), options = new JMenu("Options"),
            foreground = new JMenu("Foreground"), background = new JMenu("Background"),
            fontsize = new JMenu("Font size"), adr = new JMenu("Adresy"), setAdr = new JMenu("Ustaw Adres");

    JSeparator line = new JSeparator();

    JMenuItem open = new JMenuItem("Open", KeyEvent.VK_O), save = new JMenuItem("Save"),
            saveAs = new JMenuItem("Save as"), exit = new JMenuItem("Exit"), praca = new JMenuItem("Praca"),
            dom = new JMenuItem("Dom"), szkola = new JMenuItem("Szkola"), sPraca = new JMenuItem("Praca"),
            sDom = new JMenuItem("Dom"), sSzkola = new JMenuItem("Szkola");

    Action[] acts;
    boolean fileExists = false;
    File directory, currentFile;
    String name, adrDom = "dom", adrSzkola = "szkola", adrPraca = "praca";

    public MyMenu() {

        line.setForeground(Color.RED);
        mapColors();

        addEdit();
        addOptions();
        addFile();
    }

    public void mapColors() {
        colors.put(Color.BLUE, "Niebieski");
        colors.put(Color.YELLOW, "zowty");
        colors.put(Color.ORANGE, "Pomaranczowy");
        colors.put(Color.RED, "Czerwony");
        colors.put(Color.WHITE, "Bialy");
        colors.put(Color.BLACK, "Czarny");
        colors.put(Color.GREEN, "Zielony");

    }

    public void addBack() {

        for (Map.Entry<Color, String> entry : colors.entrySet()) {
            Color key = entry.getKey();
            String value = entry.getValue();

            JMenuItem jmi = new JMenuItem(value);
            jmi.setIcon(new ColorIcon(key));
            jmi.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            jmi.addActionListener(
                    (e) -> {
                        mta.setTABackground(key);
                    }
            );

            background.add(jmi);
        }
    }

    public void addFore() {

        for (Map.Entry<Color, String> entry : colors.entrySet()) {
            Color key = entry.getKey();
            String value = entry.getValue();

            JMenuItem jmi = new JMenuItem(value);
            jmi.setIcon(new ColorIcon(key));
            jmi.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            jmi.addActionListener(
                    (e) -> {
                        mta.setTAForeground(key);
                    }
            );

            foreground.add(jmi);
        }
    }

    public void addFSize() {

        for (int i = 8; i <= 24; i += 2) {
            final int size = i;
            JMenuItem jmi = new JMenuItem(i + " pts");
            jmi.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            jmi.addActionListener(
                    (e) -> {
                        mta.setTAFontSize(size);
                    }
            );

            fontsize.add(jmi);
        }

    }

    public void addEdit() {
        setActs1();

        dom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        acts[0].putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_D, java.awt.Event.CTRL_MASK | java.awt.Event.SHIFT_MASK));
        dom.setAction(acts[0]);
        dom.addActionListener(
                (e) -> {
                    mta.insert(adrDom, mta.getCaretPosition());
                }
        );
        adr.add(dom);

        szkola.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        acts[2].putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_S, java.awt.Event.CTRL_MASK | java.awt.Event.SHIFT_MASK));
        szkola.setAction(acts[2]);
        szkola.addActionListener(
                (e) -> {
                    mta.insert(adrSzkola, mta.getCaretPosition());
                }
        );
        adr.add(szkola);

        praca.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        acts[1].putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_P, java.awt.Event.CTRL_MASK | java.awt.Event.SHIFT_MASK));
        praca.setAction(acts[1]);
        praca.addActionListener(
                (e) -> {
                    mta.insert(adrPraca, mta.getCaretPosition());
                }
        );
        adr.add(praca);

        adr.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        edit.add(adr);

        sDom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sDom.addActionListener(
                (e) -> {
                    adrDom = JOptionPane.showInputDialog(mf, "Ustaw adres domowy");
                }
        );
        setAdr.add(sDom);

        sSzkola.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sSzkola.addActionListener(
                (e) -> {
                    adrSzkola = JOptionPane.showInputDialog(mf, "Ustaw adres szkoly");
                }
        );
        setAdr.add(sSzkola);

        sPraca.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sPraca.addActionListener(
                (e) -> {
                    adrPraca = JOptionPane.showInputDialog(mf, "Ustaw adres pracy");
                }
        );
        setAdr.add(sPraca);

        setAdr.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        edit.add(setAdr);
    }

    public void addOptions() {
        addBack();
        addFore();
        addFSize();

        foreground.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        options.add(foreground);

        background.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        options.add(background);

        fontsize.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        options.add(fontsize);
    }

    public void addFile() {
        setActs2();
        open.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        acts[0].putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, java.awt.Event.CTRL_MASK));
        open.setAction(acts[0]);
        open.setMnemonic(KeyEvent.VK_O);
        file.add(open);

        save.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        acts[1].putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
        save.setAction(acts[1]);
        save.setMnemonic(KeyEvent.VK_S);
        file.add(save);

        saveAs.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        acts[2].putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, java.awt.Event.CTRL_MASK));
        saveAs.setAction(acts[2]);
        saveAs.setMnemonic(KeyEvent.VK_A);
        file.add(saveAs);

        line.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        file.add(line);

        exit.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        acts[3].putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, java.awt.Event.CTRL_MASK));
        exit.setAction(acts[3]);
        exit.setMnemonic(KeyEvent.VK_X);
        file.add(exit);

        add(file);
        add(edit);
        add(options);
    }

    public void setActs1() {
        Action[] tmp = {

                new AbstractAction("Dom") {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("Dom");
                    }
                },

                new AbstractAction("Praca") {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("Praca");
                    }
                },

                new AbstractAction("Szkola") {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        System.out.println("Szkola");
                    }
                }
        };

        acts = tmp;
    }

    public void setActs2() {

        Action[] tmp = {

                new AbstractAction("Open") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Open");
                        oe = new OpenEngine();

                        JFileChooser fc = new JFileChooser();

                        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                        fc.setFileFilter(filter);

                        if (directory != null) {
                            fc.setCurrentDirectory(directory);
                        }

                        int returnValue = fc.showOpenDialog(null);

                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            directory = fc.getCurrentDirectory();
                            name = fc.getSelectedFile().toString();

                            try {
                                mta.getSource(oe.read(fc.getSelectedFile().toString()));
                                mf.setThisTitle(name);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                },

                new AbstractAction("Save") {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (fileExists == false) {
                            System.out.println("Save");

                            se = new SaveEngine();

                            JFileChooser fc = new JFileChooser();

                            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                            fc.setFileFilter(filter);

                            if (directory != null) {
                                fc.setCurrentDirectory(directory);
                            }

                            int returnValue = fc.showSaveDialog(null);

                            if (returnValue == JFileChooser.APPROVE_OPTION) {
                                directory = fc.getCurrentDirectory();
                                name = fc.getSelectedFile().toString();
                                se.setName(name);
                                se.setOutput(mta.getText());
                                mf.setThisTitle(name);

                                if (fc.getSelectedFile().exists()) {
                                    se.file = fc.getSelectedFile();
                                    se.saveText();
                                    directory = fc.getCurrentDirectory();
                                    currentFile = fc.getSelectedFile();
                                    fileExists = true;
                                } else {
                                    se.saveText();
                                    directory = fc.getCurrentDirectory();
                                    currentFile = fc.getSelectedFile();
                                    fileExists = true;
                                }
                            }
                        } else {

                            se = new SaveEngine();
                            se.setName(name);
                            se.setOutput(mta.getText());
                            se.file = currentFile;
                            se.saveText();
                        }
                    }
                },

                new AbstractAction("Save As") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Save As");

                        se = new SaveEngine();
                        JFileChooser fc = new JFileChooser();

                        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                        fc.setFileFilter(filter);

                        if (directory != null) {
                            fc.setCurrentDirectory(directory);
                        }

                        int returnValue = fc.showSaveDialog(null);

                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            directory = fc.getCurrentDirectory();
                            name = fc.getSelectedFile().toString();
                            se.setName(name);
                            se.setOutput(mta.getText());
                            mf.setThisTitle(name);

                            if (fc.getSelectedFile().exists()) {
                                se.file = fc.getSelectedFile();
                                se.saveText();
                                directory = fc.getCurrentDirectory();
                                currentFile = fc.getSelectedFile();
                                fileExists = true;
                            } else {

                                se.saveText();
                                directory = fc.getCurrentDirectory();
                                currentFile = fc.getSelectedFile();
                                fileExists = true;
                            }
                        }
                    }
                },

                new AbstractAction("Exit") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Exit");
                        System.exit(0);
                    }
                }
        };

        acts = tmp;
    }

    public void setMTA(MyTextArea mta) {
        this.mta = mta;
    }

    public void setMFrame(MyFrame mf) {
        this.mf = mf;
    }

}
