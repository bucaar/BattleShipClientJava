package gameobjects;

import java.io.IOException;
import protocol.Protocol;

/**
 *
 * @author aaron
 */
public class Main {
    public static void main(String[] args) {
        Protocol p = new Protocol("localhost", 4949);
        
        if(p.connect()){
            p.start();
        }
    }
}
