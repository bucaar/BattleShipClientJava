package gameobjects;

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
        layout.placeShip("C", 0, 0, "h");
        layout.placeShip("B", 0, 2, "h");
        layout.placeShip("S", 0, 4, "h");
        layout.placeShip("D", 0, 6, "h");
        layout.placeShip("P", 0, 8, "h");
        
        return layout;
    }
    
    @Override
    public void shotNotification(boolean yourShot, int x, int y, String result, String shipSunk){
        
    }
    
    @Override
    public void gameOver(boolean won){
        System.out.println("I " + (won?"won":"lost"));
    }
    
}
