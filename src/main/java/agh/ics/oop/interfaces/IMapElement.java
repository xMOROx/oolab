package agh.ics.oop.interfaces;

import agh.ics.oop.Vector2D;

public interface IMapElement {
    Vector2D getPosition();
    String getResource();
    String getObjectLabel();
}
