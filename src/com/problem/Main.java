import java.util.ArrayList;

/**
 * Created by ilya on 09.03.2017.
 */
public class Main {


    public static void main(String[] args) {

        ArrayList<Point> list;
        list = new Reader().read();
//        list = new Reader().read("input.txt");

        int l = list.size();

/*

        System.out.println(list.size());
        for (int i = 0; i < l; i++) {
            System.out.println(list.get(i));
        }




*/

        int max_kolvo = -1;
        int maxi = -1;
        int maxj = -1;
        int maxk = -1;

        for (int i = 0; i < l - 2; i++) {
            for (int j = i + 1; j < l - 1; j++) {
                for (int k = j + 1; k < l; k++) {
                    if (list.get(i).we_are_triangle(list.get(j), list.get(k))) {
                        int sum_kolvo = 0;
                        for (int t = 0; t < l; t++) {
                            if ((t != i)&&(t != j)&&(t != k)&&((new Circle(list.get(i), list.get(j), list.get(k)).point_in_side(list.get(t))))) {
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
        }
        else {
            System.out.println(max_kolvo);
            System.out.println(new Circle(list.get(maxi), list.get(maxj), list.get(maxk)));
        }
    }
}
