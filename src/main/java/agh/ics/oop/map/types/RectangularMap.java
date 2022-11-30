package agh.ics.oop.map.types;

import agh.ics.oop.objectsOnMap.Animal;
import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.moves.MoveDirection;

import java.util.HashMap;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private final HashMap<Vector2D, IMapElement> map;

    public RectangularMap(int width, int height) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Szerokość i wysokość mapy nie może być ujemna");

        smallestCords = new Vector2D(0, 0);
        greatestCords = new Vector2D(width - 1, height - 1);

        map = new HashMap<>();
        mapAnimator.addFrame(this);
    }

    @Override
    public boolean canMoveTo(Vector2D position) {
        return position.follows(smallestCords) && position.precedes(greatestCords) && !isOccupied(position);
    }

    @Override
    protected void addElementToMap(IMapElement element) {
        map.put(element.getPosition(), element);

        if (element instanceof Animal ) {
            mapElements.put(element.getPosition(), (Animal) element);
            ((Animal)element).addObserver(this);
        }

    }

    @Override
    public void moveAnimal(Animal animal, MoveDirection direction) {
        map.remove(animal.getPosition());
        animal.move(direction);
        map.put(animal.getPosition(), animal);
    }

    @Override
    public Object objectAt(Vector2D position) {
        return this.mapElements.get(position);

    }

}
