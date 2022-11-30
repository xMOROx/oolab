package agh.ics.oop.objectsOnMap;

import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IMapElement;

import java.util.Objects;

public  class Grass implements IMapElement {

     private final Vector2D position;

    public Grass(Vector2D position) {
        this.position = position;
    }

    public Vector2D getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grass grass = (Grass) o;
        return position.equals(grass.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "*";
    }
}
