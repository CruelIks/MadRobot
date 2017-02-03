package com.iks;

import java.util.concurrent.TimeUnit;

/**
 * Created by Prog on 03.02.2017.
 */
public class Leg implements Runnable {

    private Thread thread;
    private final int number;
    private final Robot robot;
    private boolean isInterrupted = false;

    public Leg(int number, Robot robot) {
        this.number = number;
        this.robot = robot;
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {

        while (!isInterrupted){
             synchronized (this){
                try {
                    this.wait();

                    System.out.printf("Robot moved with leg %d\n", number);
                    TimeUnit.MILLISECONDS.sleep(200);

                    synchronized (robot){
                        robot.notify();
                    }

                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
            }
        }
    }

    public void setInterrupted() {
        isInterrupted = true;
        thread.interrupt();
    }


    @Override
    public String toString() {
        return "Leg{" +
                "number=" + number +
                '}';
    }
}
