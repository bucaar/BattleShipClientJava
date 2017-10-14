package gameobjects;

import util.Constants;

/**
 *
 * @author aaron
 */
public class JavaBoss implements BattleShipPlayer{
    int shotX, shotY;
    
    public JavaBoss(){
    }
    
    @Override
    public int[] getShotLocation(){
        int[] coords = new int[]{shotX, shotY};
        shotX++;
        if(shotX > 9){
            shotY++;
            shotX = 0;
        }
        return coords;
    }
    
    @Override
    public ShipLayout getShipLayout(){
        ShipLayout layout = new ShipLayout();
        
        layout.placeShip(Constants.CARRIER, 0, 0, Constants.HORIZONTAL);
        layout.placeShip(Constants.BATTLESHIP, 0, 2, Constants.HORIZONTAL);
        layout.placeShip(Constants.SUBMARINE, 0, 4, Constants.HORIZONTAL);
        layout.placeShip(Constants.DESTROYER, 0, 6, Constants.HORIZONTAL);
        layout.placeShip(Constants.PATROL, 0, 8, Constants.HORIZONTAL);
        
        return layout;
    }
    
    @Override
    public void shotNotification(boolean yourShot, int x, int y, String result, String shipSunk){
        
    }
    
    @Override
    public void gameOver(boolean won){
        
    }
    
}
