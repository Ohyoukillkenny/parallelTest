package computePI;

import utils.Format;
import utils.Pair;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ComputePI_Parallel {
    public static void main(String[] args) {
        int numOfThreads = Integer.parseInt(args[args.length-2]);
        long numOfSamplings = Long.parseLong(args[args.length-1]);
        long seed = 1001L;

        // initialize the executor
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);

        // results
        Future<Long>[] futures = new Future[numOfThreads];

        long start = System.nanoTime();
        /* == begin task == */
        for (int i = 0; i < numOfThreads; i++) {
            PIWorker worker = new PIWorker(numOfSamplings/numOfThreads, seed+i);
            futures[i] = executor.submit(worker);
        }
        executor.shutdown();

        long count = 0;
        for (int i = 0; i < numOfThreads; i++) {
            try {
                long cnt = futures[i].get();
                count += cnt;
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        // merge the results
        double pi = 4 * count / (double) numOfSamplings;
        double error = 100 * Math.abs(pi-Math.PI)/Math.PI;
        Pair<Double, Double> results = new Pair<>(pi, error);

        /* == end task == */
        long end = System.nanoTime();

        System.out.println(numOfThreads + " threads, pi: " + results.left + ", error: " + results.right);

        long timeNano = end - start;
        long duration = timeNano / 1000_000;
        System.out.println("Duration (in milli-sec): "+ Format.longToString(duration));
    }
}
