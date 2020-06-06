package groupbySumRunnable;

import utils.Format;
import utils.GaussianStream;
import utils.KV;

import java.util.HashMap;
import java.util.Iterator;

public class Thread_1_Gaussian {
    public static void main(String[] args) {
        final long seed = 1001L;
        final long N = 100_000_000;
        final long numOfVal = 100;
        final double muForGaussian = 1000.0;
        final double sigmaForGaussian = 100.0;
        GaussianStream stream = new GaussianStream(numOfVal, seed, muForGaussian, sigmaForGaussian);
        Iterator<KV<Long, Long>> input = stream.finite(N);
        HashMap<Long,Long> state = new HashMap<>();

        Runnable exe = () -> {
            while (input.hasNext()) {
                KV<Long,Long> item = input.next();
                if (state.containsKey(item.key)){
                    long sum = state.get(item.key);
                    state.put(item.key, sum + item.val);
                } else {
                    state.put(item.key, item.val);
                }
            }
        };

        long start = System.nanoTime();
        /* == begin task == */
        exe.run();
        /* == end task == */
        long end = System.nanoTime();
        System.out.println("Totally "+state.size()+" key-value pairs collected.");
        long timeNano = end - start;
        long rate = (N * 1000L * 1000 * 1000) / timeNano;
        System.out.println(Format.longToString(rate));
    }
}
