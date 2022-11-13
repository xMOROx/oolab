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
//        System.out.println("Start");
//
//        List<String> Moves = Arrays.asList(args);
//
//        Moves
//                .stream()
//                .map(World::StringToDirection)
//                .map(World::output)
//                .filter(e -> !e.equals(""))
//                .forEach(System.out::println);
//
//        System.out.println("Stop");
//        Vector2D position1 = new Vector2D(1,2);
//        System.out.println(position1);
//        Vector2D position2 = new Vector2D(-2, 1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));

//        MapDirection direction1 = MapDirection.NORTH;
//        MapDirection direction2 = MapDirection.NORTH;
//
//        System.out.println(direction1.toString());
//        System.out.println(direction1.next());
//        System.out.println(direction1.previous());
//        System.out.println(direction1.toUnitVector());
//        System.out.println(direction1.equals(direction2));


        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2D[] positions = { new Vector2D(2,2), new Vector2D(3,4), new Vector2D(2,2), new Vector2D(0,0) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

    }
}
