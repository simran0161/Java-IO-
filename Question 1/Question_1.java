import java.io.IOException;
import java.nio.file.*;

public class Question_1 {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("application.log");

        // Files.writeString(path,
        //         "INFO User logged in successfully\n" +
        //                 "WARN Disk space running low\n" +
        //                 "ERROR Failed to connect to database\n" +
        //                 "INFO Data processed\n" +
        //                 "INFO Configuration loaded\n" +
        //                 "WARN Memory usage high\n" +
        //                 "ERROR Null pointer exception\n" +
        //                 "INFO Request completed\n" +
        //                 "WARN Deprecated API used\n" +
        //                 "ERROR File not found\n" +
        //                 "INFO User session started\n" +
        //                 "INFO Cache cleared\n" +
        //                 "WARN Slow response time detected\n" +
        //                 "ERROR Index out of range\n" +
        //                 "INFO Job completed",
        //         StandardCharsets.UTF_8);

        String content=Files.readString(path);
        String[] lines=content.split("\n");
        int INFO=0;
        int ERROR=0;
        int WARN=0;

        Path errorfilepath=Path.of("error.log");

        for(String line:lines){
            if(line.contains("INFO")){
                INFO++;
            }
            else if(line.contains("ERROR")){
                ERROR++;
                Files.writeString(errorfilepath, line+"\n", StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
            }
            else if(line.contains("WARN")){
                WARN++;
            }
        }

        // String result = "INFO: " + INFO + "\nERROR: " + ERROR + "\nWARN: " + WARN;
        // Path filepath=Path.of("summary.txt");
        // Files.writeString(filepath, result, StandardCharsets.UTF_8);
        
        
    }
}
