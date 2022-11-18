package agh.ics.oop;

import agh.ics.oop.animals.Animal;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.types.RectangularMap;
import agh.ics.oop.moves.MoveDirection;
import agh.ics.oop.parsers.OptionsParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Lab04Test {
    @Test
    void mapTest() {
        // Given
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f","f", "f","f", "f"};
        AbstractWorldMap map = new RectangularMap(10, 5);
        MoveDirection[] directions =  OptionsParser.parse(moves);
        Vector2D[] positions = {new Vector2D(2,2), new Vector2D(3,4)};
        IEngine engine = new SimulationEngine(directions, map, positions);

        Animal animal1 = (Animal) map.objectAt(positions[0]);
        Animal animal2 = (Animal) map.objectAt(positions[1]);

        // When

        engine.run();

        // Then

        Assertions.assertEquals(new Vector2D(2,0), animal1.getPosition());
        Assertions.assertEquals(new Vector2D(3,4), animal2.getPosition());

    }
}
