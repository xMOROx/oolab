package agh.ics.oop.map;


import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.animals.Animal;
import agh.ics.oop.moves.MoveDirection;


import java.util.LinkedList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {

    protected MapAnimator mapAnimator;
    protected MapVisualizer mapVisualiser;
    protected List<Animal> animals;
    protected Vector2D smallestCords;
    protected Vector2D greatestCords;

    public AbstractWorldMap() {
        this.animals = new LinkedList<>();
        this.mapVisualiser = new MapVisualizer(this);
        this.mapAnimator = new MapAnimator();
    }

    protected abstract void addElementToMap(IMapElement element);

    public abstract void moveAnimal(Animal animal, MoveDirection direction);

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition()))
            return false;

        addElementToMap(animal);
        mapAnimator.addFrame(this);
        return true;
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
