package computePI_Stream2;

import utils.Computation;
import utils.Format;
import utils.Pair;

public class Runner_Sequential {
    public static void main(String[] args) {
        final long lengthOfStream = Long.parseLong(args[args.length-2]);
        final long numOfSamplings = Long.parseLong(args[args.length-1]);
        
        long start = System.nanoTime();
        /* == begin task == */
        long cntOfSamples = 0L;
        long cntOfSamplings = 0L;
        long seed = 1L;
        for (int i = 0; i < lengthOfStream; i++) {
            cntOfSamples += Computation.PISampling(numOfSamplings, seed++);
            cntOfSamplings += numOfSamplings;
        }
        
        double pi = 4 * cntOfSamples / (double) cntOfSamplings;
        double error = 100 * Math.abs(pi-Math.PI)/Math.PI;
        
        Pair<Double, Double> result = new Pair<>(pi, error);
        System.out.println("pi: " + result.left + ", error: " + result.right);
        /* == end task == */
        long end = System.nanoTime();

        long timeNano = end - start;
        long rate = (lengthOfStream * 1000L * 1000 * 1000) / timeNano;
        System.out.println("=======================================");
        System.out.println("Throughput: "+ Format.longToString(rate));
    }
}
