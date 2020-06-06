package groupbySumRunnable;

import utils.KV;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

public class StreamProducer implements Runnable {
    private Iterator<KV<Long,Long>> input;
    private BlockingQueue<KV<Long,Long>> buffer;
    private int numOfThreads;
    private KV<Long,Long> EOS;
    public StreamProducer(int numOfThreads, Iterator<KV<Long,Long>> input, KV<Long,Long> EOS, BlockingQueue<KV<Long,Long>> buffer) {
        this.numOfThreads = numOfThreads;
        this.input = input;
        this.buffer = buffer;
        this.EOS = EOS;
    }

    @Override
    public void run() {
        try {
            while (input.hasNext()){
                buffer.put(input.next());
            }
            for (int i = 0; i < numOfThreads; i++) {
                buffer.put(EOS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
