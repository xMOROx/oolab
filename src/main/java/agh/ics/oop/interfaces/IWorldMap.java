package agh.ics.oop.interfaces;

import agh.ics.oop.animals.Animal;
import agh.ics.oop.Vector2D;


import java.util.Optional;

public interface IWorldMap {

    boolean canMoveTo(Vector2D position);


    boolean place(Animal animal);



    boolean isOccupied(Vector2D position);

    Object objectAt(Vector2D position);
}
