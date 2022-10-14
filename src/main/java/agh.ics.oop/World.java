package agh.ics.oop;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class World {

    private static Direction StringToDirection(String input) {
        return switch (input) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            case "l" -> Direction.LEFT;
            default -> Direction.DEFAULT;
        };
    }
    private  static String output(Direction input) {
        return  switch (input) {
            case FORWARD -> "Zwierzak idzie do przodu";
            case BACKWARD -> "Zwierzak idzie do tylu";
            case RIGHT -> "Zwierzak skreca w prawo";
            case LEFT -> "Zwierzak skreca w lewo";
            default -> "";
        };
    }
    public static void main(String[] args ) {
        System.out.println("Start");

        List<String> Moves = Arrays.asList(args);

        Moves
                .stream()
                .map(World::StringToDirection)
                .map(World::output)
                .filter(e -> !e.equals(""))
                .forEach(System.out::println);

        System.out.println("Stop");
    }
}
