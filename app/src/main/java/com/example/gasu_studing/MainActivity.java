package com.example.gasu_studing;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


//для кнопки
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//текстовые поля
import android.widget.TextView;


//мои классы
import com.example.gasu_studing.myUtils.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity {
    EventQueue eventQueue = new EventQueue();
    ExecutorService threadPool = Executors.newFixedThreadPool(4);
    AtomicInteger nowTextView = new AtomicInteger(1);


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
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


        Thread buttonControl = new Thread() {

            @Override
            public void run() {

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public synchronized void onClick(View v) {
                        Log.d("TAG", "onClisk is started");

                        int currentTextView = nowTextView.get();
                        nowTextView.incrementAndGet();

                        Log.d("TAG", "Start metod. Value nowTextView - " + nowTextView);

                        if (currentTextView < 5) {
                            Log.d("TAG", "Block with switch case is started");
                            switch (currentTextView) {
                                case 1:
                                    FutureTask<Integer> taskTextView = new FutureTask<>(new ThreadForTextView(textView1, eventQueue));
                                    threadPool.submit(taskTextView);
                                    Log.d("Tag", "Case 1 success finish");
                                    break;
                                case 2:
                                    taskTextView = new FutureTask<>(new ThreadForTextView(textView2, eventQueue));
                                    threadPool.submit(taskTextView);
                                    Log.d("Tag", "Case 2 success finish");
                                    break;
                                case 3:
                                    taskTextView = new FutureTask<>(new ThreadForTextView(textView3, eventQueue));
                                    threadPool.submit(taskTextView);
                                    Log.d("Tag", "Case 3 success finish");
                                    break;
                                case 4:
                                    taskTextView = new FutureTask<>(new ThreadForTextView(textView4, eventQueue));
                                    threadPool.submit(taskTextView);
                                    Log.d("Tag", "Case 4 success finish");
                                    break;
                            }
                            Log.d("TAG", "End metod. Value nowTextView - " + nowTextView);
                        }
                    }
                });
            }
        };

        buttonControl.run();

        Thread queueThread = new Thread() {

            @Override
            public void run() {
                eventQueue.processEvents();
            }
        };

        queueThread.start();
    }

}