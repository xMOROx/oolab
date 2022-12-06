package agh.ics.oop.gui;

import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.types.GrassField;
import agh.ics.oop.moves.MoveDirection;
import agh.ics.oop.parsers.OptionsParser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    private AbstractWorldMap map;

    private final Integer CELL_WIDTH = 30;
    private final Integer CELL_HEIGHT = 30;
    private final Integer PADDING = 3;
    private final Double BORDER_WIDTH = 1.2;

    public void init() throws IllegalArgumentException {
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            List<MoveDirection> directions = OptionsParser.parse(args);
            this.map = new GrassField(10);
            //        AbstractWorldMap map = new RectangularMap(10,5);
            Vector2D[] positions = {  new Vector2D(2,2), new Vector2D(3,4),  new Vector2D(0,0) };
            IEngine engine = new SimulationEngine(directions, map, positions);
//            engine.run();
        } catch (IllegalArgumentException error) {
            error.printStackTrace();
            Platform.exit();
            System.exit(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();

        String inlineStyle = "-fx-border-color: darkgray; " +
                "-fx-min-width: " + CELL_WIDTH + ";" +
                "-fx-min-height:" + CELL_HEIGHT + ";" +
                "-fx-padding: " + PADDING  + ";";


        Label label = new Label("y/x");
        label.setAlignment(Pos.CENTER);
        label.setStyle(inlineStyle + "-fx-border-width:" + BORDER_WIDTH +";" + "-fx-background-color: yellow"  + ";");
        grid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH  ));
        grid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT  ));
        grid.add(label, 0,0 );

        grid.setPadding(new Insets(10,10,10,10));



        Vector2D[] mapBounds = this.map.getMapBounds();

        Integer value = mapBounds[0].x;
        for (int x = 1; x <= mapBounds[1].x - mapBounds[0].x + 1; x++) {
            grid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            label = new Label(value.toString());
            label.setStyle(inlineStyle + "-fx-border-width:" + BORDER_WIDTH + " " + BORDER_WIDTH + " " + BORDER_WIDTH +  BORDER_WIDTH +" ;");
            label.setAlignment(Pos.CENTER);
            grid.add(label, x, 0);
            value += 1;
        }

        value = mapBounds[1].y;
        for (int y = 1; y <= mapBounds[1].y - mapBounds[0].y + 1; y++) {
            grid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            label = new Label(value.toString());
            label.setStyle(inlineStyle + "-fx-border-width: 0 " + BORDER_WIDTH + " " + BORDER_WIDTH + " " + BORDER_WIDTH + ";");
            label.setAlignment(Pos.CENTER);
            grid.add(label, 0, y);
            value -= 1;
        }

        inlineStyle += "-fx-border-width: 0 " + BORDER_WIDTH + " " + BORDER_WIDTH + " 0;";

        int yCoord = mapBounds[1].y;
        for (int y = 1; y <= mapBounds[1].y - mapBounds[0].y + 1; y++) {
            int xCoord = mapBounds[0].x;
            for (int x = 1; x <= mapBounds[1].x - mapBounds[0].x + 1; x++) {
                Object object = map.objectAt(new Vector2D(xCoord, yCoord));
                if (object == null) {
                    label = new Label(" ");
                } else {
                    label = new Label(object.toString());
                }
                label.setStyle(inlineStyle);
                label.setAlignment(Pos.CENTER);
                grid.add(label, x, y);
                xCoord += 1;
            }
            yCoord -= 1;
        }


        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid, Math.min(1000, (mapBounds[1].x - mapBounds[0].x + 2)*(CELL_WIDTH)) + 20, Math.min(1000, (mapBounds[1].y - mapBounds[0].y + 2)*CELL_HEIGHT) + 20 );

        primaryStage.setScene(scene);
        primaryStage.setTitle("WorldMap");
        primaryStage.show();
    }
}
