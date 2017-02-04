package com.iks;

import java.util.concurrent.TimeUnit;

/**
 * Created by Prog on 03.02.2017.
 */
public class Application {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("Create robot...");

        Robot robot = new Robot(3, 30);
        ControlPanel controlPanel = new ControlPanel(robot);
        robot.setControlPanel(controlPanel);

        /*TimeUnit.SECONDS.sleep(1);*/
        System.out.println("Start moving!");
        robot.start();

       /* TimeUnit.SECONDS.sleep(20);

        System.out.println("STOP!");
        robot.stopMoving();*/

    }

}
