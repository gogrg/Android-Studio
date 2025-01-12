package com.example.gasu_studing.myUtils;

import android.widget.TextView;

import java.util.concurrent.Callable;

public class ThreadForTextView extends Thread {
    protected TextView textView;
    int timeSleep;
    protected boolean sleepFlag;

    public ThreadForTextView(TextView textView, int timeSleep) {
        this.textView = textView;
        this.timeSleep = timeSleep;
        this.sleepFlag = false;
    }

    @Override
    public void run() {
        while (true){
            if (sleepFlag){
                try{
                    Thread.sleep(timeSleep);
                }
                catch(InterruptedException e){
                    System.out.println(e);
                }
                sleepFlag = false;
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

    public void sleepMe(){
        sleepFlag = true;
    }
}
