package de.phtp.worldguessr.control;

import static org.junit.Assert.*;

import org.junit.Test;

public class DistanceCalculatorTest {
    @Test
    public void testCalculateDistance() {
        double test = GameControl.calculateDistance(52.486564, 13.424146, 53.073498, 8.806185);
        assertEquals(317000, test, 500); //Berlin - Bremen
    }

    @Test
    public void testCalculateDistance2() {
        double test = GameControl.calculateDistance(52.486564, 13.424146, 37.798464, -122.447374);
        assertEquals(9107740, test, 500); //Berlin - San Francisco
    }

}