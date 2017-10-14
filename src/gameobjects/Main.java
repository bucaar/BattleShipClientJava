package gameobjects;

import protocol.Protocol;

/**
 *
 * @author aaron
 */
public class Main {
    public static void main(String[] args) {
        JavaBoss player = new JavaBoss();
        
        Protocol p = new Protocol(player, "localhost", 4949);
        
        p.start();
    }
}
