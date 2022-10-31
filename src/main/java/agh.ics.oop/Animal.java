package agh.ics.oop;


public class Animal {
    private MapDirection orientation;
    private Vector2D location;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.location = new Vector2D(2, 2);
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }
    @Override
    public String toString() {
        return "Animal{" +
                "orientation= " + orientation.toString() +
                ", location= " + location +
                '}';
    }

    public boolean isAt(Vector2D position) {
        return location.equals(position);
    }


    private boolean goOutOfMap(int x, int y) {
        return this.location.x + x >= 0 && this.location.x + x < 5 && this.location.y + y >= 0 && this.location.y + y < 5;
    }

    public void move(MoveDirection direction) {
        this.orientation = switch (direction) {
            case RIGHT -> this.orientation.next();
            case LEFT -> this.orientation.previous();
            default -> this.orientation;
        };

        if (direction == MoveDirection.FORWARD) {

            if (this.orientation == MapDirection.NORTH) {
                if (goOutOfMap(0, 1)) {
                    this.location = this.location.add(new Vector2D(0, 1));
                }
            } else if (this.orientation == MapDirection.EAST) {
                if (goOutOfMap(1, 0)) {
                    this.location = this.location.add(new Vector2D(1, 0));

                }
            } else if (this.orientation == MapDirection.SOUTH) {
                if (goOutOfMap(0, -1)) {
                    this.location = this.location.add(new Vector2D(0, -1));

                }
            } else {
                if (goOutOfMap(-1, 0)) {
                    this.location = this.location.add(new Vector2D(-1, 0));
                }
            }

        } else if (direction == MoveDirection.BACKWARD) {
            if (this.orientation == MapDirection.NORTH) {
                if (goOutOfMap(0, -1)) {
                    this.location = this.location.add(new Vector2D(0, -1));
                }
            } else if (orientation == MapDirection.EAST) {
                if (goOutOfMap(-1, 0)) {
                    this.location = this.location.add(new Vector2D(-1, 0));
                }
            } else if (orientation == MapDirection.SOUTH) {
                if (goOutOfMap(0, 1)) {
                    this.location = this.location.add(new Vector2D(0, 1));
                }
            } else {
                if (goOutOfMap(1, 0)) {
                    this.location = this.location.add(new Vector2D(1, 0));
                }
            }

        }
    }
}
