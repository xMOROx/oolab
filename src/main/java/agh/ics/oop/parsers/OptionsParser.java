package agh.ics.oop.parsers;

import agh.ics.oop.moves.MoveDirection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    private static final String[] stringDirection= new String[]{"f", "forward", "b", "backward", "l", "left", "r", "right"};
    public static List<MoveDirection> parse(String[] args) throws IllegalArgumentException {
        boolean wrongArgumentFlag = true;

        for (String arg: args) {
            arg = arg.toLowerCase();
            for (String stringDirect: stringDirection) {
                if(arg.equals(stringDirect)) {
                    wrongArgumentFlag = false;
                    break;
                }
            }
            if(wrongArgumentFlag) {
                throw new IllegalArgumentException(arg + " is not legal move specification");
            }
            wrongArgumentFlag = true;
        }

        List<MoveDirection> animalMoves = new ArrayList<>();
        for (String arg: args) {
            arg = arg.toLowerCase();
            switch (arg) {
                case "f", "forward" -> {
                    animalMoves.add(MoveDirection.FORWARD);

                }
                case "b", "backward" -> {
                    animalMoves.add(MoveDirection.BACKWARD);

                }
                case "l", "left" -> {
                    animalMoves.add(MoveDirection.LEFT);
                }
                case "r", "right" -> {
                    animalMoves.add(MoveDirection.RIGHT);
                }
            }
        }
        return animalMoves;
    }
}
