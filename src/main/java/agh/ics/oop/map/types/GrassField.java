package agh.ics.oop.map.types;

import agh.ics.oop.Vector2D;
import agh.ics.oop.objectsOnMap.Animal;
import agh.ics.oop.objectsOnMap.Grass;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.moves.MoveDirection;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int numberOfGrasses;
    private final int SEED = 2137;

    public GrassField(int numberOfGrasses) {
        this.numberOfGrasses = numberOfGrasses;

        this.mapAnimator.addFrame(this);
    }

    protected void addGrass() {
        var random = new Random(SEED);
        var bound = (int) Math.sqrt(10 * numberOfGrasses) + 1;
        Vector2D position;

        do {
            position = new Vector2D(random.nextInt(bound), random.nextInt(bound));
        } while (isOccupied(position));
        addElementToMap(new Grass(position));
    }


    @Override
    protected void addElementToMap(@NotNull IMapElement element) {
        this.mapBoundary.addPosition(element.getPosition());
        this.mapElements.put(element.getPosition(), element);

        if(element instanceof Animal) {
            ((Animal) element).addObserver(this);
        }

        this.mapAnimator.addFrame(this);
    }

    @Override
    public void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        Animal animal = (Animal) this.mapElements.get(oldPosition);
        boolean removedGrass = false;
        if(this.mapElements.get(newPosition) instanceof Grass) {
            this.mapElements.remove(newPosition);
            removedGrass = true;
        }

        this.mapElements.remove(oldPosition);
        this.mapElements.put(newPosition, animal);
        this.mapBoundary.positionChanged(oldPosition, newPosition);
        if(removedGrass) {
            addGrass(); // replant grass
        }
    }

    @Override
    public void moveAnimal(Animal animal, MoveDirection direction) {
        animal.move(direction);
    }


    @Override
    public boolean canMoveTo(Vector2D position) {
        return !(this.mapElements.get(position) instanceof Animal);
    }

    @Override
    public Object objectAt(Vector2D position) {

        return this.mapElements.get(position);
    }


    @Override
    public Vector2D[] getMapBounds() {
        return this.mapBoundary.getMapBounds();
    }

    public void addGrasses() {
        for (int i = 0; i < numberOfGrasses; i++) {
            this.addGrass();
        }
    }
}
