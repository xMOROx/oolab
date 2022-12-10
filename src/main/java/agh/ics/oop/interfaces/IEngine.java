package agh.ics.oop.interfaces;

import agh.ics.oop.moves.MoveDirection;

import java.util.List;

/**
 * The interface responsible for managing the moves of the animals.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IEngine extends Runnable{
    /**
     * Move the animal on the map according to the provided move directions. Every
     * n-th direction should be sent to the n-th animal on the map.
     *
     */
    void run();

    void setDirectionArray(List<MoveDirection> parse);
}
