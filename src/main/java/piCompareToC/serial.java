package piCompareToC;

import utils.Format;

import java.util.Random;

public class serial {

    static long throw_darts(long num_of_darts) {
        Random rnd = new Random();

        int i;
        long num_of_in_circle_darts = 0;
        double rand_x, rand_y, origin_dist;
        for (i = 0; i < num_of_darts; i++) {
            // Randomly generated x and y values
            rand_x = rnd.nextDouble();
            rand_y = rnd.nextDouble();

            // Distance between (x, y) from the origin
            origin_dist = rand_x * rand_x + rand_y * rand_y;

            // Checking if (x, y) lies inside the define
            // circle with R=1
            if (origin_dist <= 1)
                num_of_in_circle_darts ++;
        }
        return num_of_in_circle_darts;
    }

    public static void main(String[] args) {
        long num_of_darts = 500000000;
        double pi;
        long num_of_in_circle_darts;

        long start = System.nanoTime();

        num_of_in_circle_darts = throw_darts(num_of_darts);
        pi = 4 * num_of_in_circle_darts / (double) num_of_darts;

        long end = System.nanoTime();

        long duration = (end - start) / 1000_000;

        System.out.println("pi: "+pi);
        System.out.println("=======================================");
        System.out.println("Duration (in milli-sec): "+ Format.longToString(duration));
    }
}
