package computeSum;

import utils.Format;

import java.util.concurrent.*;

public class parallelSum {

    static double sum(long times){
        double sum = 0.0;
        double round = 10_000_000;
        for (long i = 0; i < times; i++) {
            for (double j = 0; j < round; j++) {
                sum += j;
            }
        }
        return sum;
    }

    static class Worker implements Callable<Double> {
        private final long numOfRounds;

        public Worker(long numOfRounds) {
            this.numOfRounds = numOfRounds;
        }

        @Override
        public Double call() throws Exception {
            return sum(numOfRounds);
        }
    }



    public static void main(String[] args) {
        int numOfThreads = Integer.parseInt(args[args.length-2]);
        long totalComplexity = Long.parseLong(args[args.length-1]);

        // initialize the executor
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);

        // results
        Future<Double>[] futures = new Future[numOfThreads];

        long start = System.nanoTime();
        /* == begin task == */
        for (int i = 0; i < numOfThreads; i++) {
            Worker worker = new Worker(totalComplexity / numOfThreads);
            futures[i] = executor.submit(worker);
        }
        executor.shutdown();

        double result = 0;
        for (int i = 0; i < numOfThreads; i++) {
            try {
                double sum = futures[i].get();
                result += sum;
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        /* == end task == */
        long end = System.nanoTime();

        System.out.println("complexity: " + totalComplexity + " with "+numOfThreads +" threads.");
        System.out.println("sum: "+result);
        long timeNano = end - start;
        long duration = timeNano / 1000_000;
        System.out.println("=======================================");
        System.out.println("Duration (in milli-sec): "+ Format.longToString(duration));
    }
}
