package agh.ics.oop.map;

import agh.ics.oop.Vector2D;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString() {
        switch (this) {
            case NORTH -> {
                return "Polnoc";
            }
            case SOUTH -> {
                return "Poludnie";
            }
            case WEST -> {
                return "Zachod";
            }
            case EAST -> {
                return "Wschod";
            }
        }
        return "";
    }

    public  MapDirection next() {
        return getMapDirection(this, EAST, WEST, NORTH, SOUTH);
    }

    public MapDirection previous() {
        return getMapDirection(this, WEST, EAST, SOUTH, NORTH);
    }

    private MapDirection getMapDirection(MapDirection direction, MapDirection directionFromNorth, MapDirection directionFromSouth, MapDirection directionFromWest, MapDirection directionFromEast) {
        switch (direction) {
            case NORTH -> {
                return directionFromNorth;
            }
            case SOUTH -> {
                return directionFromSouth;
            }
            case WEST -> {
                return directionFromWest;
            }
            case EAST -> {
                return directionFromEast;
            }
        }
        return null;
    }

    public Vector2D toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2D(0, 1);
            case EAST -> new Vector2D(1,0);
            case SOUTH -> new Vector2D(0,-1);
            case WEST -> new Vector2D(-1, 0);
        };
    }

}
