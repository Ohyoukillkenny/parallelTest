package groupbySum;

import utils.Format;
import utils.UniformStream;
import utils.KV;

import java.util.HashMap;
import java.util.Iterator;

public class Thread_Single_Uniform {
    public static void main(String[] args) {

        final long N = Long.parseLong(args[args.length-3]);
        final long numOfKey = Long.parseLong(args[args.length-2]);
        final long numOfVal = Long.parseLong(args[args.length-1]);

        final long seed = 1001L;
        UniformStream stream = new UniformStream(numOfKey, numOfVal, seed);
        Iterator<KV<Long, Long>> input = stream.finite(N);
        HashMap<Long,Long> results = new HashMap<>();

        long start = System.nanoTime();
        /* == begin task == */
        while (input.hasNext()) {
            KV<Long,Long> item = input.next();
            if (results.containsKey(item.key)){
                long sum = results.get(item.key);
                results.put(item.key, sum + item.val);
            } else {
                results.put(item.key, item.val);
            }
        }
        /* == end task == */
        long end = System.nanoTime();

        System.out.println("Totally "+results.size()+" key-value pairs collected.");
        for (long i = 0; i < 3; i++) {
            System.out.println("key: "+i+" , "+ results.get(i));
        }

        long timeNano = end - start;
        long rate = (N * 1000L * 1000 * 1000) / timeNano;
        System.out.println("=======================================");
        System.out.println("Throughput: "+ Format.longToString(rate));
    }
}
