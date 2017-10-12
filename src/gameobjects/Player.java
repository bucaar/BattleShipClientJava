package gameobjects;

/**
 *
 * @author aaron
 */
public class Player {
    
    private String name = "Player";
    private Board board;
    
    int shotX, shotY;
    
    public Player(){
        board = new Board();
    }
    
    public String getName(){
        return name;
    }
    
    public int[] getShotLocation(){
        int[] coords = new int[]{shotX, shotY};
        shotX++;
        if(shotX > 9){
            shotY++;
            shotX = 0;
        }
        return coords;
    }
    
    public ShipLayout getShipLayout(){
        board.placeShip("C", 0, 0, "h");
        board.placeShip("B", 0, 2, "h");
        board.placeShip("S", 0, 4, "h");
        board.placeShip("D", 0, 6, "h");
        board.placeShip("P", 0, 8, "h");
        
        return board.getShipLayout();
    }
    
    public void shotHit(int x, int y){
        
    }
    
    public void shotMiss(int x, int y){
        
    }
    
    public void shotSunk(int x, int y, String ship){
        
    }
    
    public void won(){
        
    }
    
    public void lost(){
        
    }
    
    public void error(String message){
        
    }
    
}
