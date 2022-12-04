package de.phtp.worldguessr.control;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameControlTest {
    @Test
    public void testCalculateDistance() {
        double test = GameControl.calculateDistance(52.486564, 13.424146, 53.073498, 8.806185);
        assertEquals(317000, test, 500); //Berlin - Bremen

        double test2 = GameControl.calculateDistance(52.486564, 13.424146, 37.798464, -122.447374);
        assertEquals(9107740, test2, 500); //Berlin - San Francisco
    }

    @Test
    public void testRound() {
        double test = Math.PI;
        assertEquals(3.14, GameControl.round(test), 0.001);
    }
}