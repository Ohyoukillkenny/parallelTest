package computePI_Stream2;

import utils.Computation;
import utils.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class TaskWorker implements Callable<Pair<Double, Double>> {
    private final BlockingQueue<Long> inChannel;
    private Pair<Double, Double> last;
    private int id;


    public TaskWorker(int id, BlockingQueue<Long> inChannel) {
        this.inChannel = inChannel;
        this.id = id;
    }

    @Override
    public Pair<Double, Double> call() {
        long count = 1L;
        while (true) {
            try {
                long item = inChannel.take();
                if (item < 0L){
                    // now stream terminates
                    System.out.println("Worker " + id + " has received " + count + " items.");
                    break;
                }
                long seed = count ++;
                last = Computation.computePI(item, seed);
            }
            catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
        return last;
    }
}
