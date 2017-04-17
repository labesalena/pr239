/**
 * Created by ilya on 09.03.2017.
 */
public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean we_are_triangle(Point a1, Point a2){
        if ((a1.y-a2.y)*this.x+(a2.x-a1.x)*this.y+(a1.x*a2.y-a2.x*a1.y) == 0){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}
