import java.util.*;
import java.util.stream.*;
import java.nio.file.*;

class Main {
    public record Point(int x, int y) {
    }

    public static void main(String[] args) {

        HashSet<Point> traversedPoints = new HashSet<>();

        try (Stream<String> stream = Files.lines(Paths.get("Input.txt"))) {
            stream.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }
}