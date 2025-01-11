package com.example.gasu_studing.myUtils;


public class ThreadControl {
    public static void sleep(Thread thread, int timeSleep, BooleanMultiThreadControl isPress){
        while (isPress.getValue()){
            try{
                thread.sleep(timeSleep);
                Thread.currentThread().sleep(timeSleep);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();

                System.out.println(e);
                break;
            }
        }

    }

}
