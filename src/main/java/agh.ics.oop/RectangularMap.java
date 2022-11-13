package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private final int width;
    private final int height;

    private List<Animal> animals = new ArrayList<Animal>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2D(0, 0), new Vector2D(this.width, this.height));
    }

    @Override
    public boolean canMoveTo(Vector2D position) {
        return position.x >= 0 && position.x <= this.width && position.y >= 0 && position.y <= height && !this.isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {

        if(!this.isOccupied(animal.getLocation())) {
            animals.add(animal);
            return true;
        }

        return false;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        for (Animal animal: this.animals) {
            if(animal.isAt(position))  {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2D position) {
        for (Animal animal: this.animals) {
            if(animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }

}
