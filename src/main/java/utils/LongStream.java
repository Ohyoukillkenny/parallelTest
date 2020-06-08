package utils;

import java.util.Iterator;
import java.util.Random;

public class LongStream implements Stream<Long> {
    final private long leftBound;
    final private long rightBound;
    private final Random rnd;

    public LongStream(long leftBound, long rightBound, long seed) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.rnd = new Random(seed);
    }


    @Override
    public Iterator<Long> infinite() {
        return new Iterator<Long>() {
            public boolean hasNext() {
                return true;
            }

            public Long next() {
                return Prob.nextLong(rnd, leftBound, rightBound);
            }
        };
    }

    @Override
    public Iterator<Long> finite(long N) {
        return new Iterator<Long>() {
            long cnt = 0;
            public boolean hasNext() {
                return cnt < N;
            }

            public Long next() {
                Long ret = Prob.nextLong(rnd, leftBound, rightBound);
                cnt ++;
                return ret;
            }
        };
    }

    public static Long EOS() {
        return -1L;
    }

    public static boolean isEOS(Long v) {
        return v < 0;
    }
}
