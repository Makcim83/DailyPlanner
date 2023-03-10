import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteToFile {


    public static void createNewFile() throws FileNotFoundException {
        File file = new File("testFile");
        PrintWriter pw = new PrintWriter(file);

        pw.println("Test row 1 to file");
        pw.println("Test row 2 to file");

        pw.close();
    }
}
