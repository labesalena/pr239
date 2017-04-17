/**
 * Created by ilya on 09.03.2017.
 */
public class Circle {
    double cx;
    double cy;
    double r;

     Circle(Point a1, Point  a2, Point a3) {
        double s1, s2, s3;
        double area;
        double p;
        s1 = Math.sqrt((a1.x - a2.x) * (a1.x - a2.x) + (a1.y - a2.y) * (a1.y - a2.y));
        s2 = Math.sqrt((a3.x - a2.x) * (a3.x - a2.x) + (a3.y - a2.y) * (a3.y - a2.y));
        s3 = Math.sqrt((a1.x - a3.x) * (a1.x - a3.x) + (a1.y - a3.y) * (a1.y - a3.y));
        p = (s1 + s2 + s3) / 2;
        area = Math.sqrt(p * (p - s1) * (p - s2) * (p - s3));
        this.r = s1 * s2 * s3 / (4 * area);

        Point ac1 = new Point((a1.x + a2.x) / 2, (a1.y + a2.y) / 2);
        Point ac2 = new Point((a1.x + a3.x) / 2, (a1.y + a3.y) / 2);

        Vector a1a2 = new Vector();
        a1a2.x = (a2.x - a1.x);
        a1a2.y = (a2.y - a1.y);

        Vector a1a3 = new Vector();
        a1a3.x = (a3.x - a1.x);
        a1a3.y = (a3.y - a1.y);

        if ((a1a2.y != 0)&&(a1a3.y!=0)){
            this.cx =(a1a2.y*a1a3.y*ac1.y+a1a3.y*a1a2.x*ac1.x-a1a2.y*a1a3.y*ac2.y-a1a2.y*a1a3.x*ac2.x)/(a1a2.x*a1a3.y-a1a3.x*a1a2.y);
            this.cy = (a1a2.y*ac1.y+a1a2.x*ac1.x-a1a2.x*cx)/a1a2.y;
        }
        if (a1a2.y == 0) {
                this.cx = ac1.x;
                this.cy = (a1a3.y * ac2.y + a1a3.x * ac2.x - a1a3.x * cx) / a1a3.y;
            }
        if (a1a3.y == 0) {
            this.cx = ac2.x;
            this.cy = (a1a2.y * ac1.y + a1a2.x * ac1.x - a1a2.x * cx) / a1a2.y;
        }
    }

    public boolean point_in_side(Point a){
        if(((this.cx - a.x)*(this.cx - a.x) +(this.cy - a.y)*(this.cy - a.y)- this.r*this.r)<=0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "{" + cx +  ", " + cy + "; " + r + '}';
    }
}
