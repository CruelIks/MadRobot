package com.iks;


import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Prog on 03.02.2017.
 */
public class Robot extends Thread {

    private static final double RANGE_MIN = 0.5;
    private static final double RANGE_MAX = 1.5;
    private final LinkedBlockingQueue<Leg> legs = new LinkedBlockingQueue<>();
    private boolean isStopped = false;
    private int numberOfLegs;
    private AtomicInteger legId = new AtomicInteger();
    private AtomicInteger stepsCounter = new AtomicInteger(0);
    private volatile double distanceCounter = 0;
    private volatile double goalDistance = 0;
    private ControlPanel controlPanel;

    public Robot(int numberOfLegs, double goalDistance) {
        super();
        /*this.numberOfLegs = numberOfLegs;*/
        this.goalDistance = goalDistance;

        for (int i = 0; i < numberOfLegs; i++) {
            addLeg();
        }
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void addLeg() {

        legs.add(new Leg(legId.incrementAndGet(), this));
        numberOfLegs++;

    }

    @Override
    public void run() {

        if (goalDistance == 0 | numberOfLegs == 0) {
            stopMoving();
        }

        isStopped = false;

        while (!isStopped & (distanceCounter < goalDistance)) {
            synchronized (this) {
                try {
                    for (Leg leg : legs) {

                        synchronized (leg) {
                            leg.notify();
                            TimeUnit.SECONDS.sleep(1);
                        }

                        this.wait();
                        stepsCounter.incrementAndGet();
                        distanceCounter += getRandomStepLength();

                        controlPanel.addLineToMainScreen(String.format("Robot moved with %s Count legs: %d Count steps: %d" +
                                " Distance %f from %f", leg, numberOfLegs, stepsCounter.get(), distanceCounter, goalDistance));

                        if (distanceCounter >= goalDistance) {
                            break;
                        }

                    }
                } catch (InterruptedException e) {
                }
            }
        }

        printStatus();
        controlPanel.dispose();
    }

    public String getStatus() {
        return String.format("Count steps: %d\nDistance counter: %f from %f\n", stepsCounter.get(), distanceCounter, goalDistance);
    }

    public void printStatus() {
        System.out.println(getStatus());
    }

    public void removeLeg() {

        if (legs.size() <= 1) {
            System.out.println("You can't remove last leg!");
            controlPanel.addLineToMainScreen("You can't remove last leg!");
            return;
        }

        try {
            legs.peek().setInterrupted();
            legs.remove();
            numberOfLegs--;
        } catch (NoSuchElementException e) {
            System.out.println("Nothing to delete!");
        }

    }

    private double getRandomStepLength() {
        Random r = new Random();
        double randomValue = RANGE_MIN + (RANGE_MAX - RANGE_MIN) * r.nextDouble();
        return randomValue;
    }


    public void stopMoving() {

        isStopped = true;

        for (Leg leg : legs) {
            leg.setInterrupted();
        }
        interrupt();
    }


}
