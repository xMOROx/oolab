package agh.ics.oop;


public class Animal {
    private MapDirection orientation;
    private Vector2D location;

    private final IWorldMap map;
// Konstruktor nie ma sensu już z uwagi na fakt, że definiujemy typ mapy na jakiej będą nasze zwierzątka.
//    public Animal() {
//        this.orientation = MapDirection.NORTH;
//    }

    public Animal(IWorldMap map) {
        this(map, new Vector2D(0,0));
    }

    public Animal(IWorldMap map, Vector2D initialPosition) {
        this.orientation = MapDirection.NORTH;
        this.map = map;
        this.location = initialPosition;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    public Vector2D getLocation() {
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
        this.orientation = switch (direction) {
            case RIGHT -> this.orientation.next();
            case LEFT -> this.orientation.previous();
            default -> this.orientation;
        };

        if (direction == MoveDirection.FORWARD) {

            if (this.orientation == MapDirection.NORTH) {
                if (map.canMoveTo(this.location.add(MapDirection.NORTH.toUnitVector()))) {
                    this.location = this.location.add(MapDirection.NORTH.toUnitVector());
                }
            } else if (this.orientation == MapDirection.EAST) {
                if (map.canMoveTo(this.location.add(MapDirection.EAST.toUnitVector()))) {
                    this.location = this.location.add(MapDirection.EAST.toUnitVector());

                }
            } else if (this.orientation == MapDirection.SOUTH) {
                if (map.canMoveTo(this.location.add(MapDirection.SOUTH.toUnitVector()))) {
                    this.location = this.location.add(MapDirection.SOUTH.toUnitVector());

                }
            } else {
                if (map.canMoveTo(this.location.add(MapDirection.WEST.toUnitVector()))) {
                    this.location = this.location.add(MapDirection.WEST.toUnitVector());
                }
            }

        } else if (direction == MoveDirection.BACKWARD) {
            if (this.orientation == MapDirection.NORTH) {
                if (map.canMoveTo(this.location.add(MapDirection.SOUTH.toUnitVector()))) {
                    this.location = this.location.add(MapDirection.SOUTH.toUnitVector());
                }
            } else if (orientation == MapDirection.EAST) {
                if (map.canMoveTo(this.location.add(MapDirection.WEST.toUnitVector()))) {
                    this.location = this.location.add(MapDirection.WEST.toUnitVector());
                }
            } else if (orientation == MapDirection.SOUTH) {
                if (map.canMoveTo(this.location.add(MapDirection.NORTH.toUnitVector()))) {
                    this.location = this.location.add(MapDirection.NORTH.toUnitVector());
                }
            } else {
                if (map.canMoveTo(this.location.add(MapDirection.EAST.toUnitVector()))) {
                    this.location = this.location.add(MapDirection.EAST.toUnitVector());
                }
            }

        }
    }
}
