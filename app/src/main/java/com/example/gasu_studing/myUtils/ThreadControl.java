package com.example.gasu_studing.myUtils;
import java.util.concurrent.Callable;

public class ThreadControl extends Thread{
    private BooleanMultiThreadControl isPress;
    private ThreadForTextView thread;

    public ThreadControl(ThreadForTextView thread, BooleanMultiThreadControl isPress){
        this.thread = thread;
        this.isPress = isPress;
    }

    public void run(){
        while (isPress.getValue()){
//            try{
                thread.sleepMe();
//            }
//            catch(InterruptedException e){
//                Thread.currentThread().interrupt();
//                System.out.println(e);
//                break;
//            }
        }
        Thread.currentThread().interrupt();

    }

}
