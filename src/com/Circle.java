package com;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ilya on 09.03.2017.
 */
public class Circle extends JPanel{
    int cx;
    int cy;
    int r;


      public Circle(Point a1, Point a2, Point a3) {
         int  s1, s2, s3;
         int area;
         int p;
        s1 = (int) Math.sqrt((a1.x - a2.x) * (a1.x - a2.x) + (a1.y - a2.y) * (a1.y - a2.y));
        s2 = (int) Math.sqrt((a3.x - a2.x) * (a3.x - a2.x) + (a3.y - a2.y) * (a3.y - a2.y));
        s3 = (int) Math.sqrt((a1.x - a3.x) * (a1.x - a3.x) + (a1.y - a3.y) * (a1.y - a3.y));
        p = (s1 + s2 + s3) / 2;
        area = (int) Math.sqrt(p * (p - s1) * (p - s2) * (p - s3));
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
            this.cx = (int) ((a1a2.y*a1a3.y*ac1.y+a1a3.y*a1a2.x*ac1.x-a1a2.y*a1a3.y*ac2.y-a1a2.y*a1a3.x*ac2.x)/(a1a2.x*a1a3.y-a1a3.x*a1a2.y));
            this.cy = (int) ((a1a2.y*ac1.y+a1a2.x*ac1.x-a1a2.x*cx)/a1a2.y);
        }
        if (a1a2.y == 0) {
                this.cx = ac1.x;
                this.cy = (int) ((a1a3.y * ac2.y + a1a3.x * ac2.x - a1a3.x * cx) / a1a3.y);
            }
        if (a1a3.y == 0) {
            this.cx = ac2.x;
            this.cy = (int) ((a1a2.y * ac1.y + a1a2.x * ac1.x - a1a2.x * cx) / a1a2.y);
        }
    }

    public Circle(int cx,      int cy,       int r) {
          this.cx = cx;
          this.cy = cy;
          this.r = r;
    }

    public boolean point_in_side(Point a){
        if(((this.cx - a.x)*(this.cx - a.x) +(this.cy - a.y)*(this.cy - a.y)- this.r*this.r)<=0){
            return true;
        }
        else{
            return false;
        }
    }

    static public boolean isAccesible(Point a1, Point a2, Point a3){
        int  s1, s2, s3;
        int area;
        int p;
        s1 = (int) Math.sqrt((a1.x - a2.x) * (a1.x - a2.x) + (a1.y - a2.y) * (a1.y - a2.y));
        s2 = (int) Math.sqrt((a3.x - a2.x) * (a3.x - a2.x) + (a3.y - a2.y) * (a3.y - a2.y));
        s3 = (int) Math.sqrt((a1.x - a3.x) * (a1.x - a3.x) + (a1.y - a3.y) * (a1.y - a3.y));
        p = (s1 + s2 + s3) / 2;
        area = (int) Math.sqrt(p * (p - s1) * (p - s2) * (p - s3));
        if (area == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public void paint(Graphics g){
        g.setColor(Color.red);
        g.drawOval(0, 0, 2*r ,2*r);

    }

    @Override
    public String toString() {
        if (r == 0) {
            return " No such circle";
        }
        else {
            return "{" + cx +  ", " + cy + "; " + r + '}';
        }
    }

}
