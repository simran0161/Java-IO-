import java.io.IOException;
import java.nio.file.*;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Pair {
    Path path;
    long size;

    Pair(Path path, long size) {
        this.path = path;
        this.size = size;
    }

    public long size() {
        return size;
    }

    public Path path() {
        return path;
    }
}

public class Question_2 {
    public static void main(String[] args) throws IOException {
        Path inputpath = Path.of("Files folder");
        // Files.walk(inputpath)
        // .forEach(p -> System.out.println(p));

        long fileCount = Files.walk(inputpath).filter(Files::isRegularFile).count();
        System.out.println("Total files: " + fileCount);

        long directoryCount = Files.walk(inputpath).filter(Files::isDirectory).count();
        System.out.println("Total Directory: " + directoryCount);

        long total_file_size = Files.walk(inputpath).filter(Files::isRegularFile).mapToLong(file -> {
            try {
                return Files.size(file);
            } catch (IOException e) {
                return 0;
            }
        }).sum();
        System.out.println("Total size: " + total_file_size);

        Path filepath = Path.of("report.txt");
        Files.writeString(filepath,
                "Total files: " + fileCount + "\n" + "Total Directory: " + directoryCount + "\n" + "Total size: "
                        + total_file_size + "\n",
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(b.size(), a.size()));
        try (Stream<Path> paths = Files.walk(inputpath)) {
            paths.filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            long len = Files.size(path);
                            Pair p = new Pair(path, len);
                            pq.add(p);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
        int i = 0;
        while (!pq.isEmpty() && i < 5) {
            Pair pair = pq.poll();
            Files.writeString(filepath, "File Name: " + pair.path().getFileName() + " has size " + pair.size() + "\n",
                    StandardOpenOption.APPEND);
            i++;
        }

    }
}
