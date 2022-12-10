package agh.ics.oop.gui;

import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Vector2D;
import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.interfaces.IMapElement;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.types.GrassField;
import agh.ics.oop.moves.MoveDirection;
import agh.ics.oop.parsers.OptionsParser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class App extends Application {

    private AbstractWorldMap map;

    private final Integer CELL_WIDTH = 70;
    private final Integer CELL_HEIGHT = 50;
    private final Integer PADDING = 3;
    private final Double BORDER_WIDTH = 1.5;
    private final Integer NUMBER_OF_GRASSES = 10;
    private IEngine engine;
    private Vector2D[] elementsPositions;
    private List<MoveDirection> directions;
    private Vector2D[] mapBounds;
    private GridPane grid = new GridPane();

    public void init() throws IllegalArgumentException {
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            this.directions = OptionsParser.parse(args);
            this.map = new GrassField(NUMBER_OF_GRASSES);
            this.elementsPositions = new Vector2D[]{new Vector2D(2, 2), new Vector2D(3, 4), new Vector2D(0, 0)};
            this.engine = new SimulationEngine(this.directions, this.map, this.elementsPositions, this, 1000);

        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            Platform.exit();
            System.exit(0);
        }
    }

    private void startAnimation(TextField textField) {
        String[] args = textField.getText().split(" ");
        engine.setDirectionArray(new OptionsParser().parse(args));
        Thread engineThread = new Thread(this.engine);
        engineThread.start();
    }

    private void setIcon(Stage stage) throws FileNotFoundException {
        try {
            String filePath = new File("").getAbsolutePath();
            filePath = filePath.concat("\\src\\main\\resources\\");
            Image image = new Image(new FileInputStream(filePath.concat("up.png")));
            stage.getIcons().add(image);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        this.mapBounds = this.map.getMapBounds();
        this.grid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH  ));
        this.grid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT  ));

        for (int x = 1; x <= mapBounds[1].x - mapBounds[0].x + 1; x++) {
            this.grid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
        for (int y = 1; y <= mapBounds[1].y - mapBounds[0].y + 1; y++) {
            this.grid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }

        renderMap();
        HBox guiInterface = new HBox(5);
        guiInterface.setAlignment(Pos.CENTER);
        guiInterface.setPadding(new Insets(10, 0, 0, 0));
        Button animationStart = new Button("Start");
        TextField animationMoves = new TextField();
        animationStart.setOnAction((action) -> {
            startAnimation(animationMoves);
        });

        guiInterface.getChildren().addAll(animationMoves, animationStart);

        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.getChildren().addAll(grid, guiInterface);

        ScrollPane scrollPane = new ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPadding(new Insets(20));


        Scene scene = new Scene(scrollPane, (CELL_WIDTH + PADDING + 5) * (NUMBER_OF_GRASSES + 2) , (CELL_HEIGHT + PADDING + 5) * (NUMBER_OF_GRASSES + 2));
        primaryStage.setTitle("WorldMap");
        try {
            setIcon(primaryStage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Platform.exit();
            System.exit(0);
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle("WorldMap");
        primaryStage.show();

    }

    public void renderMap() {
        String inlineStyle = "-fx-border-color: darkgray; " +
                "-fx-min-width: " + CELL_WIDTH + ";" +
                "-fx-min-height:" + CELL_HEIGHT + ";" +
                "-fx-padding: " + PADDING  + ";";


        this.grid.getChildren().clear();
        this.grid.setAlignment(Pos.CENTER);
        Label label = new Label("y/x");
        label.setAlignment(Pos.CENTER);
        label.setStyle(inlineStyle + "-fx-border-width:" + BORDER_WIDTH +";" + "-fx-background-color: yellow"  + ";");

        this.grid.add(label, 0,0 );

        this.grid.setPadding(new Insets(10,10,10,10));

        mapBounds = map.getMapBounds();

        Integer value = this.mapBounds[0].x;
        for (int x = 1; x <= this.mapBounds[1].x - this.mapBounds[0].x + 1; x++) {
            label = new Label(value.toString());
            label.setStyle(inlineStyle + "-fx-border-width:" + BORDER_WIDTH + " " + BORDER_WIDTH + " " + BORDER_WIDTH +  BORDER_WIDTH +" ;");
            label.setAlignment(Pos.CENTER);
            this.grid.add(label, x, 0);
            value += 1;
        }

        value = this.mapBounds[1].y;
        for (int y = 1; y <= this.mapBounds[1].y - this.mapBounds[0].y + 1; y++) {
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
                IMapElement object = map.objectAt(new Vector2D(xCoord, yCoord));

                if (object == null) {
                    label = new Label(" ");
                    label.setStyle(inlineStyle);
                    label.setAlignment(Pos.CENTER);
                    this.grid.add(label, x, y);
                } else {
                    try {
                        GuiElementBox mapElement = new GuiElementBox(object);
                        mapElement.getElement().setStyle(inlineStyle);
                        mapElement.getElement().setAlignment(Pos.CENTER);
                        this.grid.add(mapElement.getElement(), x, y);
                    } catch(FileNotFoundException error) {
                        System.out.println(error.getMessage());
                    }
                }
                xCoord += 1;
            }
            yCoord -= 1;
        }
    }


}
