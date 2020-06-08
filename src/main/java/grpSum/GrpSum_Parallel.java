package grpSum;

import utils.*;

import java.util.Iterator;
import java.util.concurrent.*;

public class GrpSum_Parallel {
    public static void main(String[] args) {
        final int numOfThread = Integer.parseInt(args[args.length-4]);
        final long N = Long.parseLong(args[args.length-3]);
        final long numOfKey = Long.parseLong(args[args.length-2]);
        final long numOfVal = Long.parseLong(args[args.length-1]);

        final long seed = 1001L;
        KVStream stream = new KVStream(numOfKey, numOfVal, seed);
        Iterator<KV<Long, Long>> input = stream.finite(N);

        // initialize the buffer
        Buffer<KV<Long,Long>> buffer = new Buffer<>(8096);
        // initialize the executor
        ExecutorService executor = Executors.newFixedThreadPool(numOfThread);

        Future<Long>[] futures = new Future[numOfThread];
        // results
        ConcurrentHashMap<Long,Long> results = new ConcurrentHashMap<>();

        long start = System.nanoTime();
        /* == begin task == */
        StreamProducer<KV<Long, Long>> producerTask = new StreamProducer<>(numOfThread, input, KVStream.EOS(), buffer);
        executor.execute(producerTask);
        for (int i = 0; i < numOfThread; i++) {
            GrpSumWorker worker = new GrpSumWorker(buffer, results);
            futures[i] = executor.submit(worker);
        }
        executor.shutdown();
        /* == end task == */
        long end = System.nanoTime();

        for (int i = 0; i < numOfThread; i++) {
            try {
                System.out.println("Worker " + i + " collects "+ futures[i].get() +" items)");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Totally " + results.size() + " keys collected.");
        for (long i = 0; i < 3; i++) {
            System.out.println("key: "+i+" , "+ results.get(i));
        }

        long timeNano = end - start;
        long rate = (N * 1000L * 1000 * 1000) / timeNano;
        System.out.println("=======================================");
        System.out.println("Throughput: "+ Format.longToString(rate));

    }
}
