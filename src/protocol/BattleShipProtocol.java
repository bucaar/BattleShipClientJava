package protocol;

/**
 *
 * @author aaron
 */
public interface BattleShipProtocol {
    
    boolean connect();
    
    void start();
    
    void disconnect();
}
