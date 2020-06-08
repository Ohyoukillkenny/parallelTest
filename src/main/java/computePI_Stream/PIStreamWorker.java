package computePI_Stream;

import utils.Buffer;
import utils.Computation;
import utils.Pair;

import java.util.concurrent.Callable;

public class PIStreamWorker implements Callable<Pair<Double, Double>> {
    private Buffer<Pair<Long,Long>> buffer;
    private Pair<Double, Double> last;

    public PIStreamWorker(Buffer<Pair<Long,Long>> buffer) {
        this.buffer = buffer;
        last = null;
    }

    @Override
    public Pair<Double, Double> call() throws Exception {
        while (true) {
            try {
                Pair<Long,Long> item = buffer.poll();
                if (item.isEmpty()){
                    // now stream terminates
                    break;
                }
                last = Computation.computePI(item.left, item.right);
            }
            catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
        return last;
    }
}
