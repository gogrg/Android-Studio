//2 случая: один раз баг произошёл, и на месте, где кнопка при нажатии происходило нажатие на родительский контейнер
//второй случай - при нажатии на кнопку не происходит ни запуск потока, ни обработка нажатия на родительский контейнер


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

        Button button = (Button) findViewById(R.id.button);


        //номер текущего поля для создания потока;

        AtomicInteger nowTextView = new AtomicInteger(1);

        ExecutorService threadPool = Executors.newFixedThreadPool(4);


        Thread buttonControl = new Thread(){

            @Override
            public void run(){

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public synchronized void onClick(View v) {
                        eventQueue.addEvent(() -> {

                            Log.d("TAG", "onClisk is started");


                            int currentTextView = nowTextView.get();

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
                                //пришлось убрать проверку так как теперь не получить доступ к конкретному потоку
                                //if (thread[currentTextView[0] - 1].checkStart()) {
                                nowTextView.incrementAndGet();
                                Log.d("TAG", "End metod. Value nowTextView - " + nowTextView);
                                //}
                            }
                        }, "add new thread");
                    }
                });
            }
        };

        buttonControl.run();

        Thread queueThread = new Thread() {

            @Override
            public void run(){
                eventQueue.processEvents();
            }
        };

        queueThread.start();

        ConstraintLayout parentLayout = findViewById(R.id.mainLayout);

        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public synchronized void onClick(View v) {

                Log.d("ParentView", "Parent layout is click");
//                int[] location = new int[2];
//                button.getLocationOnScreen(location);
//                Log.d("Координата кнопки по X", "Location - " + location[0]);
//                Log.d("Координата кнопки по Y", "Location - " + location[1]);
            }
        });


    }

}