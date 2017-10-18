package gameobjects;

import protocol.Protocol;

/**
 *
 * @author aaron
 */
public class Main {
    public static void main(String[] args) {
        int port = 4949;
        if(args.length > 0){
            try{
                port = Integer.parseInt(args[0]);
            }
            catch(NumberFormatException e){
                System.err.printf("Could not parse port %s, default to %d", args[0], port);
            }
        }
        
        JavaBoss player = new JavaBoss();
        
        Protocol p = new Protocol(player, "localhost", port);
        
        p.start();
    }
}
