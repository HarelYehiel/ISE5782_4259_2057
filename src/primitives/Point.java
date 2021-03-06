package primitives;

/**
 * A point in three-dimensional space of this shape: (x,y,z)
 */
public class Point {
    protected Double3 xyz;

    /**
     * point Represented by three coordinate in 3D world
     *
     * @param x Axis
     * @param y Axis
     * @param z Axis
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * point Represented by Double3
     * @param dbl
     */
    public  Point(Double3 dbl) {xyz = new Double3(dbl.d1,dbl.d2,dbl.d3);}

    public double getX(){return xyz.d1;}
    public double getY(){return xyz.d2;}
    public double getZ(){return xyz.d3;}

    /**
     * equals between two points.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    /**
     * @return the details: xyz.
     * */
    @Override
    public String toString() {
        return "xyz=" + xyz;
    }

    /**
     *  Add v to this.xyz.
     * @param v
     * @return new Point
     */
    public Point add(Vector v) {
        Double3 d = xyz.add(v.xyz);
        return new Point(d);
    }

    /**
     * subtract from this.xyz to p by subtract(p.xyz) of class Double3.
     * @param p is the point need to subtract.
     * @return new Vector.
     */
    public Vector subtract(Point p) {
        Double3 d = xyz.subtract(p.xyz);
        return new Vector(d);
    }

    /**
     * Distance from this.xyz to point in squared.
     * @param p
     * @return double
     */
    public double distanceSquared(Point p) {
        return (xyz.d1 - p.xyz.d1) * (xyz.d1 - p.xyz.d1) +
                (xyz.d2 - p.xyz.d2) * (xyz.d2 - p.xyz.d2) +
                (xyz.d3 - p.xyz.d3) * (xyz.d3 - p.xyz.d3);
    }

    /**
     * Distance from this.xyz to point.
     * @param p
     * @return double
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }
}
