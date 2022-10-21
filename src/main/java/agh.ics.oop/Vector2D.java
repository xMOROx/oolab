package agh.ics.oop;

import java.util.Objects;

public class Vector2D {
    public final int y;
    public final int x;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean precedes(Vector2D other) {
        if(other == null) {
            return false;
        }
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2D other) {
        if(other == null) {
            return false;
        }
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2D add(Vector2D other) {
        if(other == null) {
            return null;
        }
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        if(other == null) {
            return null;
        }
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D upperRight(Vector2D other) {
        if(other == null) {
            return null;
        }
        return new Vector2D(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2D lowerLeft(Vector2D other) {
        if(other == null) {
            return null;
        }
        return new Vector2D(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2D opposite() {
        return new Vector2D(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }
        if(other == null || getClass() != other.getClass()  ) {
            return false;
        }
        Vector2D vector = (Vector2D) other;
        return vector.x == this.x && vector.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

}