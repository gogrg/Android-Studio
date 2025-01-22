package com.example.gasu_studing.myUtils;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.gasu_studing.R;

class TextViewTouchListener implements View.OnTouchListener {
    private ThreadForTextView threadThisTextView;

    private EventQueue eventQueue;
    public TextViewTouchListener(ThreadForTextView thread, EventQueue eventQueue) {
        threadThisTextView = thread;
        this.eventQueue = eventQueue;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
            //Log.d("TAG", "TextView " + v.getId() + " pressed");

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    if (threadThisTextView.checkStart()) {
                        eventQueue.addEvent(() -> {
                            threadThisTextView.sleepMe();
                        }, "TextView " + v.getId() + " pressed");
                    }

                    //Log.d("TAG", "TextView " + v.getId() + " Event: " + event.getAction());
                    //return true;

                case MotionEvent.ACTION_UP:

                    if (threadThisTextView.checkStart()) {
                        //controlIncrementThread(true);
                        eventQueue.addEvent(() -> {
                            threadThisTextView.unsleepMe();
                        }, "TextView " + v.getId() + " pressed");
                    }

                    //Log.d("TAG", "TextView " + v.getId() + " Event: " + event.getAction());
                    //return true;


                default:
                    //return false;
            }

        return true;
    }

    private void controlIncrementThread(boolean status) {
        if (status) {
            threadThisTextView.unsleepMe();
        } else {
            threadThisTextView.sleepMe();
        }
    }
}
