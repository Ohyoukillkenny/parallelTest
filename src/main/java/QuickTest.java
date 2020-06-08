import utils.Format;
import utils.Pair;

import static utils.Computation.computePI;

public class QuickTest {

    public static void main(String[] args) {
//        long N = 1024 * 1024 * 1024;
        long N =1024 * 1024 * 4;
        long start = System.nanoTime();
        /* == begin task == */
        Pair<Double,Double>  results = computePI(N, 1001L);
        /* == end task == */
        System.out.println("pi: " + results);
        long end = System.nanoTime();
        long timeNano = end - start;
        long duration = timeNano / 1000_000;
        System.out.println("=======================================");
        System.out.println("Duration (milli-sec): "+ Format.longToString(duration));


//        ThreadGroup tg = new ThreadGroup("main");
//        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
