package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Reader { //Класс, отвечающий за считку и вывод данных. Очень удобен, тк позволяет не думать вне себя о том как хранятся, записываются и выводятся данные

    public ArrayList<Point> read(String filename) { // точки считываются в массив. в элемент массива добавляется координата х и у
        ArrayList<Point> list = new ArrayList<Point>();
        try {
            Scanner fin = new Scanner(new File(filename));
            int n = fin.nextInt();
            for(int i = 0; i<n; i++) {
                list.add(new Point(fin.nextInt(), fin.nextInt()));
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void write(Circle ans, ArrayList<Point> points, String filename) {
        try {
            PrintStream fout = new PrintStream(new File(filename));
            int n = points.size();
            fout.println(ans.toString());
            fout.println(n);
            for(int i = 0; i < n; i++) {
                fout.println(points.get(i).x + " " + points.get(i).y);
            }
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
