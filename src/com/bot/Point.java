package com.bot;
import javax.swing.*;
import java.awt.*;

/**
 * Created by nurgalievtr.18 on 10.04.2017.
 */
public class Point extends JPanel {
    public int x;
    public int y;
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(0,0,3,3);
        g.setColor(Color.red);
    }
    public boolean we_are_triangle(Point a1,Point a2){
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
