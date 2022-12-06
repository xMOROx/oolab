package agh.ics.oop.interfaces;

import agh.ics.oop.Vector2D;

public interface IPositionChangeElement extends IPositionChangeObserver
{
    void addObserver(IPositionChangeObserver observer);
    void removeObserver(IPositionChangeObserver observer);

}
