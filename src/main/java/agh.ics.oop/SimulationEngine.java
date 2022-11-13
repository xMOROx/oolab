package agh.ics.oop;

import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.*;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private Vector2D animalPositions[];
    private final int numberOfAnimals;
    private final IWorldMap map;

    public SimulationEngine(MoveDirection [] directions, IWorldMap map, Vector2D [] positions) {
        this.directions = directions;
        this.map = map;
        ArrayList<Vector2D> positionsTemp = new ArrayList<Vector2D>();
        for (Vector2D position: positions) {
            Animal animal = new Animal(this.map, position);
            if (this.map.place(animal)) {
                positionsTemp.add(position);
            }
        }
        animalPositions = positionsTemp.toArray(new Vector2D[0]);
        this.numberOfAnimals = positionsTemp.size();
    }

    @Override
    public void run() {
        JFrame displayMap = new JFrame();
        displayMap.setVisible(true);
        displayMap.setSize(600, 600);
        JTextArea area = new JTextArea();
        area.setFont(area.getFont().deriveFont(40f));
        System.out.println(area.getSize().width);

        displayMap.add(area);
        displayMap.setLayout(new FlowLayout());

        int animalIndex = 0;

        for (MoveDirection move: directions) {
            Vector2D position = animalPositions[animalIndex];
            Animal animal = (Animal)map.objectAt(position);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            animal.move(move);
            area.setText(map.toString());
            animalPositions[animalIndex] = animal.getLocation();
            animalIndex = (animalIndex + 1) % this.numberOfAnimals;
        }
    }
}
