package com.example.gasu_studing.myUtils;

import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.Callable;
import java.util.concurrent.BlockingQueue;
public class ThreadForTextView implements Callable<Integer> {
    protected TextView textView;
    protected int timeSleep;
    protected boolean sleepFlag;
    protected boolean startFlag = false;

    public ThreadForTextView(TextView textView, EventQueue eventQueue) {
        this.textView = textView;
        this.sleepFlag = false;
        startFlag = true;

        textView.setOnTouchListener(new TextViewTouchListener(this, eventQueue));

        Log.d("TAG", "Construcor class ThreadForTextView started. Value startFlag - " + startFlag);
    }

    @Override
    public Integer call() {
        while (true) {
            if (sleepFlag) {
                continue;
            }
            try {
                Thread.sleep(100);
                textView.setText(String.valueOf(Integer.parseInt(textView.getText().toString()) + 1));
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
