package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;


public class Vector2DTest {
    private int[] randomize(int min, int max) {
        if (min > max) {
            return null;
        }
        Random rand = new Random();
        int range = max - min + 1;
        return new int[]{rand.nextInt(range) + min, rand.nextInt(range) + 1};
    }

    @Test
    void equals() {
        Vector2D first = new Vector2D(1, 1);
        Vector2D second = new Vector2D(1, 1);
        Vector2D third = new Vector2D(-1, 20);
        Assertions.assertTrue(first.equals(second));
        Assertions.assertFalse(first.equals(third));
        Assertions.assertFalse(first.equals(null));
        Assertions.assertFalse(first.equals(new int[10]));
    }

    @Test
    void precedes() {

        int[] cords = new int[]{12, -4};

        Vector2D first = new Vector2D(cords[0], cords[1]);

        Assertions.assertFalse(first.precedes(null));

        Assertions.assertFalse(first.precedes(new Vector2D(cords[0] - 2, cords[1] - 1)));
        Assertions.assertFalse(first.precedes(new Vector2D(cords[0] - 1, cords[1])));
        Assertions.assertFalse(first.precedes(new Vector2D(cords[0], cords[1] - 5)));
        Assertions.assertFalse(first.precedes(new Vector2D(cords[0] + 1, cords[1] - 3)));
        Assertions.assertFalse(first.precedes(new Vector2D(cords[0] - 1, cords[1] + 3)));

        Assertions.assertTrue(first.precedes(new Vector2D(cords[0], cords[1])));
        Assertions.assertTrue(first.precedes(new Vector2D(cords[0] + 1, cords[1])));
        Assertions.assertTrue(first.precedes(new Vector2D(cords[0], cords[1] + 2)));
        Assertions.assertTrue(first.precedes(new Vector2D(cords[0] + 5, cords[1] + 3)));
    }

    @Test
    void follows() {

        int[] cords = new int[]{-23, 45};
        Vector2D first = new Vector2D(cords[0], cords[1]);
        Assertions.assertFalse(first.follows(null));
        Assertions.assertFalse(first.follows(new Vector2D(cords[0] + 1, cords[1] + 2)));
        Assertions.assertFalse(first.follows(new Vector2D(cords[0] - 1, cords[1] + 2)));
        Assertions.assertFalse(first.follows(new Vector2D(cords[0], cords[1] + 2)));
        Assertions.assertFalse(first.follows(new Vector2D(cords[0] + 1, cords[1])));
        Assertions.assertFalse(first.follows(new Vector2D(cords[0] + 1, cords[1] - 3)));
        Assertions.assertTrue(first.follows(new Vector2D(cords[0], cords[1])));
        Assertions.assertTrue(first.follows(new Vector2D(cords[0] - 1, cords[1] - 5)));
        Assertions.assertTrue(first.follows(new Vector2D(cords[0] - 4, cords[1])));
        Assertions.assertTrue(first.follows(new Vector2D(cords[0], cords[1] - 4)));

    }

    @Test
    void upperRight() {


        int[] cords = new int[]{-11, 23};

        Vector2D first = new Vector2D(cords[0], cords[1]);

        Assertions.assertNull(first.upperRight(null));
        Assertions.assertEquals(new Vector2D(5,23), first.upperRight(new Vector2D(5,2)));
        Assertions.assertEquals(new Vector2D(5,24), first.upperRight(new Vector2D(5,24)));
        Assertions.assertEquals(new Vector2D(-11,24), first.upperRight(new Vector2D(-15,24)));
        Assertions.assertEquals(new Vector2D(-11,23), first.upperRight(new Vector2D(-15,2)));


    }

    @Test
    void lowerLeft() {


        int[] cords = new int[]{-11, 23};
        Vector2D first = new Vector2D(cords[0], cords[1]);

        Assertions.assertNull(first.upperRight(null));
        Assertions.assertEquals(new Vector2D(-11,2), first.lowerLeft(new Vector2D(5,2)));
        Assertions.assertEquals(new Vector2D(-11,23), first.lowerLeft(new Vector2D(5,24)));
        Assertions.assertEquals(new Vector2D(-15,23), first.lowerLeft(new Vector2D(-15,24)));
        Assertions.assertEquals(new Vector2D(-15,2), first.lowerLeft(new Vector2D(-15,2)));
    }

    @Test
    void opposite() {

        int[] cords = {10,56};
        Vector2D first = new Vector2D(cords[0], cords[1]);
        Assertions.assertEquals(new Vector2D(-10, -56), first.opposite());
        first = new Vector2D(-cords[0], -cords[1]);
        Assertions.assertEquals(new Vector2D(10, 56), first.opposite());
    }

    private void addSign(int x, int y, int u, int v) {
        int[] cords = {x,y};
        int[] cords2 = {u,v};
        Vector2D first = new Vector2D(cords[0], cords[1]);

        Assertions.assertEquals(new Vector2D(cords[0] + cords2[0], cords[1] + cords2[1]), first.add(new Vector2D(cords2[0], cords2[1])));
        Assertions.assertEquals(new Vector2D(cords[0] - cords2[0], cords[1] + cords2[1]), first.add(new Vector2D(-cords2[0], cords2[1])));
        Assertions.assertEquals(new Vector2D(cords[0] + cords2[0], cords[1] - cords2[1]), first.add(new Vector2D(cords2[0], -cords2[1])));
        Assertions.assertEquals(new Vector2D(cords[0] - cords2[0], cords[1] - cords2[1]), first.add(new Vector2D(-cords2[0], -cords2[1])));
    }

    @Test
    void add() {


        Vector2D first = new Vector2D(2, 7);
        Assertions.assertNull(first.add(null));
        addSign(2,5,6,4);
        addSign(-2,5,6,4);
        addSign(-2,-5,6,4);
        addSign(-2,-5,-6,4);
        addSign(-2,-5,-6,-4);



    }

    private void subtractSign(int x, int y, int u, int v) {
        int[] cords = {x,y};
        int[] cords2 = {u,v};
        Vector2D first = new Vector2D(cords[0], cords[1]);
        Assertions.assertEquals(new Vector2D(cords[0] - cords2[0], cords[1] - cords2[1]), first.subtract(new Vector2D(cords2[0], cords2[1])));
        Assertions.assertEquals(new Vector2D(cords[0] + cords2[0], cords[1] - cords2[1]), first.subtract(new Vector2D(-cords2[0], cords2[1])));
        Assertions.assertEquals(new Vector2D(cords[0] - cords2[0], cords[1] + cords2[1]), first.subtract(new Vector2D(cords2[0], -cords2[1])));
        Assertions.assertEquals(new Vector2D(cords[0] + cords2[0], cords[1] + cords2[1]), first.subtract(new Vector2D(-cords2[0], -cords2[1])));
    }

    @Test
    void subtract() {

        Vector2D first = new Vector2D(2, 8);
        Assertions.assertNull(first.add(null));
        subtractSign(2,5,6,4);
        subtractSign(-2,5,6,4);
        subtractSign(-2,-5,6,4);
        subtractSign(-2,-5,-6,4);
        subtractSign(-2,-5,-6,-4);
    }


    @Test
    void testToString() {

        int[] cords = new int[]{3, 5};
        Vector2D first = new Vector2D(cords[0], cords[1]);
        Assertions.assertEquals("(" + cords[0] + "," + cords[1] + ")", first.toString());
        cords[0] = -23;
        first = new Vector2D(cords[0], cords[1]);
        Assertions.assertEquals("(" + cords[0] + "," + cords[1] + ")", first.toString());

    }
}
