package agh.ics.oop;


import agh.ics.oop.interfaces.IEngine;
import agh.ics.oop.map.AbstractWorldMap;
import agh.ics.oop.map.types.RectangularMap;
import agh.ics.oop.moves.MoveDirection;
import agh.ics.oop.parsers.OptionsParser;
import agh.ics.oop.map.types.GrassField;


public class World {

    public static void main(String[] args ) {


        MoveDirection[] directions = OptionsParser.parse(args);
        AbstractWorldMap map = new GrassField(10);
//        AbstractWorldMap map = new RectangularMap(10,5);

        Vector2D[] positions = { new Vector2D(2,2), new Vector2D(3,4),  new Vector2D(0,0) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

    }

}
