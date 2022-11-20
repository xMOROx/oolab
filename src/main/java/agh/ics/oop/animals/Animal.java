package agh.ics.oop.animals;


import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.moves.MoveDirection;
import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.MapDirection;

public class Animal implements IMapElement {
    private MapDirection orientation;
    private Vector2D location;
    private final static Vector2D DEFAULT_POSITION = new Vector2D(2,2);

    private final IWorldMap map;
// Konstruktor nie ma sensu już z uwagi na fakt, że definiujemy typ mapy na jakiej będą nasze zwierzątka.
//    public Animal() {
//        this.orientation = MapDirection.NORTH;
//    }

    public Animal(IWorldMap map) {
        this(map, DEFAULT_POSITION);
    }

    public Animal(IWorldMap map, Vector2D initialPosition) {
        this.orientation = MapDirection.NORTH;
        this.map = map;
        this.location = initialPosition;
        this.map.place(this);
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

    private Vector2D correctMove(Vector2D move) {

        Vector2D result = this.location.add(move);
        return map.canMoveTo(result) ? result : this.location;
    }
}
