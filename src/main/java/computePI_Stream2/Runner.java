package computePI_Stream2;

import utils.Format;
import utils.Pair;

import java.util.concurrent.*;

public class Runner {
    public static void main(String[] args) {
        final int numOfThreads = Integer.parseInt(args[args.length-4]);
        final int channelSize = Integer.parseInt(args[args.length-3]);
        final long lengthOfStream = Long.parseLong(args[args.length-2]);
        final long numOfSamplings = Long.parseLong(args[args.length-1]);

        // initialize the executor
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        // set the stream (task) producer
        TaskProducer producer = new TaskProducer(numOfThreads, channelSize, lengthOfStream, numOfSamplings);
        BlockingQueue<Long>[] channels = producer.getChannels();
        // set the workers
        TaskWorker[] workers = new TaskWorker[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            workers[i] = new TaskWorker(i, channels[i]);
        }

        // results
        Future<Pair<Double,Double>>[] results = new Future[numOfThreads];
        long start = System.nanoTime();
        /* == begin task == */
        executor.execute(producer);
        for (int i = 0; i < numOfThreads; i++) {
            results[i] = executor.submit(workers[i]);
        }
        executor.shutdown();
        // print the estimated value of pi and the error of computation for each thread
        for (int i = 0; i < numOfThreads; i++) {
            try {
                Pair<Double, Double> last = results[i].get();
                System.out.println("thread "+ i + ", pi: " + last.left + ", error: " + last.right);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        /* == end task == */
        long end = System.nanoTime();

        long timeNano = end - start;
        long rate = (lengthOfStream * 1000L * 1000 * 1000) / timeNano;
        System.out.println("=======================================");
        System.out.println("Throughput: "+ Format.longToString(rate));
    }
}
