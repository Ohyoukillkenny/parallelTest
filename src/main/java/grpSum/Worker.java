package grpSum;

import utils.Buffer;
import utils.KV;
import utils.UniformStream;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class Worker implements Callable<HashMap<Long,Long>> {
    private HashMap<Long,Long> result;
    private Buffer<KV<Long,Long>> buffer;
    private long cnt = 0;

    public Worker(Buffer<KV<Long,Long>> buffer) {
        this.buffer = buffer;
        result = new HashMap<>();
    }

    @Override
    public HashMap<Long,Long> call() throws Exception {
        while (true) {
            try {
                KV<Long,Long> item = buffer.poll();
                if (UniformStream.isEOS(item)){
                    System.out.println("Worker terminates at: " + System.nanoTime() + " and collects "+ cnt+" items)");
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
        return result;
    }
}
