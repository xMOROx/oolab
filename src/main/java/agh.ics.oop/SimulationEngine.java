package agh.ics.oop;

import agh.ics.oop.objectsOnMap.Animal;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.types.GrassField;
import agh.ics.oop.moves.MoveDirection;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.Timer;

public class SimulationEngine implements IEngine {

    private final List<MoveDirection> directions;
    private final AbstractWorldMap map;
    public  final int ANIMATION_DELAY = 500;

    private final List<Animal> animalsOrder = new ArrayList<>();

    public SimulationEngine(List<MoveDirection>  directions, AbstractWorldMap map, Vector2D [] positions) {
        this.directions = directions;
        this.map = map;

        for (Vector2D position: positions) {
            Animal newAnimal = new Animal(this.map, position);
            if(this.map.place(newAnimal)) {
                this.animalsOrder.add(newAnimal);
            }
        }


        if (this.map instanceof GrassField) {
            ((GrassField) this.map).addGrasses();
        }

    }

    @Override
    public void run() {

        int n = this.animalsOrder.size();
        System.out.println(this.map);

        for (int i = 0; i < directions.size(); i++) {
            this.map.moveAnimal( animalsOrder.get(i % n), directions.get(i));
            System.out.println(this.map);
//            this.map.getMapAnimator().addFrame(this.map);
        }

//        createAnimationWindow(map.getMapAnimator().getAnimation());
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

        var i = new AtomicInteger(1);
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
