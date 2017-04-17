import java.util.ArrayList;
import java.util.List;


public class Reader {
    ArrayList<Point> read() {
        ArrayList<Point> list = new ArrayList<Point>();
        list.add(new Point(1,1));
        list.add(new Point(3,3));
        list.add(new Point(3,1));
        list.add(new Point(5,1));
        list.add(new Point(0,3));
        return list;
    }

    ArrayList<Point> read(String filename) {
        return new ArrayList<Point>();
    }

    ArrayList<Point> read(int n) {
        ArrayList<Point> list = new ArrayList<Point>();
        for (int i = 0; i < n; i++){
            list.add(new Point(Math.random(), Math.random()));
        }
        return list;
    }

}
