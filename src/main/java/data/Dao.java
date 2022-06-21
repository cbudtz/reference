package data;

import java.util.LinkedList;
import java.util.List;

public class Dao implements IDao {
    private final LinkedList<Integer> queue = new LinkedList<>();
    private final Object emptyLock = new Object();

    @Override
    public void start() {
        new Thread(()->{
            while(true){
                while (queue.size()<20){
                    synchronized (emptyLock){
                        try {
                           // System.out.println("Waiting on lock - Queue size: " + queue.size());
                            emptyLock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                List<Integer> copyList = new LinkedList<>(queue);
                synchronized (queue){
                    queue.clear();
                }
                System.err.println("Saving " + copyList.size() + " Integers");
            }
        }).start();
    }

    @Override
    public void enqueue(int i) {
        synchronized (queue){
            queue.add(i);
       //     System.out.println("enqueueing");
        }
        synchronized (emptyLock){
            emptyLock.notifyAll();
        //    System.out.println("Notifying");
        }

    }
}
