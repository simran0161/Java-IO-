import java.io.IOException;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.charset.StandardCharsets;

class OveridingNotAllowed extends Exception {
    public OveridingNotAllowed(String message) {
        super(message);
    }
}

public class IO_readdata {
    public static void main(String[] args) throws IOException {
        Path inputpath = Path.of("file3.txt");

        try {
            if (Files.exists(inputpath) && Files.size(inputpath) > 0) {
                throw new OveridingNotAllowed("Overriding the file is not allowed");
            }

            Files.writeString(inputpath,
                    "Java is one of the most popular programming languages, and many developers choose Java because it is versatile, secure, and platform‑independent. Whether you are building web applications, mobile apps, or enterprise systems, Java provides powerful libraries and tools to simplify development. Learning Java also helps beginners understand core programming concepts that apply to many other languages.",
                    StandardCharsets.UTF_8);

            String content = Files.readString(inputpath);
            
            Pattern pattern = Pattern.compile("\\bJava\\b");
            Matcher matcher = pattern.matcher(content);

            int count = 0;
            while (matcher.find()) {
                count++;
            }

            System.out.println("This file contains data: " + content);
            System.out.println("The occurence of word Java is: "+count);
            
        } catch (OveridingNotAllowed e) {
            System.out.println("Exception occurs: "+e.getMessage());
        }

    }
}
