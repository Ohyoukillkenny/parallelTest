package computePI;

import utils.Computation;

import java.util.concurrent.Callable;

public class PIWorker implements Callable<Long> {
    private long numOfSamplings;
    private long seed;

    public PIWorker(long numOfSamplings, long seed) {
        this.numOfSamplings = numOfSamplings;
        this.seed = seed;
    }

    @Override
    public Long call() throws Exception {
        return Computation.PISampling(numOfSamplings, seed);
    }
}
