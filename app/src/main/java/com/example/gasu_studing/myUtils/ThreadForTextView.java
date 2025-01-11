package com.example.gasu_studing.myUtils;

import android.widget.TextView;

import java.util.concurrent.Callable;

public class ThreadForTextView extends Thread {
    protected TextView textView;

    public ThreadForTextView(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void run() {
        while (true){

            try {
                Thread.sleep(100);
                textView.setText(String.valueOf(Integer.parseInt(textView.getText().toString()) + 1));
            } catch (InterruptedException e) {
                System.out.println("Thread in interrupted");
            }
        }

    }
}
