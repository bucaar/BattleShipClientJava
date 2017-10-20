/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aaron
 */
public class PlayerTest {
    
    public PlayerTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
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
    
    @Test
    public void testPlayerClass(){
        BattleShipPlayer player = getPlayerInstance();
    }
}
