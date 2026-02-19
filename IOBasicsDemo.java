import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class IOBasicsDemo {
    public static void main(String[] args) throws IOException {
        Path inputpath = Path.of("Input.txt");
        //Path is a interface which is used to represent a file or directory path in file system.
        // Path.of come with Java 11+ and it is a static factory method which is used to create a Path.
        // Here is a inputpath object that represents a file named Input.txt in the current directory

        Files.writeString(inputpath,
                "My name is Simran Singh!", //character sequence 
                StandardCharsets.UTF_8);
        // Files is the utility class that provides some static methods for operations like read, write,copy, delete,etc.
        // Here we are using writeString method to write a string to the file represented by inputpath.

        //Here writeString is a method which writes the data into the file that has directory defined by inputdata.
        //If the file doesn't exist, it will be created. If it already exists, it will be overwritten.
        
        //character encoding used to convert the string into bytes(UTF_8)
    }
}
