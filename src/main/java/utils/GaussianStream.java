package utils;

import java.util.Iterator;
import java.util.Random;

public class GaussianStream {
    // keys with gaussian distribution
    private final long rangeOfVals;
    private final Random rnd;
    private final double mu;
    private final double sigma;

    public GaussianStream(long numOfV, long seed, double mu, double sigma) {
        rangeOfVals = numOfV;
        rnd = new Random(seed);
        this.mu = mu;
        this.sigma = sigma;
    }

    public Iterator<KV<Long,Long>> infinite(){
        return new Iterator<KV<Long,Long>>() {

            public boolean hasNext() {
                return true;
            }

            public KV<Long,Long> next() {
                long key = (long) (rnd.nextGaussian()*sigma + mu);
                key = key > 0 ? key : 0;
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
                long key = (long) (rnd.nextGaussian()*sigma + mu);
                key = key > 0 ? key : 0;
                KV<Long, Long> ret = new KV<>(key, Prob.nextLong(rnd, rangeOfVals));
                cnt ++;
                return ret;
            }
        };
    }

    public static void main(String[] args) {
        GaussianStream stream = new GaussianStream(100, 1L, 50.0, 10.0);
        Iterator<KV<Long, Long>> input = stream.finite(1000);
        while (input.hasNext()) {
            System.out.println(input.next());
        }
    }
}
