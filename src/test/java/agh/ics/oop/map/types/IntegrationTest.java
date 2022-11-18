package agh.ics.oop.map.types;

import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Vector2D;
import agh.ics.oop.animals.Animal;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.MapDirection;
import agh.ics.oop.moves.MoveDirection;
import agh.ics.oop.parsers.OptionsParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class IntegrationTest {

    @Test
    void emptyArrayWithOneAnimal() {
        MoveDirection[] directions = OptionsParser.parse(new String[] { });
        AbstractWorldMap map = new GrassField(10);


        Vector2D animalExpectedPosition = new Vector2D(2, 2);
        Vector2D[] positions = { animalExpectedPosition};

        MapDirection animalExpectedDirection = MapDirection.NORTH;
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertNotNull(map.objectAt(animalExpectedPosition));
        Animal animal = (Animal) map.objectAt(animalExpectedPosition);
        assertEquals(animalExpectedPosition, animal.getPosition());
        assertEquals(animalExpectedDirection, animal.getOrientation());
    }

    @Test
    void emptyArrayWithTwoAnimals() {
        MoveDirection[] directions = OptionsParser.parse(new String[] { });
        AbstractWorldMap map = new GrassField(10);

        Vector2D animal1ExpectedPosition = new Vector2D(2, 2);
        Vector2D animal2ExpectedPosition = new Vector2D(3, 4);
        MapDirection animal1ExpectedDirection = MapDirection.NORTH;
        MapDirection animal2ExpectedDirection = MapDirection.NORTH;

        Vector2D[] positions = { animal1ExpectedPosition, animal2ExpectedPosition };
        IEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();
        assertNotNull(map.objectAt(animal1ExpectedPosition));
        assertNotNull(map.objectAt(animal2ExpectedPosition));

        Animal animal1 = (Animal) map.objectAt(animal1ExpectedPosition);
        Animal animal2 = (Animal) map.objectAt(animal2ExpectedPosition);

        assertEquals(animal1ExpectedPosition, animal1.getPosition());
        assertEquals(animal2ExpectedPosition, animal2.getPosition());
        assertEquals(animal1ExpectedDirection, animal1.getOrientation());
        assertEquals(animal2ExpectedDirection, animal2.getOrientation());
    }


    @Test
    void arrayTestSequenceWithOneAnimal() {
        MoveDirection[] directions = OptionsParser.parse(
                new String[] { "f", "b", "r", "l", "f", "f" }
        );

        AbstractWorldMap map = new GrassField(10);

        Vector2D[] positions = { new Vector2D(2,2)};
        IEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();

        Vector2D animalExpectedPosition = new Vector2D(2, 4);
        MapDirection animalExpectedDirection = MapDirection.NORTH;

        assertNotNull(map.objectAt(animalExpectedPosition));

        Animal animal = (Animal) map.objectAt(animalExpectedPosition);

        assertEquals(animalExpectedPosition, animal.getPosition());
        assertEquals(animalExpectedDirection, animal.getOrientation());
    }

    @Test
    void arrayTestSequenceWithTwoAnimals() {
        MoveDirection[] directions = OptionsParser.parse(
                new String[] { "f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f" }
        );
        AbstractWorldMap map = new GrassField(10);


        Vector2D[] positions = { new Vector2D(2,2), new Vector2D(3,4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2D animal1ExpectedPosition = new Vector2D(2, -1);
        Vector2D animal2ExpectedPosition = new Vector2D(3, 7);
        MapDirection animal1ExpectedDirection = MapDirection.SOUTH;
        MapDirection animal2ExpectedDirection = MapDirection.NORTH;

        assertNotNull( map.objectAt(animal1ExpectedPosition));
        assertNotNull( map.objectAt(animal2ExpectedPosition));

        Animal animal1 = (Animal)map.objectAt(animal1ExpectedPosition);
        Animal animal2 = (Animal)map.objectAt(animal2ExpectedPosition);

        assertEquals(animal1ExpectedPosition, animal1.getPosition());
        assertEquals(animal2ExpectedPosition, animal2.getPosition());
        assertEquals(animal1ExpectedDirection, animal1.getOrientation());
        assertEquals(animal2ExpectedDirection, animal2.getOrientation());
    }


    @Test
    void randomText() {
        MoveDirection[] directions = OptionsParser.parse(new String[] { "alamakota" });
        AbstractWorldMap map = new GrassField(10);
        var animal = new Animal(map);



        var expectedDirection = MapDirection.NORTH;
        var expectedPosition = new Vector2D(2, 2);

        Vector2D[] positions = { expectedPosition };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertEquals(expectedDirection, animal.getOrientation());
        assertEquals(expectedPosition, animal.getPosition());
    }
}
