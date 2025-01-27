package com.example.gasu_studing.myUtils;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventQueue {
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    public synchronized void addEvent(Runnable event, String message) {
        queue.add(event);
        messageQueue.add(message);
        Log.d("TAG", "Event " + message + " add in queue");
    }


    public void processEvents() {

        while (true) {
            if (!queue.isEmpty()) {
                try {
                    queue.take().run();
                    Log.d("TAG", "Event " + messageQueue.take() + " is started");
                    //Thread.sleep(500);
                } catch (InterruptedException e) {
                    Log.d("TAG", "Thread with queue is interrupted");
                }
            }
        }
    }
}
