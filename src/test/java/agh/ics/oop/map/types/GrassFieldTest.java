package agh.ics.oop.map.types;

import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Vector2D;
import agh.ics.oop.objectsOnMap.Animal;
import agh.ics.oop.objectsOnMap.Grass;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.MapDirection;
import agh.ics.oop.moves.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void canMoveToEmptyCell() {
        AbstractWorldMap map = new GrassField(10);
        assertTrue(map.canMoveTo(new Vector2D(2, 2)));
    }

    @Test
    void canMoveToOccupiedCell() {
        AbstractWorldMap map = new GrassField(10);
        List<MoveDirection> directions = new ArrayList<>();

        directions.add(MoveDirection.FORWARD);

        Vector2D[] positions = {new Vector2D(2,2)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        assertFalse(map.canMoveTo(new Vector2D(2, 2)));
    }


    @Test
    void runWithOneAnimal() {
       List<MoveDirection> directions = new ArrayList<>();

       directions.add( MoveDirection.FORWARD);
       directions.add( MoveDirection.RIGHT);
       directions.add( MoveDirection.LEFT);
       directions.add( MoveDirection.FORWARD);
       directions.add( MoveDirection.FORWARD);


        AbstractWorldMap map = new GrassField(10);

        Vector2D[] positions = { new Vector2D(2,1)};
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
    void runWithTwoAnimals() {
       List<MoveDirection> directions = new ArrayList<>(List.of(MoveDirection.FORWARD,
               MoveDirection.BACKWARD,
               MoveDirection.RIGHT,
               MoveDirection.LEFT,
               MoveDirection.FORWARD,
               MoveDirection.FORWARD,
               MoveDirection.RIGHT,
               MoveDirection.RIGHT,
               MoveDirection.FORWARD,
               MoveDirection.FORWARD,
               MoveDirection.FORWARD,
               MoveDirection.FORWARD,
               MoveDirection.FORWARD));

        AbstractWorldMap map = new GrassField(10);


        Vector2D[] positions = { new Vector2D(2,1), new Vector2D(3, 3)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Vector2D animal1ExpectedPosition = new Vector2D(2, -1);
        Vector2D animal2ExpectedPosition = new Vector2D(3, 4);
        MapDirection animal1ExpectedDirection = MapDirection.SOUTH;
        MapDirection animal2ExpectedDirection = MapDirection.NORTH;

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
    void isOccupiedFalse() {
        var map = new GrassField(10);
        assertFalse(!(map.objectAt(new Vector2D(2, 2)) == null || map.objectAt(new Vector2D(2, 2) ) instanceof Grass)
                && map.isOccupied(new Vector2D(2, 2)));
    }

    @Test
    void isOccupiedTrue() {
        AbstractWorldMap map = new GrassField(10);
        List<MoveDirection> directions = new ArrayList<>();
        directions.add(MoveDirection.FORWARD);
        Vector2D[] positions = { new Vector2D(2,2)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        assertTrue(map.isOccupied(new Vector2D(2, 2)));
    }

    @Test
    void objectAtEmptyOrGrass() {
        AbstractWorldMap map = new RectangularMap(4, 4);

        assertTrue((map.objectAt(new Vector2D(2, 2)) == null) ||
                map.objectAt(new Vector2D(2, 2) ) instanceof Grass);
    }

}