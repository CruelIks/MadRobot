package com.iks;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Robot's tests
 *
 * @author Kirill Ivanov
 */
public class RobotTest {

    private Robot testedInstance = new Robot(3, 7);

    @Before
    public void setUp(){
        ControlPanel panel = mock(ControlPanel.class);
        testedInstance.setControlPanel(panel);
    }

    @Test
    public void testAddLeg(){
        testedInstance.addLeg();
        assertTrue(4 == testedInstance.getNumberOfLegs());
        assertTrue(4 == testedInstance.getLegs().size());
    }

    @Test
    public void testRemoveLeg(){
        testedInstance.removeLeg();
        assertTrue(2 == testedInstance.getNumberOfLegs());
        assertTrue(2 == testedInstance.getLegs().size());
    }

    @Test
    public void testRemoveLegDoesntRemoveAllLegs(){
        testedInstance.removeLeg();
        testedInstance.removeLeg();
        testedInstance.removeLeg();
        testedInstance.removeLeg();

        assertTrue(1 == testedInstance.getNumberOfLegs());
        assertTrue(1 == testedInstance.getLegs().size());
    }

    @Test
    public void testGetRandomStepLengthReturnValueFromValidInterval(){
        double randomValue = testedInstance.getRandomStepLength();

        assertTrue("Value > 0.5", randomValue > 0.5);
        assertTrue("Value < 1.5", randomValue < 1.5);

    }

    @Test
    public void testGetRandomStepLengthReturnDifferentValues(){
        double firstValue = testedInstance.getRandomStepLength();
        double secondValue = testedInstance.getRandomStepLength();
        assertTrue(Double.compare(firstValue, secondValue) != 0);
    }

    @Test
    public void testRunFunction() throws InterruptedException {
        testedInstance.run();

        /*while (testedInstance.isAlive()){

        }*/

        testedInstance.join();

        double distance = testedInstance.getDistanceCounter();

        assertTrue(distance >= 7 && distance < 8.5);

    }


}
