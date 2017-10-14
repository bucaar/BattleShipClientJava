package util;

/**
 *
 * @author aaron
 */
public class Constants {
    public static final String HIT          = "HIT";
    public static final String MISS         = "MISS";
     
    public static final String CARRIER      = "C";
    public static final String BATTLESHIP   = "B";
    public static final String SUBMARINE    = "S";
    public static final String DESTROYER    = "D";
    public static final String PATROL       = "P";
    
    public static final String HORIZONTAL   = "h";
    public static final String VERTICAL     = "v";
    
    /**
     * Helper method to ensure the reference to the constant is used over a string with equal characters.
     * @param result The result to convert
     * @return The reference to the constant value
     */
    public static String resultToConstant(String result){
        if(result.equalsIgnoreCase(HIT)) return HIT;
        if(result.equalsIgnoreCase(MISS)) return MISS;
        return null;
    }
    
    /**
     * Helper method to ensure the reference to the constant is used over a string with equal characters.
     * @param ship The ship to convert
     * @return The reference to the constant value
     */
    public static String shipToConstant(String ship){
        if(ship.equalsIgnoreCase(CARRIER)) return CARRIER;
        if(ship.equalsIgnoreCase(BATTLESHIP)) return BATTLESHIP;
        if(ship.equalsIgnoreCase(SUBMARINE)) return SUBMARINE;
        if(ship.equalsIgnoreCase(DESTROYER)) return DESTROYER;
        if(ship.equalsIgnoreCase(PATROL)) return PATROL;
        return null;
    }
    
    /**
     * Helper method to ensure the reference to the constant is used over a string with equal characters.
     * @param orientation The orientation to convert
     * @return The reference to the constant value
     */
    public static String orientationToConstant(String orientation){
        if(orientation.equalsIgnoreCase(HORIZONTAL)) return HORIZONTAL;
        if(orientation.equalsIgnoreCase(VERTICAL)) return VERTICAL;
        return null;
    }
    
    /**
     * Prevent instances of the Constant class
     */
    private Constants(){}
}
