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

/**
 *
 * @author aaron
 */
public class PlayerTest {
    private BattleShipPlayer player;
    
    public PlayerTest() {
    }
    
    @Before
    public void setupClass(){
        player = getPlayerInstance();
    }
    
    private Class<? extends BattleShipPlayer> getPlayerClass(){
        String packageName = BattleShipPlayer.class.getPackage().getName();
        
        File packageFile = new File("src/" + packageName);
        File[] packageContents = packageFile.listFiles();
        for(File file : packageContents){
            String filename = file.getName();
            if(filename.endsWith(".java")){
                String classname = filename.substring(0, filename.indexOf("."));
                try{
                    Class clazz = Class.forName(packageName + "." + classname);
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
        BattleShipPlayer player = null;
        
        if(clazz == null){
            fail("Could not locate a class that implements " + BattleShipPlayer.class.getSimpleName() + " in package " + BattleShipPlayer.class.getPackage().getName());
            return null;
        }
        
        try{
            player = clazz.newInstance();
        }
        catch(IllegalAccessException | InstantiationException e){
            fail("Could not create an instance of class " + clazz.getSimpleName());
            return null;
        }
        
        return player;
    }
    
    @Test(timeout = 1000L)
    public void testGetShipLayout(){
        for(int iteration = 0; iteration < 10; iteration++){
            String enemyLayout = player.getShipLayout().toString();
            enemyLayout = enemyLayout.substring(1, enemyLayout.length()-1);
            if(enemyLayout.length() == 0){
                fail("ShipLayout is not placing any ships.");
            }
            String[][] board = new String[10][10];

            String[] ships = enemyLayout.split("], ");
            for(String ship : ships){
                String id = ship.substring(1, 2);
                int ioxs = ship.indexOf("[") + 1;
                int ioxe = ship.indexOf(",");
                int ioys = ioxe + 2;
                int ioye = ship.lastIndexOf(",");
                int ioo  = ship.lastIndexOf("\"") - 1;
                int x = Integer.parseInt(ship.substring(ioxs,ioxe));
                int y = Integer.parseInt(ship.substring(ioys,ioye));
                String o = ship.substring(ioo, ioo+1);


                int length=0;
                switch(id){
                    case "C": length = 5; break;
                    case "B": length = 4; break;
                    case "S":
                    case "D": length = 3; break;
                    case "P": length = 2; break;
                    default:  fail("Invalid ship id placed: " + id); 
                }

                int width=1, height=1;
                switch(o){
                    case "h": width  *= length; break;
                    case "v": height *= length; break;
                    default:  fail("Invalid ship orientation " + o);
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
    }
    
    @Test(timeout = 5000L)
    public void testGetShotLocation(){
        int[][][] myShipCoords = new int[][][]{
            {{0,0},{1,0},{2,0},{3,0},{4,0}},
            {{0,1},{1,1},{2,1},{3,1}},
            {{0,2},{1,2},{2,2}},
            {{0,3},{1,3},{2,3}},
            {{0,4},{1,4}}
        };
        int[] myShipHitCount = new int[5];
        int myShotX = 0, myShotY = 0;
        int[][] enemyShots = new int[100][2];
    }
}
