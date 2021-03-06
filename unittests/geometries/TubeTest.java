package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {


    /**
     /**
     * Test method for {@link Tube #getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Checks whether the returned normal is correct.
        Vector v = new Vector(1,0,0);
        Point p0 = new Point(0,0,0);
        Tube t = new Tube(new Ray(p0,v),5);
        Point p = new Point(4,5,0);
        Vector normal = new Vector(0,1,0);
        assertEquals(t.getNormal(p),normal,"The method getNormal return wrong normal.");
    }
}