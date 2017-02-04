package com.iks;


/**
 * Main class
 *
 * @author Kirill Ivanov
 */
public class Application {

    public static void main(String[] args) throws InterruptedException {

        Robot robot;
        try
        {
            int countLegs = Integer.parseInt(args[0]);
            int totalDistance = Integer.parseInt(args[1]);

            robot = new Robot(countLegs, totalDistance);
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("Incorrect parameter's! Program start's with default!\n" +
                    "Robot has 3 legs and have to run 30 metres!");
            robot = new Robot(3, 30);
        }


        ControlPanel controlPanel = new ControlPanel(robot);
        robot.setControlPanel(controlPanel);

        robot.start();



    }

}
