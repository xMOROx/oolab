package agh.ics.oop.parsers;

import agh.ics.oop.moves.MoveDirection;

public class OptionsParser {
    private static final String[] stringDirection= new String[]{"f", "forward", "b", "backward", "l", "left", "r", "right"};
    public static MoveDirection[] parse(String[] args) {
        int counter = 0;
        for (String arg: args) {
            arg = arg.toLowerCase();
            for (String stringDirect: stringDirection) {
                if(arg.equals(stringDirect)) {
                    counter++;
                    break;
                }
            }
        }

        MoveDirection[] animalMoves = new MoveDirection[counter];
        counter = 0;
        for (String arg: args) {
            arg = arg.toLowerCase();
            switch (arg) {
                case "f", "forward" -> {
                    animalMoves[counter] = MoveDirection.FORWARD;
                    counter++;
                }
                case "b", "backward" -> {
                    animalMoves[counter] = MoveDirection.BACKWARD;
                    counter++;
                }
                case "l", "left" -> {
                    animalMoves[counter] = MoveDirection.LEFT;
                    counter++;
                }
                case "r", "right" -> {
                    animalMoves[counter] = MoveDirection.RIGHT;
                    counter++;
                }
            }
        }
        return animalMoves;
    }
}
