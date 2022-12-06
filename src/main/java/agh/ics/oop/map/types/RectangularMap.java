package agh.ics.oop.map.types;

import agh.ics.oop.objectsOnMap.Animal;
import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.moves.MoveDirection;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private final HashMap<Vector2D, IMapElement> map;

    public RectangularMap(int width, int height) {
        if (width < 0 || height < 0)
            throw new IllegalArgumentException("Szerokość i wysokość mapy nie może być ujemna");

        this.mapBoundary.addPosition(new Vector2D(width - 1, height - 1));
        map = new HashMap<>();
        mapAnimator.addFrame(this);
    }

    @Override
    public boolean canMoveTo(@NotNull Vector2D position) {
        return position.follows(this.mapBoundary.getMapBounds()[0]) && position.precedes(this.mapBoundary.getMapBounds()[1]) && !isOccupied(position);
    }

    @Override
    public Vector2D[] getMapBounds() {
        return this.mapBoundary.getMapBounds();
    }

    @Override
    protected void addElementToMap(IMapElement element) {

        this.map.put(element.getPosition(), element);
        this.mapBoundary.addPosition(element.getPosition());

        this.mapElements.put(element.getPosition(), (Animal) element);

        ((Animal)element).addObserver(this);


    }

    @Override
    public void moveAnimal(Animal animal, MoveDirection direction) {
        this.map.remove(animal.getPosition());
        animal.move(direction);

        this.map.put(animal.getPosition(), animal);
    }

    @Override
    public Object objectAt(Vector2D position) {
        return this.mapElements.get(position);

    }

}
