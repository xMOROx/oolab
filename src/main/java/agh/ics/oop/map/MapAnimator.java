package agh.ics.oop.map;

import agh.ics.oop.interfaces.IWorldMap;

import java.util.LinkedList;
import java.util.List;

// Utility class for animation

public class MapAnimator {
    private final List<String> animation;

    public MapAnimator() {
        this.animation = new LinkedList<>();
    }

    public List<String> getAnimation() {
        return animation;
    }

    public void addFrame(IWorldMap map) {
        animation.add(map.toString());
    }
}
