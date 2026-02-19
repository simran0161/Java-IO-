import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

public class IO_writedata {
    public static void main(String[] args) throws IOException {
        Path inputpath=Path.of("file1.txt");

        Files.writeString(inputpath,
            "This is the file 1",
            StandardCharsets.UTF_8  
        );
    }
}
