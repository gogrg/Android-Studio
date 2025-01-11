package com.example.gasu_studing.myUtils;


public class ThreadControl extends Thread{
    private BooleanMultiThreadControl isPress;
    private int timeSleep;
    private ThreadForTextView thread;

    public ThreadControl(ThreadForTextView thread, int timeSleep, BooleanMultiThreadControl isPress){
        this.thread = thread;
        this.timeSleep = timeSleep;
        this.isPress = isPress;
    }

    public void start(){
        while (isPress.getValue()){
            //try{
                thread.sleepMe(timeSleep);
                //Thread.currentThread().sleep(timeSleep);
//            }
//            catch(InterruptedException e){
//                Thread.currentThread().interrupt();
//
//                System.out.println(e);
//                break;
//            }
        }

    }

}
