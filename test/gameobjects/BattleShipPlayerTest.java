/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import util.Constants;

/**
 *
 * @author aaron
 */
public class BattleShipPlayerTest {
    private BattleShipPlayer player;
    
    public BattleShipPlayerTest() {
    }
    
    @Before
    public void setupClass(){
        player = getPlayerInstance();
    }
    
    private Class<? extends BattleShipPlayer> getPlayerClass(){
        String packageName = BattleShipPlayer.class.getPackage().getName();
        
        File packageFile = new File("src/" + packageName);
        File[] packageContents = packageFile.listFiles();
        
        //this iterates over all files within the same package as BattleShipPlayer interface
        for(File file : packageContents){
            String filename = file.getName();
            if(filename.endsWith(".java")){
                //we have a .java file, so we will try to get that class
                String classname = filename.substring(0, filename.indexOf("."));
                try{
                    Class clazz = Class.forName(packageName + "." + classname);
                    //see if this class implements BattleShipPlayer
                    for(Class implementedInterface : clazz.getInterfaces()){
                        if(implementedInterface == BattleShipPlayer.class){
                            return clazz;
                        }
                    }
                }
                catch(ClassNotFoundException e){
                    //ignore
                }
            }
            
        }
        return null;
    }
    
    private BattleShipPlayer getPlayerInstance(){
        Class<? extends BattleShipPlayer> clazz = getPlayerClass();
        BattleShipPlayer myPlayer;
        
        if(clazz == null){
            fail("Could not locate a class that implements " + BattleShipPlayer.class.getSimpleName() + " in package " + BattleShipPlayer.class.getPackage().getName());
            return null;
        }
        
        try{
            myPlayer = clazz.newInstance();
        }
        catch(IllegalAccessException | InstantiationException e){
            fail("Could not create an instance of class " + clazz.getSimpleName());
            return null;
        }
        
        return myPlayer;
    }
    
    @Test(timeout = 1000L)
    public void testGetShipLayout(){
        //get the JSON string
        String enemyLayout = player.getShipLayout().toString();
        enemyLayout = enemyLayout.substring(1, enemyLayout.length()-1);
        if(enemyLayout.length() == 0){
            fail("ShipLayout is not placing any ships.");
        }
        
        //keep track of which cells are already occupied
        String[][] board = new String[10][10];
        
        //for every placed ship
        String[] ships = enemyLayout.split("], ");
        for(String ship : ships){
            String id = ship.substring(1, 2);
            //index of x start and end
            int ioxs = ship.indexOf("[") + 1;
            int ioxe = ship.indexOf(",");
            
            //index of y start and end
            int ioys = ioxe + 2;
            int ioye = ship.lastIndexOf(",");
            
            //index of orientation
            int ioo  = ship.lastIndexOf("\"") - 1;
            
            int x = Integer.parseInt(ship.substring(ioxs,ioxe));
            int y = Integer.parseInt(ship.substring(ioys,ioye));
            String o = ship.substring(ioo, ioo+1);

            int length=0;
            switch(id){
                case "C": length = 5; break;
                case "B": length = 4; break;
                case "S": case "D": length = 3; break;
                case "P": length = 2; break;
                default:  fail("Invalid ship id placed: " + id); 
            }

            int width=1, height=1;
            switch(o){
                case "h": width  *= length; break;
                case "v": height *= length; break;
                default:  fail("Invalid ship orientation " + o);
            }

            if(width > 1 && height > 1){
                fail("Something really bad happened");
            }

            for(int bx=x;bx<x+width;bx++){
                for(int by=y;by<y+height;by++){
                    try{
                        if(board[bx][by] != null){
                            fail("Placement of ship " + id + " overlaps with ship " + board[bx][by]);
                        }
                        board[bx][by] = id;
                    }
                    catch(IndexOutOfBoundsException e){
                        fail("Ship " + id + " was placed out of bounds at " + bx + ", " + by);
                    }
                }
            }
        }
    }
    
    @Test(timeout = 5000L)
    public void testGetShotLocation(){
        //placements of test's ships
        int[][][] myShipCoords = new int[][][]{
            {{0,0},{1,0},{2,0},{3,0},{4,0}},
            {{0,1},{1,1},{2,1},{3,1}},
            {{0,2},{1,2},{2,2}},
            {{0,3},{1,3},{2,3}},
            {{0,4},{1,4}}
        };
        
        String[] myShipLabels = new String[]{Constants.CARRIER, Constants.BATTLESHIP, Constants.SUBMARINE, Constants.DESTROYER, Constants.PATROL};
        
        int[] myShipHitCount = new int[5];
        int myShipSunkCount = 0;
        boolean[][] enemyShots = new boolean[10][10];
        
        for(int shotNum=0;shotNum<100;shotNum++){
            int[] enemyShot = player.getShotLocation();
            if(enemyShot.length < 2){
                fail("Shot #" + (shotNum+1) + " does not have both an X and Y component. Length found is: " + enemyShot.length);
            }
            
            try{
                if(enemyShots[enemyShot[0]][enemyShot[1]]){
                    fail("Shot #" + (shotNum+1) + " has shot at an already shot location of (" + enemyShot[0] + ", " + enemyShot[1] + ")");
                }
                enemyShots[enemyShot[0]][enemyShot[1]] = true;
                int hitShip = -1;
            search:
                for(int myShip=0; myShip < myShipCoords.length; myShip++){
                    for(int[] coords : myShipCoords[myShip]){
                        if(coords[0] == enemyShot[0] && coords[1] == enemyShot[1]){
                            hitShip = myShip;
                            break search;
                        }
                    }
                }
                if(hitShip != -1){
                    if(++myShipHitCount[hitShip] == myShipCoords[hitShip].length){
                        player.shotNotification(true, enemyShot[0], enemyShot[1], Constants.HIT, myShipLabels[hitShip]);
                        if(++myShipSunkCount == 5){
                            break;
                        }
                    }
                    else{
                        player.shotNotification(true, enemyShot[0], enemyShot[1], Constants.HIT, null);
                    }
                }
                else{
                    player.shotNotification(true, enemyShot[0], enemyShot[1], Constants.MISS, null);
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                fail("Shot #" + (shotNum+1) + " fell out of bounds at (" + enemyShot[0] + ", " + enemyShot[1] + ")");
            }
        }
    }
}
