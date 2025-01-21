package com.example.gasu_studing.myUtils;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.gasu_studing.R;

class TextViewTouchListener implements View.OnTouchListener {
    private ThreadForTextView threadThisTextView;

    public TextViewTouchListener(ThreadForTextView thread) {
        threadThisTextView = thread;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (threadThisTextView.checkStart()) {
                    controlIncrementThread(false);
                }

                Log.d("TAG", "Event: " + event.getAction());
                return true;

            case MotionEvent.ACTION_UP:

                if (threadThisTextView.checkStart()) {
                    controlIncrementThread(true);
                }

                Log.d("TAG", "Event: " + event.getAction());
                return true;


            default:
                return false;
        }
    }

    private void controlIncrementThread(boolean status) {
        if (status) {
            threadThisTextView.unsleepMe();
        } else {
            threadThisTextView.sleepMe();
        }
    }
}
