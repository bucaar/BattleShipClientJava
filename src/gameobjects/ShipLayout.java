package gameobjects;

/**
 *
 * @author aaron
 */
public class ShipLayout {
    
    private String[] layout = new String[5];
    
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
            
    public String toJSON(){
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
