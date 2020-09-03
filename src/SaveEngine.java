import java.io.*;

public class SaveEngine {

    String output, name;
    File file;
    FileWriter writer;
    boolean fileExists;

    public SaveEngine() {
        file = null;
        fileExists = false;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void saveText() {

        if (file == null) {
            createFile(name);
            try {
                writer = new FileWriter(file);
                writer.write(output);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                writer = new FileWriter(file);
                writer.write(output);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void createFile(String name) {
        file = new File(name + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
