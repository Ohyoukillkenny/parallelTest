package groupbySum;

import utils.Format;
import utils.KV;
import utils.UniformStream;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Thread_N_Uniform {
    public static void main(String[] args) {
        final int numOfThread = Integer.parseInt(args[args.length-4]);
        final long N = Long.parseLong(args[args.length-3]);
        final long numOfKey = Long.parseLong(args[args.length-2]);
        final long numOfVal = Long.parseLong(args[args.length-1]);

        final long seed = 1001L;
        UniformStream stream = new UniformStream(numOfKey, numOfVal, seed);
        Iterator<KV<Long, Long>> input = stream.finite(N);

        // multi thread setting
        GroubySumThread[] threadPool = new GroubySumThread[numOfThread];

        // each thread should have its own hashmap
        HashMap<Long,Long>[] results = new HashMap[numOfThread];
        Arrays.fill(results, new HashMap<>());

        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new GroubySumThread(results[i]);
        }

        long start = System.nanoTime();
        /* == begin task == */
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].start();
        }
        while (input.hasNext()){
            KV<Long,Long> item = input.next();
            int index = (int) (item.key % numOfThread);
            threadPool[index].next(item);
        }
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i].terminate();
        }

        /* == end task == */
        long end = System.nanoTime();

        HashMap<Long,Long> mergedResults = new HashMap<>();
        for (int i = 0; i < numOfThread; i++) {
            mergedResults.putAll(results[i]);
        }
        System.out.println("Totally " + mergedResults.size() + " key-value pairs collected.");
        for (long i = 0; i < 3; i++) {
            System.out.println("key: "+i+" , "+ mergedResults.get(i));
        }

        long timeNano = end - start;
        long rate = (N * 1000L * 1000 * 1000) / timeNano;
        System.out.println("=======================================");
        System.out.println("Throughput: "+ Format.longToString(rate));
    }
}
