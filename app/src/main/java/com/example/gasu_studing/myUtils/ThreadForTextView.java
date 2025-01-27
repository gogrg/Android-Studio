package com.example.gasu_studing.myUtils;

import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.Callable;

public class ThreadForTextView implements Callable<Integer> {
    protected TextView textView;
    protected int timeSleep;
    //не придумал, что тут будет лучше, volatile или sinchronized, так как только внутри потока используются переменные и обращаюсь к ним,
    // но мне из того, что нагуглил, думаю, что volatile, так как другие потоки сюда не обращаются
    protected volatile boolean sleepFlag;
    protected volatile boolean startFlag = false;

    protected EventQueue eventQueue;

    public ThreadForTextView(TextView textView, EventQueue eventQueue) {
        this.textView = textView;
        this.sleepFlag = false;
        startFlag = true;
        this.eventQueue = eventQueue;

        textView.setOnTouchListener(new TextViewTouchListener(this));

        Log.d("TAG", "Construcor class ThreadForTextView started. Value startFlag - " + startFlag);
    }

    @Override
    public Integer call() {
        while (true) {
            try {
                Log.d("TAG", "sleepFlag = " + sleepFlag);
                if (sleepFlag) {
                    Thread.sleep(timeSleep);
                    continue;
                }
                Thread.sleep(100);

                eventQueue.addEvent(() -> {
                    textView.setText(String.valueOf(Integer.parseInt(textView.getText().toString()) + 1));
                }, "Increment textView" + textView.getId());


            } catch (InterruptedException e) {
                System.out.println("Thread in interrupted");
            }
        }

    }


    public boolean checkStart() {
        return startFlag;
    }

    public void sleepMe() {
        sleepFlag = true;
    }

    public void unsleepMe() {
        sleepFlag = false;
    }
}
