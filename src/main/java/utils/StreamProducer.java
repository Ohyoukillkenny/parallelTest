package utils;

import java.util.Iterator;

public class StreamProducer implements Runnable {
    private int numOfThreads;
    private Iterator<KV<Long,Long>> input;
    private KV<Long,Long> eos;
    private Buffer<KV<Long, Long>> buffer;

    public StreamProducer(int numOfThreads, Iterator<KV<Long,Long>> input, KV<Long,Long> eos, Buffer<KV<Long, Long>> buffer) {
        this.numOfThreads = numOfThreads;
        this.input = input;
        this.buffer = buffer;
        this.eos = eos;
    }

    @Override
    public void run() {
        try {
            while (input.hasNext()){
                buffer.add(input.next());
            }
            for (int i = 0; i < numOfThreads; i++) {
                buffer.add(eos);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
