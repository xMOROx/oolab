package agh.ics.oop;

import agh.ics.oop.animals.Animal;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.types.GrassField;
import agh.ics.oop.moves.MoveDirection;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;
    private final AbstractWorldMap map;
    private final List<Animal> animals;
    public  final int ANIMATION_DELAY = 1000;

    public SimulationEngine(MoveDirection [] directions, AbstractWorldMap map, Vector2D [] positions) {
        this.directions = directions;
        this.map = map;

        for (Vector2D position: positions) {
            new Animal(this.map, position);
        }

        this.animals = this.map.getAnimals();
        if (this.map instanceof GrassField) {
            ((GrassField) this.map).addGrass();
        }

    }

    @Override
    public void run() {


        Iterator<Animal> iterator = this.animals.iterator();
        if (!iterator.hasNext())
            return;

        for (var direction : directions) {
            if (!iterator.hasNext())
                iterator = animals.iterator();

            Animal animal = iterator.next();
            this.map.moveAnimal(animal, direction);
            this.map.getMapAnimator().addFrame(this.map);
        }


        createAnimationWindow(map.getMapAnimator().getAnimation());
    }

    private  void createAnimationWindow(List<String> animation) {
        var frame = new JFrame("Zwierzeta");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        var panel = new JPanel();
        frame.add(panel);

        var textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Courier New", Font.PLAIN, 25));
        textArea.setText(animation.stream().max(Comparator.comparing(String::length)).orElse(""));
        panel.add(textArea);

        var i = new AtomicInteger(1); // upraszcza strukture aby ominąć pętle
        ActionListener taskPerformer = evt -> {
            textArea.setText(animation.get(i.get() % animation.size()));
            i.addAndGet(1);
        };
        new Timer(ANIMATION_DELAY, taskPerformer).start();

        frame.pack();
        textArea.setText("");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
