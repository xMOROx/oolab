package agh.ics.oop.interfaces;

import agh.ics.oop.Vector2D;

public interface IPositionChangeElement
{
    void addObserver(IPositionChangeObserver observer);
    void removeObserver(IPositionChangeObserver observer);
    void positionChanged(Vector2D oldPosition, Vector2D newPosition);
}
