import java.io.*;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;
import javax.swing.JFileChooser;

public class PersonReader {
    public static void main(String[] args) {
        try{
            JFileChooser chooser = new JFileChooser();
            File directory = new File(System.getProperty("user.dir")); //get the present working directory
            chooser.setCurrentDirectory(directory); //sets the default directory
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){ //prompt to select a file
                //create a buffered input stream from the chosen file
                File selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                System.out.println("ID#    | First Name | Last Name | Title | YOB");
                System.out.println("-------+------------+-----------+-------+------");
                while(reader.ready()){
                    String line = reader.readLine(); //read each line in the file
                    String[] data = line.split(","); //create a String array from the line delineated by commas

                    System.out.print(data[0] + " |"); //print ID
                    System.out.print(String.format(" %-11s", data[1]) + "|"); //first name
                    System.out.print(String.format(" %-10s", data[2]) + "|"); //last name
                    System.out.print(String.format(" %-6s", data[3]) + "|"); //title
                    System.out.println(" " + data[4]); //YOB
                }
                reader.close();
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}