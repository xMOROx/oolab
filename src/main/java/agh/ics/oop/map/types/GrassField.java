package agh.ics.oop.map.types;

import agh.ics.oop.Vector2D;
import agh.ics.oop.animals.Animal;
import agh.ics.oop.grass.Grass;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.moves.MoveDirection;

import java.util.*;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private final int numberOfGrasses;
    private final List<Grass> grasses;

    public GrassField(int numberOfGrasses) {
        this.numberOfGrasses = numberOfGrasses;
        this.grasses = new LinkedList<>();

        this.smallestCords = new Vector2D(0, 0);
        this.greatestCords = new Vector2D(0, 0);

        addGrass();
        this.mapAnimator.addFrame(this);
    }

    private void addGrass() {
        var random = new Random();
        var bound = (int) Math.sqrt(10 * numberOfGrasses) + 1;
        Vector2D position;

        for (int i = 0; i < numberOfGrasses; i++) {
            do {
                position = new Vector2D(random.nextInt(bound), random.nextInt(bound));
            } while (isOccupied(position));
            addElementToMap(new Grass(position));
        }
    }

    @Override
    protected void addElementToMap(IMapElement element) {
        var position = element.getPosition();
        changeCorners(position);

        if (element instanceof Grass grass)
            grasses.add(grass);
        else if (element instanceof Animal animal)
            animals.add(animal);
    }

    @Override
    public void moveAnimal(Animal animal, MoveDirection direction) {
        animal.move(direction);
        changeCorners(animal.getPosition());
    }

    protected void changeCorners(Vector2D position) {

        greatestCords = position.upperRight(greatestCords);
        smallestCords = position.lowerLeft(smallestCords);

    }

    @Override
    public boolean canMoveTo(Vector2D position) {
        for (var animal : animals) {
            if (animal.getPosition().equals(position))
                return false;
        }

        return true;
    }

    @Override
    public Object objectAt(Vector2D position) {
        for (var animal : animals) {
            if (animal.getPosition().equals(position))
                return animal;
        }

        for (var grass : grasses) {
            if (grass.getPosition().equals(position))
                return grass;
        }

        return null;
    }

}
