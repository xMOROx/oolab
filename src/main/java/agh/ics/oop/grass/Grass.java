package agh.ics.oop.grass;

import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IMapElement;

public  class Grass implements IMapElement {

     private final Vector2D position;

    public Grass(Vector2D position) {
        this.position = position;
    }

    public Vector2D getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "*";
    }
}
