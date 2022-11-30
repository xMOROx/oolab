package agh.ics.oop.interfaces;

import agh.ics.oop.Vector2D;

public interface IPositionChangeObserver {
    void positionChanged(Vector2D oldPosition, Vector2D newPosition);

}
