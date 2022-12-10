package agh.ics.oop.interfaces;

import agh.ics.oop.objectsOnMap.Animal;
import agh.ics.oop.Vector2D;

public interface IWorldMap {

    boolean canMoveTo(Vector2D position);


    boolean place(IMapElement animal);

    boolean isOccupied(Vector2D position);

    IMapElement objectAt(Vector2D position);
}
