try (Scanner fin = new Scanner(new File("in.txt"));
        PrintStream fout = new PrintStream(new File("out.txt"))) {
package com.bot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Point> points = new ArrayList<Point>();
    static Panel pointpane = new Panel();
    public static void createGUI() {
        final JFrame frame = new JFrame("Testframe");
        frame.setPreferredSize(new Dimension(700, 700));
        JPanel panel = new JPanel(new BorderLayout());
        final Panel butPanel = new Panel();
        butPanel.setLayout(null);
        butPanel.setPreferredSize(new Dimension(250, 700));

        pointpane.setLayout(null);

        JLabel addPointwithCoords = new JLabel("Добавить точку по координатам");
        addPointwithCoords.setBounds(2, 2, 300, 25);
        butPanel.add(addPointwithCoords);
        JLabel addRandomPoints = new JLabel("Добавить рандомное количество точек");
        addRandomPoints.setBounds(2, 50, 300, 25);
        butPanel.add(addRandomPoints);
        JLabel X = new JLabel("X:");
        X.setBounds(2, 25, 15, 25);
        butPanel.add(X);
        JLabel Y = new JLabel("Y:");
        Y.setBounds(45, 25, 15, 25);
        butPanel.add(Y);
        JLabel N = new JLabel("NUM:");
        N.setBounds(2, 70, 30, 25);
        butPanel.add(N);
        final JTextField x = new JTextField();
        x.setBounds(17, 25, 25, 25);
        butPanel.add(x);
        final JTextField y = new JTextField();
        y.setBounds(60, 25, 25, 25);
        butPanel.add(y);
        final JTextField n = new JTextField();
        n.setBounds(35, 70, 25, 25);
        butPanel.add(n);

        // рисование тестового круга
       /*Circle c = new Circle(200,100,200);
        c.setBounds(0,0,1200,1200);
        pointpane.add(c);
        pointpane.repaint();
        pointpane.revalidate();*/


        JButton button1 = new JButton("Добавить точку");
        button1.setBounds(2, 100, 160, 40);
        butPanel.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int X = (!x.getText().equals("") ? Integer.parseInt(x.getText()) : 0);
                int Y = (!y.getText().equals("") ? Integer.parseInt(y.getText()) : 0);
                int N = (!n.getText().equals("") ? Integer.parseInt(n.getText()) : 0);
                if ((X > 0) && (Y > 0)) {
                    Point b = new Point(X, Y);
                    points.add(b);
                    b.setBounds(b.x, b.y, b.x + 3, b.y + 3);
                    pointpane.add(b);
                    pointpane.revalidate();
                    pointpane.repaint();
                } else {
                    if (N > 0) {
                        for (int i = 0; i < N; i++) {
                            Point b = new Point((int) (Math.random() * (frame.getWidth() - 250)), (int) (Math.random() * frame.getHeight()));
                            points.add(b);
                            b.setBounds(b.x, b.y, b.x + 3, b.y + 3);
                            pointpane.add(b);
                            pointpane.revalidate();
                            pointpane.repaint();
                        }
                    }
                }

            }
        });

        JButton button2 = new JButton("очистить");
        button2.setBounds(2, 300, 160, 40);
        butPanel.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < points.size(); i++) {
                    while (points.size() > 0) {
                        int index = points.size() - 1;
                        Point point = points.remove(index);
                        pointpane.remove(point);
                        pointpane.repaint();
                        pointpane.revalidate();
                    }
                }
            }
        });


        JButton button3 = new JButton("Решить задачу");
        button3.setBounds(2, 250, 160, 40);
        butPanel.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField t = new JTextField(solve());
                t.setBounds(20,500,200,100);
                butPanel.add(t);

            }
        });

        JButton button4 = new JButton("Считать из файла");
        button4.setBounds(2, 200, 160, 40);
        butPanel.add(button4);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("считано");
            }
        });

        JButton button5 = new JButton("Записать в файл");
        button5.setBounds(2, 150, 160, 40);
        butPanel.add(button5);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("записано");
            }
        });


        panel.add(pointpane, BorderLayout.CENTER);
        panel.add(butPanel, BorderLayout.EAST);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                createGUI();
            }
        });
    }

    public static String solve() {
        System.out.println("выполнено");
        for (int i = 0; i < points.size(); i++) {//n
            points.get(i); //arr[i]
        }

        int max_kolvo = -1;
        int maxi = -1;
        int maxj = -1;
        int maxk = -1;
        int l = points.size();

        for (int i = 0; i < l - 2; i++) {
            for (int j = i + 1; j < l - 1; j++) {
                for (int k = j + 1; k < l; k++) {
                    if (points.get(i).we_are_triangle(points.get(j), points.get(k))) {
                        int sum_kolvo = 0;
                        for (int t = 0; t < l; t++) {
                            if ((t != i) && (t != j) && (t != k) &&
                                    (Circle.isAccesible(points.get(i), points.get(j), points.get(k)) &&
                                    (new Circle(points.get(i), points.get(j), points.get(k)).point_in_side(points.get(t))))){
                                sum_kolvo++;
                            }
                            if (sum_kolvo > max_kolvo) {

                                max_kolvo = sum_kolvo;
                                maxi = i;
                                maxj = j;
                                maxk = k;
                            }
                        }
                    }
                }
            }
        }
        //   System.out.println("maxi = " + maxi);
        Circle c = new Circle(points.get(maxi), points.get(maxj), points.get(maxk));
        if (max_kolvo == -1) {

            System.out.println("No such circle");
        } else {
            System.out.println(max_kolvo);

            c.setBounds(0,0,600,600);
            pointpane.add(c);
            pointpane.repaint();
            pointpane.revalidate();
            System.out.println(c);
            return c.toString();}
        return c.toString() ;
    }


}
} catch (FileNotFoundException e) {
        e.printStackTrace();
        }
