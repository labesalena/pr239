package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main{
    private static ArrayList<Point> points = new ArrayList<Point>();
    static Circle c_ans = new Circle(0,0,0);
    static Panel pointpane = new Panel();

    public static void clear_pointpane() {
        pointpane.removeAll();
        pointpane.repaint();
        pointpane.revalidate();
    }

    public static void paint_pointpane() {
        clear_pointpane();
        for (int i = 0; i < points.size(); i++) {
            Point b = points.get(i);
            b.setBounds(b.x, b.y, b.x + 3, b.y + 3);
            pointpane.add(b);

            JLabel p_label = new JLabel("(" + b.x + ":" + b.y + ")");
            p_label.setBounds(b.x, b.y, 100, 25);
            pointpane.add(p_label);
        }
        if (c_ans.r > 0) {
            Point center = new Point(c_ans.cx, c_ans.cy);
            center.setBounds(center.x, center.y, center.x + 3, center.y + 3);
            pointpane.add(center);

            JLabel c_label = new JLabel("circle (" + center.x + ":" + center.y + ")");
            c_label.setBounds(center.x, center.y, 100, 25);
            pointpane.add(c_label);

            c_ans.setBounds(c_ans.cx - c_ans.r, c_ans.cy - c_ans.r, 2 * c_ans.r, 2 * c_ans.r);
            pointpane.add(c_ans);
        }
        pointpane.repaint();
        pointpane.revalidate();


    }

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

        JLabel X = new JLabel("X:");
        X.setBounds(2, 25, 15, 25);
        butPanel.add(X);
        JLabel Y = new JLabel("Y:");
        Y.setBounds(45, 25, 15, 25);
        butPanel.add(Y);
        final JTextField x = new JTextField();
        x.setBounds(17, 25, 25, 25);
        butPanel.add(x);
        final JTextField y = new JTextField();
        y.setBounds(60, 25, 25, 25);
        butPanel.add(y);


        JLabel addRandomPoints = new JLabel("Добавить рандомное количество точек");
        addRandomPoints.setBounds(2, 100, 300, 25);
        butPanel.add(addRandomPoints);

        JLabel N = new JLabel("NUM:");
        N.setBounds(2, 120, 30, 25);
        butPanel.add(N);
        final JTextField n = new JTextField();
        n.setBounds(35, 120, 25, 25);
        butPanel.add(n);
        

        JButton button_add_one = new JButton("Добавить");
        button_add_one.setBounds(2, 50, 160, 40);
        butPanel.add(button_add_one);
        button_add_one.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int X = (!x.getText().equals("") ? Integer.parseInt(x.getText()) : 0);
                int Y = (!y.getText().equals("") ? Integer.parseInt(y.getText()) : 0);
                if ((X > 0) && (Y > 0)) {
                    Point b = new Point(X, Y);
                    points.add(b);
                }
                paint_pointpane();
            }
        });

        JButton button_add_few = new JButton("Добавить");
        button_add_few.setBounds(2, 150, 160, 40);
        butPanel.add(button_add_few);
        button_add_few.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int N = (!n.getText().equals("") ? Integer.parseInt(n.getText()) : 0);
                if (N > 0) {
                    for (int i = 0; i < N; i++) {
                        Point b = new Point((int) (Math.random() * (frame.getWidth() - 350)) + 50, (int) (Math.random() * (frame.getHeight() - 100))+ 50);
                        points.add(b);
                    }
                }
                paint_pointpane();
            }
        });



        JButton button2 = new JButton("Очистить");
        button2.setBounds(2, 350, 160, 40);
        butPanel.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                points.clear();
                c_ans = new Circle(0,0,0);
                clear_pointpane();
            }
        });


        JButton button3 = new JButton("Решить задачу");
        button3.setBounds(2, 300, 160, 40);
        butPanel.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c_ans = solve();
                JTextField t = new JTextField(c_ans.toString());
                t.setBounds(20,500,200,100);
                butPanel.add(t);
                paint_pointpane();
            }
        });

        JButton button4 = new JButton("Считать из файла");
        button4.setBounds(2, 250, 160, 40);
        butPanel.add(button4);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear_pointpane();
                c_ans = new Circle(0,0,0);

                points = new Reader().read("in.txt");
                System.out.println("считано");
                paint_pointpane();
            }
        });

        JButton button5 = new JButton("Записать в файл");
        button5.setBounds(2, 200, 160, 40);
        butPanel.add(button5);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reader().write(c_ans, points, "out.txt");
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

    public static Circle solve() {
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
        if (max_kolvo == -1) {
            System.out.println("No such circle");
            return new Circle(0,0,0);
        } else {
            Circle c = new Circle(points.get(maxi), points.get(maxj), points.get(maxk));
            System.out.println(max_kolvo);
            System.out.println(c);
            System.out.println("выполнено");
            return c;
        }
    }
}
