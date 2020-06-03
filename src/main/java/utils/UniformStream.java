package utils;

import java.util.Iterator;
import java.util.Random;

public class UniformStream {
    // keys are uniformly distributed
    private final long numOfKeys;
    private final long rangeOfVals;

    private final Random rnd;

    public UniformStream(long numOfK, long numOfV, long seed) {
        numOfKeys = numOfK;
        rangeOfVals = numOfV;
        rnd = new Random(seed);
    }

    public Iterator<KV<Long,Long>> infinite(){
        return new Iterator<KV<Long,Long>>() {
            public boolean hasNext() {
                return true;
            }

            public KV<Long,Long> next() {
                long key = Prob.nextLong(rnd, numOfKeys);
                return new KV<>(key, Prob.nextLong(rnd, rangeOfVals));
            }
        };
    }

    public Iterator<KV<Long, Long>> finite(long N){
        return new Iterator<KV<Long,Long>>() {
            long cnt = 0;
            public boolean hasNext() {
                return cnt < N;
            }

            public KV<Long,Long> next() {
                long key = Prob.nextLong(rnd, numOfKeys);
                KV<Long, Long> ret = new KV<>(key, Prob.nextLong(rnd, rangeOfVals));
                cnt ++;
                return ret;
            }
        };
    }

    public static void main(String[] args) {
        UniformStream stream = new UniformStream(10, 100, 1L);
        Iterator<KV<Long, Long>> input = stream.finite(1000);
        while (input.hasNext()) {
            System.out.println(input.next());
        }
    }
}
