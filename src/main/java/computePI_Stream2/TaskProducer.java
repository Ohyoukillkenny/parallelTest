package computePI_Stream2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TaskProducer implements Runnable {
    private final int numOfChannels;
    private final long lengthOfStream;
    // high num -> high complexity of computation
    private final long numOfSamplings;
    private final BlockingQueue<Long>[] channels;

    public TaskProducer(int numOfChannels, int channelSize, long lengthOfStream, long numOfSamplings) {
        this.numOfChannels = numOfChannels;
        this.lengthOfStream = lengthOfStream;
        this.numOfSamplings = numOfSamplings;
        channels = new BlockingQueue[numOfChannels];
        for (int i = 0; i < numOfChannels; i++) {
            channels[i] = new LinkedBlockingDeque<>(channelSize);
        }
    }

    public BlockingQueue<Long>[] getChannels() {
        return channels;
    }

    @Override
    public void run() {
        long cnt = 0L;
        int index = 0; // 0 -- numOfChannels-1
        while (cnt < lengthOfStream) {
            try {
                channels[index].put(numOfSamplings);
                index = (index + 1) % numOfChannels;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cnt ++;
        }
        // indicate the end of stream by sending -1L to workers
        for (int i = 0; i < numOfChannels; i++) {
            try {
                channels[i].put(-1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
