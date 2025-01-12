package com.example.gasu_studing;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


//для кнопки
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

//текстовые поля
import android.widget.TextView;


//мои классы
import com.example.gasu_studing.myUtils.*;


public class MainActivity extends AppCompatActivity {

    private int timeSleep = 10;

    ThreadForTextView thread[] = new ThreadForTextView[4];


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);

        Button button = findViewById(R.id.button);


        //номер текущего поля для создания потока;
        byte nowTextView[] = {1};


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nowTextView[0] < 5) {
                    switch (nowTextView[0]) {
                        case 1:
                            thread[0] = new ThreadForTextView(textView1, timeSleep);
                            thread[0].start();
                            break;
                        case 2:
                            thread[1] = new ThreadForTextView(textView2, timeSleep);
                            thread[1].start();
                            break;
                        case 3:
                            thread[2] = new ThreadForTextView(textView3, timeSleep);
                            thread[2].start();
                            break;
                        case 4:
                            thread[3] = new ThreadForTextView(textView4, timeSleep);
                            thread[3].start();
                            break;
                    }

                    nowTextView[0]++;
                }
            }
        });


        textView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //thread[0].sleepMe();
                        controlIncrementThread(0, false);
                        Log.d("TAG", "Event: " + event.getAction());
                        return true;

                    case MotionEvent.ACTION_UP:
                        //thread[0].unsleepMe();
                        controlIncrementThread(0, true);
                        Log.d("TAG", "Event: " + event.getAction());
                        return true;


                    default:
                        return false;
                }
            }
        });

        textView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //thread[0].sleepMe();
                        controlIncrementThread(1, false);
                        Log.d("TAG", "Event: " + event.getAction());
                        return true;

                    case MotionEvent.ACTION_UP:
                        //thread[0].unsleepMe();
                        controlIncrementThread(1, true);
                        Log.d("TAG", "Event: " + event.getAction());
                        return true;


                    default:
                        return false;
                }
            }
        });

        textView3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //thread[0].sleepMe();
                        controlIncrementThread(2, false);
                        Log.d("TAG", "Event: " + event.getAction());
                        return true;

                    case MotionEvent.ACTION_UP:
                        //thread[0].unsleepMe();
                        controlIncrementThread(2, true);
                        Log.d("TAG", "Event: " + event.getAction());
                        return true;


                    default:
                        return false;
                }
            }
        });

        textView4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //thread[0].sleepMe();
                        controlIncrementThread(3, false);
                        Log.d("TAG", "Event: " + event.getAction());
                        return true;

                    case MotionEvent.ACTION_UP:
                        //thread[0].unsleepMe();
                        controlIncrementThread(3, true);
                        Log.d("TAG", "Event: " + event.getAction());
                        return true;


                    default:
                        return false;
                }
            }
        });

    }

    //status = 1 - работать, status = 0 - остановить
    private void controlIncrementThread(int numberThread, boolean status) {
        if (numberThread < 0 || numberThread > 4) {
            return;
        }
        if (status) {
            thread[numberThread].unsleepMe();
        } else {
            thread[numberThread].sleepMe();
        }
    }
}