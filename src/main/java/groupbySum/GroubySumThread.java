package groupbySum;

import utils.KV;

import java.util.HashMap;

public class GroubySumThread extends Thread {
    private HashMap<Long,Long> result;

    // to stop the thread
    private boolean exit;

    public GroubySumThread(HashMap<Long,Long> result) {
        this.result = result;
    }

    public void next(KV<Long,Long> item){
        if (result.containsKey(item.key)){
            long sum = result.get(item.key);
            result.put(item.key, sum + item.val);
        } else {
            result.put(item.key, item.val);
        }
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                Thread.sleep(512);
            }
            catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
    }

    public void terminate(){
        exit = true;
    }

}
