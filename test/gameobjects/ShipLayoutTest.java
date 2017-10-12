/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aaron
 */
public class ShipLayoutTest {
    
    public ShipLayoutTest() {
    }

    /**
     * Test of class ShipLayout.
     */
    @Test
    public void testPlaceShip() {
        ShipLayout instance = new ShipLayout();
        
        assertEquals(instance.toJSON(), "{}");
        
        // "C": [0, 0, "h"]
        instance.placeShip("C", 0, 0, "H");
        assertEquals(instance.toJSON(), "{\"C\": [0, 0, \"h\"]}");
        
        instance.placeShip("p", 0, 1, "v");
        assertEquals(instance.toJSON(), "{\"C\": [0, 0, \"h\"], \"P\": [0, 1, \"v\"]}");
        
        instance.placeShip("D", 0, 3, "v");
        assertEquals(instance.toJSON(), "{\"C\": [0, 0, \"h\"], \"D\": [0, 3, \"v\"], \"P\": [0, 1, \"v\"]}");
        
        instance.placeShip("D", 7, 3, "h");
        assertEquals(instance.toJSON(), "{\"C\": [0, 0, \"h\"], \"D\": [7, 3, \"h\"], \"P\": [0, 1, \"v\"]}");
    }
    
}
