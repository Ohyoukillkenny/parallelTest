package utils;

import java.util.Iterator;

public class StreamProducer<A> implements Runnable {
    private int numOfThreads;
    private Iterator<A> input;
    private A eos;
    private Buffer<A> buffer;

    public StreamProducer(int numOfThreads, Iterator<A> input, A eos, Buffer<A> buffer) {
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
