package grpSum;

import utils.Buffer;
import utils.KV;
import utils.KVStream;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class GrpSumWorker implements Callable<Long> {

    private Buffer<KV<Long,Long>> buffer;
    private ConcurrentHashMap<Long,Long> hashMap;
    private long cnt = 0;

    public GrpSumWorker(Buffer<KV<Long,Long>> buffer, ConcurrentHashMap<Long,Long> hashMap) {
        this.buffer = buffer;
        this.hashMap = hashMap;
    }

    @Override
    public Long call() throws Exception {
        while (true) {
            try {
                KV<Long,Long> item = buffer.poll();
                if (KVStream.isEOS(item)){
                    break;
                }
                cnt ++;
                if (hashMap.containsKey(item.key)){
                    long sum = hashMap.get(item.key);
                    hashMap.put(item.key, sum + item.val);
                } else {
                    hashMap.put(item.key, item.val);
                }
            }
            catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
        return cnt;
    }
}
