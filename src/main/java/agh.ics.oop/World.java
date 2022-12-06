package agh.ics.oop;


import agh.ics.oop.gui.App;
import javafx.application.Application;


/*
    Uwaga 1: W App.java zostały użyte style css'a które obsługuje JavaFX zamiast 'grid.setGridLinesVisible(true);' z powodów estetycznych


*/

public class World {

    public static void main(String[] args ) {

        try{
            Application.launch(App.class, args);
        } catch (Exception error) {
            System.out.println(error.getMessage());

        }


    }

}
