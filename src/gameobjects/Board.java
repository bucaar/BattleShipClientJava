package gameobjects;

/**
 *
 * @author aaron
 */
public class Board {
    private String[][] tiles;
    
    public Board(){
        this.tiles = new String[10][10];
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
    
}
