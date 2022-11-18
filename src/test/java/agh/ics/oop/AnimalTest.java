package agh.ics.oop;

import agh.ics.oop.animals.Animal;
import agh.ics.oop.interfaces.IWorldMap;
import agh.ics.oop.map.MapDirection;
import agh.ics.oop.map.types.RectangularMap;
import agh.ics.oop.moves.MoveDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AnimalTest {

//    @Test
//    void testToString() {
//        IWorldMap map = new RectangularMap(4,4);
//        Animal test = new Animal(map);
//        Assertions.assertEquals("Animal{" +
//                "orientation= " + "Polnoc"  +
//                ", location= " + "(2,2)" +
//                '}', test.toString());
//        test.move(MoveDirection.BACKWARD);
//        Assertions.assertEquals("Animal{" +
//                "orientation= " + "Polnoc"  +
//                ", location= " + "(2,1)" +
//                '}', test.toString());
//        test.move(MoveDirection.LEFT);
//        Assertions.assertEquals("Animal{" +
//                "orientation= " + "Zachod"  +
//                ", location= " + "(2,1)" +
//                '}', test.toString());
//        test.move(MoveDirection.RIGHT);
//        test.move(MoveDirection.RIGHT);
//        Assertions.assertEquals("Animal{" +
//                "orientation= " + "Wschod"  +
//                ", location= " + "(2,1)" +
//                '}', test.toString());
//        test.move(MoveDirection.RIGHT);
//        Assertions.assertEquals("Animal{" +
//                "orientation= " + "Poludnie"  +
//                ", location= " + "(2,1)" +
//                '}', test.toString());
//    }

    @Test
    void isAt() {
        IWorldMap map = new RectangularMap(4,4);
        Animal test = new Animal(map, new Vector2D(2,2));
        Assertions.assertTrue(test.isAt(new Vector2D(2,2)));
        Assertions.assertFalse(test.isAt(new Vector2D(-2,2)));

    }

    @Test
    void move() {
        IWorldMap map = new RectangularMap(4,4);

        Animal test = new Animal(map, new Vector2D(2,2));

        Assertions.assertTrue(test.isAt(new Vector2D(2,2)));
        test.move(MoveDirection.FORWARD);

        Assertions.assertTrue(test.isAt(new Vector2D(2,3)));
        test.move(MoveDirection.FORWARD);
        test.move(MoveDirection.FORWARD);
        test.move(MoveDirection.FORWARD);
        test.move(MoveDirection.FORWARD);

//        Assertions.assertTrue(test.isAt(new Vector2D(2,4)));

        Animal test2 = new Animal(map, new Vector2D(2,2));
        test2.move(MoveDirection.BACKWARD);

        Assertions.assertTrue(test2.isAt(new Vector2D(2,1)));
        test2.move(MoveDirection.BACKWARD);
        test2.move(MoveDirection.BACKWARD);
        test2.move(MoveDirection.BACKWARD);
        test2.move(MoveDirection.BACKWARD);

        Assertions.assertTrue(test2.isAt(new Vector2D(2,0)));

        Animal test3 = new Animal(map, new Vector2D(2,2));
        Assertions.assertEquals(test3.getOrientation(), MapDirection.NORTH);
        test3.move(MoveDirection.RIGHT);
        Assertions.assertEquals(test3.getOrientation(), MapDirection.EAST);
        test3.move(MoveDirection.RIGHT);

        Assertions.assertEquals(test3.getOrientation(), MapDirection.SOUTH);
        test3.move(MoveDirection.RIGHT);

        Assertions.assertEquals(test3.getOrientation(), MapDirection.WEST);

        Animal test4 = new Animal(map, new Vector2D(2,2));
        Assertions.assertEquals(test4.getOrientation(), MapDirection.NORTH);
        test4.move(MoveDirection.LEFT);
        Assertions.assertEquals(test4.getOrientation(), MapDirection.WEST);
        test4.move(MoveDirection.LEFT);

        Assertions.assertEquals(test4.getOrientation(), MapDirection.SOUTH);
        test4.move(MoveDirection.LEFT);

        Assertions.assertEquals(test4.getOrientation(), MapDirection.EAST);

        Animal test5 = new Animal(map, new Vector2D(2,2));
        test5.move(MoveDirection.RIGHT);
        Assertions.assertTrue(test5.isAt(new Vector2D(2,2)));
        test5.move(MoveDirection.FORWARD);
        Assertions.assertTrue(test5.isAt(new Vector2D(3,2)));
        test5.move(MoveDirection.FORWARD);
        Assertions.assertTrue(test5.isAt(new Vector2D(3,2)));
        test5.move(MoveDirection.FORWARD);
        test5.move(MoveDirection.FORWARD);
        Assertions.assertTrue(test5.isAt(new Vector2D(3,2)));

        Animal test6 = new Animal(map, new Vector2D(2,2));

        test6.move(MoveDirection.LEFT);
        test6.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(test6.isAt(new Vector2D(3, 2)));
        test6.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(test6.isAt(new Vector2D(3, 2)));
        test6.move(MoveDirection.BACKWARD);
        test6.move(MoveDirection.BACKWARD);
        test6.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(test6.isAt(new Vector2D(3, 2)));

        Animal test7 = new Animal(map, new Vector2D(2,2));

        test7.move(MoveDirection.LEFT);
        test7.move(MoveDirection.FORWARD);
        Assertions.assertTrue(test7.isAt(new Vector2D(1,2)));
        test7.move(MoveDirection.FORWARD);
        Assertions.assertTrue(test7.isAt(new Vector2D(0,2)));
        test7.move(MoveDirection.FORWARD);
        test7.move(MoveDirection.FORWARD);
        test7.move(MoveDirection.FORWARD);

        Assertions.assertTrue(test7.isAt(new Vector2D(0,2)));

        Animal test8 = new Animal(map, new Vector2D(2,2));

        test8.move(MoveDirection.RIGHT);
        test8.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(test8.isAt(new Vector2D(1,2)));
        test8.move(MoveDirection.BACKWARD);
        Assertions.assertTrue(test8.isAt(new Vector2D(0,2)));
        test8.move(MoveDirection.BACKWARD);
        test8.move(MoveDirection.BACKWARD);
        test8.move(MoveDirection.BACKWARD);

        Assertions.assertTrue(test8.isAt(new Vector2D(0,2)));

    }
}