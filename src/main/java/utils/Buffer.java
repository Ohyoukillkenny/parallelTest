package utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Buffer<A> {
    // Concurrent collections are more efficient
    private BlockingQueue<A> list;
    private int size;
    public Buffer(int size) {
        this.list = new LinkedBlockingDeque<>(size);
        this.size = size;
    }
    public void add(A value) throws InterruptedException {
        list.put(value);
    }

    public A poll() throws InterruptedException {
        return list.take();
    }

    public int getSize() {
        return size;
    }

//    private Queue<A> list;
//    private int size;
//    public Buffer(int size) {
//        this.list = new LinkedList<>();
//        this.size = size;
//    }
//    public void add(A value) throws InterruptedException {
//        synchronized (this) {
//            while (list.size() >= size) {
//                wait();
//            }
//            list.add(value);
//            notify();
//        }
//    }
//    public A poll() throws InterruptedException {
//        synchronized (this) {
//            while (list.size() == 0) {
//                wait();
//            }
//            A value = list.poll();
//            notify();
//            return value;
//        }
//    }
}
