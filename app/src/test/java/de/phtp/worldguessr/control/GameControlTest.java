package de.phtp.worldguessr.control;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import de.phtp.worldguessr.model.DAO;

public class GameControlTest {

    private GameControl gameControl;

    private DAO dao;

    @Before
    public void setUp(){
        gameControl = new GameControl();
        dao = Mockito.mock(DAO.class);
    }

    @Test
    public void testCalculateDistance() {
        double test = gameControl.calculateDistance(52.486564, 13.424146, 53.073498, 8.806185);
        assertEquals(317000, test, 500); //Berlin - Bremen

        double test2 = gameControl.calculateDistance(52.486564, 13.424146, 37.798464, -122.447374);
        assertEquals(9107740, test2, 500); //Berlin - San Francisco
    }

    @Test
    public void testRound1(){
        assertEquals(gameControl.round(4423.234234),4423.23,0);
    }

    @Test
    public void testRound2(){
        assertEquals(gameControl.round(75435.31643),75435.32,0);
    }

    @Test
    public void testBuildSnackbarString1(){
        assertEquals(gameControl.buildSnackbarString(4423.23),"distance: 4.42km");
    }

    @Test
    public void testBuildSnackbarString2(){
        assertEquals(gameControl.buildSnackbarString(442.63),"distance: 442.63m");
    }
}