package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MapDirectionTest {

    @Test
    void next() {
        MapDirection east = MapDirection.EAST;
        Assertions.assertEquals(MapDirection.SOUTH, east.next());
        MapDirection south = MapDirection.SOUTH;
        Assertions.assertEquals(MapDirection.WEST, south.next());
        MapDirection west = MapDirection.WEST;
        Assertions.assertEquals(MapDirection.NORTH, west.next());
        MapDirection north = MapDirection.NORTH;
        Assertions.assertEquals(MapDirection.EAST, north.next());
    }

    @Test
    void previous() {
        MapDirection east = MapDirection.EAST;
        Assertions.assertEquals(MapDirection.NORTH, east.previous());
        MapDirection south = MapDirection.SOUTH;
        Assertions.assertEquals(MapDirection.EAST, south.previous());
        MapDirection west = MapDirection.WEST;
        Assertions.assertEquals(MapDirection.SOUTH, west.previous());
        MapDirection north = MapDirection.NORTH;
        Assertions.assertEquals(MapDirection.WEST, north.previous());
    }
}