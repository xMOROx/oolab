package agh.ics.oop.map;

import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IPositionChangeObserver;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapBoundary implements IPositionChangeObserver {
    protected final SortedMap<Vector2D, Integer> oxAxis;
    protected final SortedMap<Vector2D, Integer> oyAxis;


    public MapBoundary() {
        this.oxAxis = new TreeMap<>(Comparator.comparing(v -> v.x));
        this.oyAxis = new TreeMap<>(Comparator.comparing(v -> v.y));
    }

    public void removePosition(Vector2D position) {
        if (oxAxis.containsKey(position)) {
            if (oxAxis.get(position) == 1) {
                oxAxis.remove(position);
            } else {
                oxAxis.put(position, oxAxis.get(position) - 1);
            }
        }
        if (oyAxis.containsKey(position)) {
            if (oyAxis.get(position) == 1) {
                oyAxis.remove(position);
            } else {
                oyAxis.put(position, oyAxis.get(position) - 1);
            }
        }
    }

    public void addPosition(Vector2D position) {
        if (oxAxis.containsKey(position)) {
            oxAxis.put(position, oxAxis.get(position) + 1);
        } else {
            oxAxis.put(position, 1);
        }

        if (oyAxis.containsKey(position)) {
            oyAxis.put(position, oyAxis.get(position) + 1);
        } else {
            oyAxis.put(position, 1);
        }
    }

    @Override
    public void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        this.removePosition(oldPosition);
        this.addPosition(newPosition);
    }

    public Vector2D[] getMapBounds() {
        return new Vector2D[]{new Vector2D(oxAxis.firstKey().x, oyAxis.firstKey().y), new Vector2D(oxAxis.lastKey().x, oyAxis.lastKey().y)};
    }
}
