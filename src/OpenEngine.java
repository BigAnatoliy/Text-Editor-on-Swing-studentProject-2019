import java.io.*;

public class OpenEngine {

    public OpenEngine() {

    }

    public String read(String file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textLine = bufferedReader.readLine(), output = "";

        do {
            output += textLine + "\n";

            textLine = bufferedReader.readLine();
        } while (textLine != null);

        bufferedReader.close();

        return output;
    }

}
