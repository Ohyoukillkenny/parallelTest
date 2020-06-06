package grpSum;

import utils.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class Thread_N_Uniform {
    public static void main(String[] args) {
        final int numOfThread = Integer.parseInt(args[args.length-4]);
        final long N = Long.parseLong(args[args.length-3]);
        final long numOfKey = Long.parseLong(args[args.length-2]);
        final long numOfVal = Long.parseLong(args[args.length-1]);

        final long seed = 1001L;
        UniformStream stream = new UniformStream(numOfKey, numOfVal, seed);
        Iterator<KV<Long, Long>> input = stream.finite(N);

        // initialize the buffer
        Buffer<KV<Long,Long>> buffer = new Buffer<>(8096);
        // initialize the executor
        ExecutorService executor = Executors.newFixedThreadPool(numOfThread);

        // store the results
        List<Future<HashMap<Long,Long>>> futures = new ArrayList<>();

        long start = System.nanoTime();
        /* == begin task == */
        StreamProducer producerTask = new StreamProducer(numOfThread, input, UniformStream.EOS(), buffer);
        executor.execute(producerTask);
        for (int i = 0; i < numOfThread; i++) {
            Worker worker = new Worker(buffer);
            futures.add(executor.submit(worker));
        }
        // collect the results
        HashMap<Long,Long> finalRes = new HashMap<>();

        for (int i = 0; i < numOfThread; i++) {
            try {
                HashMap<Long, Long> res = futures.get(i).get();
                HM.merge(finalRes, res, (sum1, sum2) -> sum1+sum2);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        /* == end task == */
        long end = System.nanoTime();

        System.out.println("Totally " + finalRes.size() + " keys collected.");
        for (long i = 0; i < 3; i++) {
            System.out.println("key: "+i+" , "+ finalRes.get(i));
        }

        long timeNano = end - start;
        long rate = (N * 1000L * 1000 * 1000) / timeNano;
        System.out.println("=======================================");
        System.out.println("Throughput: "+ Format.longToString(rate));

    }
}
