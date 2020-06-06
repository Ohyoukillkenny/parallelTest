package groupbySumRunnable;

import utils.KV;
import utils.UniformStream;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

public class GrpSumWorker implements Runnable {
    private HashMap<Long,Long> result;
    private BlockingQueue<KV<Long,Long>> buffer;
    private int id;
    private long[] endTimes;

    private long cnt = 0;

    public GrpSumWorker(int id, long[] endTimes, HashMap<Long,Long> result, BlockingQueue<KV<Long,Long>> buffer) {
        this.result = result;
        this.buffer = buffer;
        this.id = id;
        this.endTimes = endTimes;
    }

    @Override
    public void run() {
        while (true) {
            try {
                KV<Long,Long> item = buffer.take();
                if (UniformStream.isEOS(item)){
                    endTimes[id] = System.nanoTime();
                    System.out.println("Worker "+id+" terminates at: " + endTimes[id] + " (collected "+ cnt+" items)");
                    break;
                }
                cnt ++;
                if (result.containsKey(item.key)){
                    long sum = result.get(item.key);
                    result.put(item.key, sum + item.val);
                } else {
                    result.put(item.key, item.val);
                }
            }
            catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
    }



}
