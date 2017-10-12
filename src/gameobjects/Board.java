package gameobjects;

/**
 *
 * @author aaron
 */
public class Board {
    private ShipLayout layout;
    private String[][] tiles;
    
    public Board(){
        this.tiles = new String[10][10];
        this.layout = new ShipLayout();
    }
    
    public void placeShip(String ship, int x, int y, String orientation){
        layout.placeShip(ship, x, y, orientation);
    }
    
    public void hit(int x, int y){
        if(x<0 || x>9 || y<0 || y>9){
            return;
        }
        tiles[x][y] = "H";
    }
    
    public void miss(int x, int y){
        if(x<0 || x>9 || y<0 || y>9){
            return;
        }
        tiles[x][y] = "M";
    }
    
    public void sunk(int x, int y, String ship){
        if(x<0 || x>9 || y<0 || y>9){
            return;
        }
        ship = "" + ship.toUpperCase().charAt(0);
        tiles[x][y] = ship;
    }
    
    public ShipLayout getShipLayout(){
        return layout;
    }
    
}
