package groupbySumRunnable;

import utils.*;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class Thread_N_Uniform {
    public static void main(String[] args) throws InterruptedException {
        final int numOfThread = Integer.parseInt(args[args.length-4]);
        final long N = Long.parseLong(args[args.length-3]);
        final long numOfKey = Long.parseLong(args[args.length-2]);
        final long numOfVal = Long.parseLong(args[args.length-1]);

        final long seed = 1001L;
        UniformStream stream = new UniformStream(numOfKey, numOfVal, seed);
        Iterator<KV<Long, Long>> input = stream.finite(N);

        // initialize the buffer
        BlockingQueue<KV<Long, Long>> buffer = new LinkedBlockingDeque<>(8096);

        // multi thread setting
        ExecutorService executor = Executors.newFixedThreadPool(numOfThread);

        // each thread should have its own hashmap
        HashMap<Long,Long>[] results = new HashMap[numOfThread];
        for (int i = 0; i < numOfThread; i++) {
            results[i] = new HashMap<>();
        }

        // an array that records the end time for each thread
        long[] endTimes = new long[numOfThread];

        long start = System.nanoTime();
        /* == begin task == */
        executor.execute(new StreamProducer(numOfThread, input, UniformStream.EOS(), buffer));
        for (int i = 0; i < numOfThread; i++) {
            HashMap<Long, Long> resAddr = results[i];
            executor.execute(new GrpSumWorker(i, endTimes, resAddr, buffer));
        }
        executor.shutdown();
        /* == end task == */

        while (true) {
            boolean flag = true;
            for (int i = 0; i < numOfThread; i++) {
                if (endTimes[i] == 0)
                    flag = false;
            }
            if (flag)
                break;
            else
                Thread.sleep(5000);
        }

        long end = -1L;
        for (int i = 0; i < numOfThread; i++) {
            end = end < endTimes[i] ? endTimes[i] : end;
        }
        HashMap<Long,Long> mergedResults = new HashMap<>();
        for (int i = 0; i < numOfThread; i++) {
            HM.merge(mergedResults, results[i], (sum1,sum2) -> sum1+sum2);
        }
        System.out.println("Totally " + mergedResults.size() + " keys collected.");
        for (long i = 0; i < 3; i++) {
            System.out.println("key: "+i+" , "+ mergedResults.get(i));
        }

        long timeNano = end - start;
        long rate = (N * 1000L * 1000 * 1000) / timeNano;
        System.out.println("=======================================");
        System.out.println("Throughput: "+ Format.longToString(rate));
    }
}
