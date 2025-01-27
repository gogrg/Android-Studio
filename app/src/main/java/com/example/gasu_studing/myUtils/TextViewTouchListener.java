package com.example.gasu_studing.myUtils;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

class TextViewTouchListener implements View.OnTouchListener {
    private ThreadForTextView threadThisTextView;

    public TextViewTouchListener(ThreadForTextView thread) {
        threadThisTextView = thread;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("TAG", "TextView " + v.getId() + " pressed");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (threadThisTextView.checkStart()) {
                    threadThisTextView.sleepMe();
                }

                Log.d("TAG", "TextView " + v.getId() + " Event: " + event.getAction());
                return true;

            case MotionEvent.ACTION_UP:

                if (threadThisTextView.checkStart()) {
                    threadThisTextView.unsleepMe();
                }

                Log.d("TAG", "TextView " + v.getId() + " Event: " + event.getAction());
                return true;


            default:
                return false;
        }
    }
}
