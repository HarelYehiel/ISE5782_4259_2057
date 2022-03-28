package geometries;

import primitives.Point;
import static primitives.Util.*;
import primitives.Vector;
import primitives.Ray;
import java.util.List;

/**
 *  Plane is infinite linear 3D surface The class represents Plane entity in our
 *  3D model
 */
public class Plane implements Geometry{

    private Point q0;
    private Vector normal;

    /**
     * @return the details: q0, normal.
     */
    @Override
    public String toString() {
        return "q0=" + q0 +
                ", normal=" + normal;
    }

    /**
     * @return q0.
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * @return normal.
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Constructor that builds Plane from 3 Points in the plane
     * @param q0 first point
     * @param q1 second point
     * @param q2 third point
     */
    public Plane(Point q0,Point q1,Point q2) {
        if(q0.equals(q1) || q0.equals(q2) || q1.equals(q2))
            throw new IllegalArgumentException("All vertices of a Plane need to be different");


            Vector v0 = q0.subtract(q1);
            Vector v1 = q0.subtract(q2);
            Vector v2 = v0.crossProduct(v1);
            if(isZero(v2.lengthSquared()))
                throw new IllegalArgumentException("All vertices of a Plane need not to be on the same line");


        this.q0 = q0;
        normal = v2.normalize();
    }

    /**
     * Constructor that initialize the q0 and normal.
     * normal = vector.
     * @param q0
     * @param vector
     */
    public Plane(Point q0, Vector vector) {
        this.q0 = q0;
        this.normal = vector.normalize();
    }

    /**
     *
     * @param p
     * @return the normal of p.
     */
    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    /**
     *
     * @param ray
     * @return list of intersection points between ray and plan.
     */
    public List<Point> findIntersections(Ray ray) {
        /*
        based on this
        P0: begin point of ray
        v: is vector of ray
        Q: is point on plane
        n: is normal of plane
        Ray points: 𝑃=𝑃0+𝑡∙𝑣, 𝑡>0
        Plane points: n∙(𝑄−𝑃)=0
        𝑛∙(𝑄−𝑡∙𝑣−𝑃0)=0
        𝑛∙(𝑄−𝑃0)−𝑡∙𝑛∙𝑣=0
        𝑡=𝑛∙(𝑄−𝑃0)/𝑛∙𝑣
         */
        double nv = this.normal.dotProduct(ray.getDir());
        if (isZero(nv))  // check if n and v are orthogonal n * u = 0
            return null;
        double nQMinusP0 = this.normal.dotProduct(this.q0.subtract(ray.getP0()));
        double t = alignZero(nQMinusP0 / nv);
        if (t <= 0)     //  check if ray is opposite direction of plane
            return null;
        return List.of(ray.getPoint(t));
    }
}
