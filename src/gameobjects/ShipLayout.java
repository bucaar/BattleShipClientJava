package gameobjects;

/**
 *
 * @author aaron
 */
public class ShipLayout {
    
    private String[] layout = new String[5];
    
    /**
     * Method used to place a ship within this ShipLayout 
     * 
     * @param ship The ship id to place. Must be one of {C, B, S, D, P}
     * @param x The x coordinate of the most top-left tile of the ship
     * @param y The y coordinate of the most top-left tile of the ship
     * @param orientation The orientation to place the ship in. Must be one of {h, v}
     */
    public void placeShip(String ship, int x, int y, String orientation){
        int index;
        ship = ship.toUpperCase();
        orientation = orientation.toLowerCase();
        switch(ship){
            case "C":
                index = 0;
                break;
            case "B":
                index = 1;
                break;
            case "S":
                index = 2;
                break;
            case "D":
                index = 3;
                break;
            case "P":
                index = 4;
                break;
            default:
                return;
        }
        switch(orientation){
            case "h":
            case "v":
                break;
            default:
                return;
        }
        layout[index] = String.format("\"%s\": [%d, %d, \"%s\"]", ship, x, y, orientation);
    }
            
    /**
     * Returns a string representation of this layout in a JSON structure 
     * 
     * @return the JSON string
     */
    @Override
    public String toString(){
        String output = "{";
        boolean first = true;
        for(int i=0;i<layout.length;i++){
            if(layout[i] != null){
                if(!first){
                    output += ", ";
                }
                output += layout[i];
                first = false;
            }
        }
        output += "}";
        return output;
    }        
}
