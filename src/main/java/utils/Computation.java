package utils;

import java.util.Random;

public class Computation {
    /* CalculatesPI by taking N sample points */
    public static Pair<Double,Double> computePI(long N, long seed)
    {
        long count = PISampling(N, seed);
        double pi = 4 * count / (double) N;
        double error = 100 * Math.abs(pi-Math.PI)/Math.PI;
        return new Pair<>(pi, error);
    }

    public static long PISampling(long N, long seed){
        double x,y;
        double r;
        long count = 0;
        Random rnd = new Random(seed);
        for (long i = 0; i < N; i++) {
            /*select random point*/
            x = rnd.nextDouble();
            y = rnd.nextDouble();
            r = Math.sqrt(x*x + y*y);
            if (r <= 1.0)
                count++;
        }
        return count;
    }
}
