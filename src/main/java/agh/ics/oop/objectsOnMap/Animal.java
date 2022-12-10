package agh.ics.oop.objectsOnMap;


import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.interfaces.IPositionChangeElement;
import agh.ics.oop.interfaces.IPositionChangeObserver;
import agh.ics.oop.moves.MoveDirection;
import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.MapDirection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Animal implements IMapElement, IPositionChangeElement {
    private MapDirection orientation;
    private Vector2D location;
    private final static Vector2D DEFAULT_POSITION = new Vector2D(2,2);

    private final IWorldMap map;

    private final List<IPositionChangeObserver> observers = new LinkedList<>();

    public Animal(IWorldMap map) {
        this(map, DEFAULT_POSITION);
    }

    public Animal(IWorldMap map, Vector2D initialPosition) {
        this.orientation = MapDirection.NORTH;
        this.map = map;
        this.location = initialPosition;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public Vector2D getPosition() {
        return this.location;
    }
    @Override
    public String toString() {
        return switch (this.orientation) {
            case NORTH -> "N";
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
        };
    }

    public boolean isAt(Vector2D position) {
        return location.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> this.location = correctMove(this.orientation.toUnitVector());
            case BACKWARD -> this.location = correctMove(this.orientation.toUnitVector().opposite());
        }
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        for (IPositionChangeObserver observer : this.observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(this.location, animal.location) && Objects.equals(map, animal.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.location, map);
    }

    private Vector2D correctMove(Vector2D move) {
        Vector2D result = this.location.add(move);

        if(map.canMoveTo(result)) {
            this.positionChanged(this.location, result);
            return result;
        }
        return this.location;
    }

    @Override
    public String getResource() {
        return switch (this.orientation) {
            case NORTH -> "up.png";
            case EAST -> "right.png";
            case SOUTH ->"down.png";
            case WEST -> "left.png";
        };
    }

    @Override
    public String getObjectLabel() {
        return this.toString() + ' ' + this.location.toString();
    }
}
