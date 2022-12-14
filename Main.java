import java.util.*;
import java.util.stream.*;
import java.nio.file.*;

class Main {
    public static void main(String[] args) {
        MotionTracker motionTracker = new MotionTracker();

        try (Stream<String> stream = Files.lines(Paths.get("Input.txt"))) {
            stream.forEach(line -> motionTracker.applyMotion(line.charAt(0), Integer.parseInt(line.substring(2))));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        System.out.println(motionTracker.countSteps());
    }
}

class MotionTracker {
    HashSet<Point> traversedPoints = new HashSet<>();
    Point head = new Point(0, 0);
    Point one = new Point(0, 0);
    Point two = new Point(0, 0);
    Point three = new Point(0, 0);
    Point four = new Point(0, 0);
    Point five = new Point(0, 0);
    Point six = new Point(0, 0);
    Point seven = new Point(0, 0);
    Point eight = new Point(0, 0);
    Point nine = new Point(0, 0);

    public MotionTracker() {
    }

    void applyMotion(char direction, int num) {
        System.out.println("> move " + num + " to " + direction);
        
        for (int i = 0; i < num; i++) {
            head = head.move(direction);
            one = one.stepTowards(head);
            two = two.stepTowards(one);
            three = three.stepTowards(two);
            four = four.stepTowards(three);
            five = five.stepTowards(four);
            six = six.stepTowards(five);
            seven = seven.stepTowards(six);
            eight = eight.stepTowards(seven);
            nine = nine.stepTowards(eight);

            traversedPoints.add(nine);
        }
    }

    int countSteps() {
        return traversedPoints.size();
    }
}

record Point(int x, int y) {
    Point move(char direction) {
        return switch (direction) {
            case 'U' -> new Point(x, y + 1);
            case 'D' -> new Point(x, y - 1);
            case 'L' -> new Point(x - 1, y);
            case 'R' -> new Point(x + 1, y);
            default -> throw new IllegalStateException("Invalid direction: " + direction);
        };
    }

    Point stepTowards(Point point) {
        var distX = Math.abs(this.x - point.x);
        var distY = Math.abs(this.y - point.y);

        if (distX <= 1 && distY <= 1) {
            return this;
        } else if (distX == 0) {
            return point.y > this.y ? new Point(x, y + 1) : new Point(x, y - 1);
        } else if (distY == 0) {
            return point.x > this.x ? new Point(x + 1, y) : new Point(x - 1, y);
        } else if (distX == 2 && distY == 1) {
            return new Point((this.x + point.x)/2, point.y);
        } else if (distY == 2 && distX == 1) {
            return new Point(point.x, (this.y + point.y)/2);
        } else if (distX == 2 && distY == 2) {
            return new Point((this.x + point.x)/2, (this.y + point.y)/2);
        } else {
            throw new IllegalStateException("Cannot follow: from " + this + " to " + point);
        }
    }
}