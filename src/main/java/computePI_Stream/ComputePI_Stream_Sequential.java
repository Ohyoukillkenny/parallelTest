package computePI_Stream;

import utils.Computation;
import utils.Format;
import utils.Pair;

import java.util.Iterator;

public class ComputePI_Stream_Sequential {
    public static void main(String[] args) {
        final long numOfSamplings = Long.parseLong(args[args.length-2]);
        final long lengthOfStream = Long.parseLong(args[args.length-1]);

        Iterator<Pair<Long,Long>> input = new Iterator<Pair<Long,Long>>() {
            long cnt = 0;
            @Override
            public boolean hasNext() {
                return cnt++ < lengthOfStream;
            }

            @Override
            public Pair<Long,Long> next() {
                // use cnt as seed
                return new Pair<>(numOfSamplings, cnt);
            }
        };

        // store the estimated value of pi and the error of computation
        Pair<Double,Double> last = null;

        long start = System.nanoTime();
        /* == begin task == */
        while (input.hasNext()){
            Pair<Long,Long> item = input.next();
            last = Computation.computePI(item.left, item.right);
        }
        /* == end task == */
        long end = System.nanoTime();

        System.out.println("pi: " + last.left + ", error: " + last.right);
        long timeNano = end - start;
        long rate = (lengthOfStream * 1000L * 1000 * 1000) / timeNano;
        System.out.println("=======================================");
        System.out.println("Throughput: "+ Format.longToString(rate));
    }
}
