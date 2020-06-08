package computePI_Stream;

import utils.*;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ComputerPI_Stream_Parallel {
    public static void main(String[] args) {
        int numOfThreads = Integer.parseInt(args[args.length-3]);
        final long numOfSamplings = Long.parseLong(args[args.length-2]);
        final long lengthOfStream = Long.parseLong(args[args.length-1]);

        Iterator<Pair<Long, Long>> input = new Iterator<Pair<Long, Long>>() {
            long cnt = 0;

            @Override
            public boolean hasNext() {
                return cnt++ < lengthOfStream;
            }

            @Override
            public Pair<Long,Long> next() {
                // use cnt as seed
                return new Pair<>(numOfSamplings, cnt);
            }
        };

        // initialize the executor
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        // results
        Future<Pair<Double,Double>>[] futures = new Future[numOfThreads];

        Buffer<Pair<Long,Long>> buffer = new Buffer<>(1024);

        // set empty pair as the end stream marker
        StreamProducer<Pair<Long,Long>> producer = new StreamProducer<>(numOfThreads, input, Pair.empty(), buffer);


        long start = System.nanoTime();
        /* == begin task == */
        executor.execute(producer);
        for (int i = 0; i < numOfThreads; i++) {
            PIStreamWorker worker = new PIStreamWorker(buffer);
            futures[i] = executor.submit(worker);
        }
        executor.shutdown();
        /* == end task == */
        long end = System.nanoTime();

        // print the estimated value of pi and the error of computation for each thread
        for (int i = 0; i < numOfThreads; i++) {
            try {
                Pair<Double, Double> last = futures[i].get();
                System.out.println("thread "+ i + ", pi: " + last.left + ", error: " + last.right);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        long timeNano = end - start;
        long rate = (lengthOfStream * 1000L * 1000 * 1000) / timeNano;
        System.out.println("=======================================");
        System.out.println("Throughput: "+ Format.longToString(rate));
    }
}
