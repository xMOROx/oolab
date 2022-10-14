package agh.ics.oop;


import java.util.Arrays;


public class World {
    public static void run(Direction[] arguments) {
        for (Direction argument : arguments) {

            String message = switch (argument) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak skręca w prawo";
                case LEFT -> "Zwierzak skręca w lewo";
                default -> "";
            };
            if (message.length() != 0) {
                System.out.println(message);
            }

        }
    }

    private static Direction[] copyArray(Direction[] moves) {
//        if (moves.length == 0 ) {
//            return Arrays.copyOf(moves, 2);
//        }
        return Arrays.copyOf(moves, moves.length +1);
    }

    private static Direction[] StringToEnum(String[] args) {

        Direction[] Moves = {};
        int size = -1;

        for (String argument: args) {
            size++;

            if(Moves.length <= size) {
                Moves = copyArray(Moves);
            }

            Direction direction = switch(argument) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> Direction.DEFAULT;
            };
            Moves[size] = direction;
        }

        return Moves;
    }

    public static void main(String[] args ) {

        Direction[] Moves = StringToEnum(args);
        System.out.println("Start");
        run(Moves);
        System.out.println("Stop");
    }
}
