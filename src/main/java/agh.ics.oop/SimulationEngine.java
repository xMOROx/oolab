package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.objectsOnMap.Animal;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.types.GrassField;
import agh.ics.oop.moves.MoveDirection;
import javafx.application.Platform;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.*;
import javax.swing.Timer;

public class SimulationEngine implements IEngine {

    private List<MoveDirection> directions;
    private final AbstractWorldMap map;
    public  int animationDelay = 500;

    private final List<Animal> animalsOrder = new ArrayList<>();

    private final List<App> observers = new ArrayList<>();

    public SimulationEngine(AbstractWorldMap map, Vector2D [] positions) {

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

    public SimulationEngine(List<MoveDirection>  directions, AbstractWorldMap map, Vector2D [] positions) {
        this(map, positions);
        this.directions = directions;
    }

    public SimulationEngine(List<MoveDirection>  directions, AbstractWorldMap map, Vector2D [] positions, App observer) {
        this(directions, map, positions);
        this.observers.add(observer);
    }

    public SimulationEngine(List<MoveDirection>  directions, AbstractWorldMap map, Vector2D [] positions, App observer, int delay) {
        this(directions, map, positions, observer);
        this.animationDelay = delay;
    }


    private void dispatchAnimation() {
        for (App app : observers) {
            Platform.runLater(app::renderMap);
        }
    }


    @Override
    public void run() {

        int n = this.animalsOrder.size();
        if(observers.size() == 0) {
            System.out.println(this.map);

            for (int i = 0; i < directions.size(); i++) {
                this.map.moveAnimal( animalsOrder.get(i % n), directions.get(i));
//                System.out.println(this.map);
//            this.map.getMapAnimator().addFrame(this.map);
            }
            return;
        }
        try {
            dispatchAnimation();
            for (int i = 0; i < this.directions.size(); i++) {
                animalsOrder.get(i % n).move(this.directions.get(i));
                dispatchAnimation();
                Thread.sleep(this.animationDelay);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
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
        new Timer(animationDelay, taskPerformer).start();

        frame.pack();
        textArea.setText("");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setDirectionArray(List<MoveDirection> directionArray) {
        this.directions = directionArray;
    }
}
