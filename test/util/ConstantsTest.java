/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import ProvidedClasses.Constants;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aaron
 */
public class ConstantsTest {
    
    public ConstantsTest() {
    }

    /**
     * Test of resultToConstant method, of class Constants.
     */
    @Test
    public void testResultToConstant() {
        String result = Constants.resultToConstant("HIT");
        assertEquals(Constants.HIT, result);
        assertTrue(Constants.HIT == result);
        
        result = Constants.resultToConstant("MISS");
        assertEquals(Constants.MISS, result);
        assertTrue(Constants.MISS == result);
    }

    /**
     * Test of shipToConstant method, of class Constants.
     */
    @Test
    public void testShipToConstant() {
        String result = Constants.shipToConstant("C");
        assertEquals(Constants.CARRIER, result);
        assertTrue(Constants.CARRIER == result);
        
        result = Constants.shipToConstant("S");
        assertEquals(Constants.SUBMARINE, result);
        assertTrue(Constants.SUBMARINE == result);
    }

    /**
     * Test of orientationToConstant method, of class Constants.
     */
    @Test
    public void testOrientationToConstant() {
        String result = Constants.orientationToConstant("h");
        assertEquals(Constants.HORIZONTAL, result);
        assertTrue(Constants.HORIZONTAL == result);
        
        result = Constants.orientationToConstant("v");
        assertEquals(Constants.VERTICAL, result);
        assertTrue(Constants.VERTICAL == result);
    }
    
}
