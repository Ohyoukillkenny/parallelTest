package computePI_Stream2;

import utils.Computation;
import utils.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class TaskWorker implements Callable<Pair<Double, Double>> {
    private final BlockingQueue<Long> inChannel;
    private long cntOfReceivedItems;
    private long sumOfReceivedItems;
    private long cntOfSamples;
    private int id;


    public TaskWorker(int id, BlockingQueue<Long> inChannel) {
        this.inChannel = inChannel;
        this.id = id;
        cntOfReceivedItems = 0;
        sumOfReceivedItems = 0;
        cntOfSamples = 0;
    }

    @Override
    public Pair<Double, Double> call() {
        long seed = 1L;
        while (true) {
            try {
                long item = inChannel.take();
                if (item < 0L){
                    // now stream terminates
                    System.out.println("Worker " + id + " has received " + cntOfReceivedItems + " items.");
                    double pi = 4 * cntOfSamples / (double) sumOfReceivedItems;
                    double error = 100 * Math.abs(pi-Math.PI)/Math.PI;
                    return new Pair<>(pi, error);
                }
                cntOfReceivedItems ++;
                sumOfReceivedItems += item;
                cntOfSamples += Computation.PISampling(item, seed++);
            }
            catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
    }
}
