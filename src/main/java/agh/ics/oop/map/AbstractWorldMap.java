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

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected MapAnimator mapAnimator;
    protected MapVisualizer mapVisualiser;
    protected Map<Vector2D,IMapElement> mapElements;
    protected Vector2D smallestCords;
    protected Vector2D greatestCords;

    public AbstractWorldMap() {
        this.mapElements = new HashMap<>();
        this.mapVisualiser = new MapVisualizer(this);
        this.mapAnimator = new MapAnimator();
    }

    protected abstract void addElementToMap(IMapElement element);

    public abstract void moveAnimal(Animal animal, MoveDirection direction);

//    public Map<Vector2D, Animal> getAnimals() {
//        return this.mapElements;
//    }
    @Override
    public boolean place(@NotNull IMapElement element) {
        if (!canMoveTo(element.getPosition()))
            return false;

        addElementToMap(element);
        return true;
    }

    @Override
    public void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        Animal animal = (Animal) this.mapElements.get(oldPosition);

        this.mapElements.remove(oldPosition);
        this.mapElements.put(newPosition, animal);
    }


    @Override
    public boolean isOccupied(Vector2D position) {
        return objectAt(position) != null;
    }

    @Override
    public String toString() {
        return mapVisualiser.draw(smallestCords, greatestCords);
    }

    public MapAnimator getMapAnimator() {
        return mapAnimator;
    }

}
