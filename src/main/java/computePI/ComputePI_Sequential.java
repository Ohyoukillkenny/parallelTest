package computePI;

import utils.Format;
import utils.Pair;

import static utils.Computation.computePI;

public class ComputePI_Sequential {
    public static void main(String[] args) {
        long numOfSamplings = Long.parseLong(args[args.length-1]);
        long seed = 1001L;
        long start = System.nanoTime();

        /* == begin task == */
        Pair<Double,Double> results = computePI(numOfSamplings, seed);
        /* == end task == */
        System.out.println("compute pi with " + numOfSamplings + " samples.");
        System.out.println("pi: " + results.left + ", error: " + results.right);
        long end = System.nanoTime();
        long timeNano = end - start;
        long duration = timeNano / 1000_000;
        System.out.println("=======================================");
        System.out.println("Duration (in milli-sec): "+ Format.longToString(duration));
    }
}
