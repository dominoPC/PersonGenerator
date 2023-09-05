import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.*;

public class ProductWriter {
    public static void main(String[] args) {
        List<String[]> products = new ArrayList<>(); //create an ArrayList to store records
        Scanner in = new Scanner(System.in); //Scanner used for SafeInput

        do { //runs once before checking for more records
            String[] record = new String[3]; //record holds a string for data elements

            //use SafeInput to get a name and add it to the record
            record[0] = SafeInput.getNonZeroLenString(in, "Please enter a name");
            //then do the same for description
            record[1] = SafeInput.getNonZeroLenString(in, "Please enter a description");
            //converts double to String
            record[2] = String.valueOf(SafeInput.getDouble(in, "Please enter a cost"));

            products.add(record); //adds the record to the products ArrayList
        } while(SafeInput.getYNConfirm(in, "Would you like to enter another record?")); //check to enter more records

        String filename = SafeInput.getNonZeroLenString(in, "Please enter a file name");

        try{
            File directory = new File(System.getProperty("user.dir")); //get the present working directory
            Path file = Paths.get(directory.getPath() + "\\src\\" + filename +".txt"); //use the specified filename
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            int id = 1; //used for the record ID
            for(String[] record:products){ //iterate through the ArrayList
                if(id != 1){ //enter a new line for each record that's not the first
                    writer.newLine();
                }
                writer.write(String.format("%06d", id)); //write the zero-padded ID
                id++;
                for(String datum: record){ //write the other records
                    writer.write("," + datum); //comma-separated
                }
            }
            writer.close(); //flush the stream
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}