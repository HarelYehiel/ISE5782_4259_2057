package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;


/**
 * Triangle is a polygon whit only 3 vertices
 */
public class Triangle extends Polygon{

    /**
     * ctor with three vertices (Points3D)
     *
     * @param q0 Vertex of the triangle
     * @param q1 Vertex of the triangle
     * @param q2 Vertex of the triangle
     */
    public Triangle(Point q0,Point q1,Point q2) {
        super(q0,q1,q2);
    }

    /**
     *
     * @param ray
     * @return list of intersection Geopoints between ray and triangle.
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        List<Point> result = plane.findIntersections(ray);
        if (result == null) {
            return null;
        }
        Point p = ray.getP0();
        Vector v=ray.getDir();
        // Create vector to the vertices
        Vector Subtract0 = vertices.get(0).subtract(p);
        Vector Subtract1 = vertices.get(1).subtract(p);
        Vector Subtract2 = vertices.get(2).subtract(p);

        // Create normal form Sides
        Vector crossProduct_normalize0 = Subtract0.crossProduct(Subtract1).normalize();
        Vector crossProduct_normalize1 = Subtract1.crossProduct(Subtract2).normalize();
        Vector crossProduct_normalize2 = Subtract2.crossProduct(Subtract0).normalize();

        // arr_NdotProductDir= normal * direction of ray
        double NdotProductDir0 = crossProduct_normalize0.dotProduct(v);
        double NdotProductDir1 = crossProduct_normalize1.dotProduct(v);
        double NdotProductDir2 = crossProduct_normalize2.dotProduct(v);
        if (Util.isZero(NdotProductDir0) || Util.isZero(NdotProductDir1) || Util.isZero(NdotProductDir2))
            return null;

        // The point is inside if all
        if (!(Util.checkSign(NdotProductDir0, NdotProductDir1)) || !(Util.checkSign(NdotProductDir1, NdotProductDir2))
                || !(Util.checkSign(NdotProductDir2, NdotProductDir0)))
            return null;

        double distance = result.get(0).distance(ray.getP0());

        return Util.alignZero( distance - maxDistance) <= 0 ? List.of(new GeoPoint(this, result.get(0))) : null;

    }
}
