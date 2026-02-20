import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class question_3 {

    public static void main(String[] args) {

        Path dir = Path.of("C:/Users/SimranSingh/Documents/Java IO/IO_Assignment/Question 3/Files folder");

        if (!Files.exists(dir)) {
            System.out.println("Directory does not exist: " + dir.toAbsolutePath());
            return;
        }
        Map<Long, List<Path>> sizeMap = new HashMap<>();
        try (Stream<Path> paths = Files.walk(dir)) {
            List<Path> files = paths.filter(Files::isRegularFile).toList();
            
            for (Path file : files){
                long size = Files.size(file);
                sizeMap.computeIfAbsent(size, k -> new ArrayList<>()).add(file);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        
        for (List<Path> sameSizeFiles : sizeMap.values()) {
            if (sameSizeFiles.size() > 1) {
                for (int i=0;i<sameSizeFiles.size();i++) {
                    for (int j=i+1;j<sameSizeFiles.size();j++) {

                        Path file1 = sameSizeFiles.get(i);
                        Path file2 = sameSizeFiles.get(j);

                        try {
                            if (compareFiles(file1, file2)) {

                                System.out.println("Duplicate Files Found:");
                                System.out.println("File 1: " + file1.getFileName());
                                System.out.println("File 2: " + file2.getFileName());
                                System.out.println("---------------------------");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static boolean compareFiles(Path p1, Path p2) throws IOException {

        try (InputStream isfile1 = Files.newInputStream(p1);
            InputStream isfile2 = Files.newInputStream(p2)) {

            byte[] buffer1 = new byte[4096];
            byte[] buffer2 = new byte[4096];

            int bytesRead1, bytesRead2;

            while ((bytesRead1 = isfile1.read(buffer1)) != -1) {

                bytesRead2 = isfile2.read(buffer2);

                if (bytesRead1 != bytesRead2)
                    return false;

                for (int i = 0; i < bytesRead1; i++) {
                    if (buffer1[i] != buffer2[i])
                        return false;
                }
            }
        }

        return true;
    }
}
