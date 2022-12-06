package agh.ics.oop.map;


import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.objectsOnMap.Animal;
import agh.ics.oop.moves.MoveDirection;
import org.jetbrains.annotations.NotNull;


import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap  implements IWorldMap, IPositionChangeObserver {

    protected final MapAnimator mapAnimator;
    protected final MapVisualizer mapVisualiser;
    protected final Map<Vector2D,IMapElement> mapElements;

    protected final MapBoundary mapBoundary;

    public AbstractWorldMap() {
        this.mapElements = new HashMap<>();
        this.mapVisualiser = new MapVisualizer(this);
        this.mapAnimator = new MapAnimator();
        this.mapBoundary = new MapBoundary();
        this.mapBoundary.addPosition(new Vector2D(0, 0)); // zadeklarowane na potrzeby działania animatora

    }

    protected abstract void addElementToMap(IMapElement element);

    public abstract void moveAnimal(Animal animal, MoveDirection direction);


    @Override
    public boolean place(@NotNull IMapElement element) throws IllegalArgumentException {
        if (!canMoveTo(element.getPosition()))
             throw new IllegalArgumentException("Position " + element.getPosition() + " is not allowed to put animal!" );

        addElementToMap(element);
        return true;
    }

    @Override
    public void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        Animal animal = (Animal) this.mapElements.get(oldPosition);
        this.mapElements.remove(oldPosition);
        this.mapBoundary.removePosition(oldPosition);
        this.mapBoundary.addPosition(newPosition);
        this.mapElements.put(newPosition, animal);
    }


    @Override
    public boolean isOccupied(Vector2D position) {
        return objectAt(position) != null;
    }

    @Override
    public String toString() {
        return mapVisualiser.draw(this.mapBoundary.getMapBounds()[0], this.mapBoundary.getMapBounds()[1]);
    }

    public abstract Vector2D[] getMapBounds();

    public MapAnimator getMapAnimator() {
        return mapAnimator;
    }

}
