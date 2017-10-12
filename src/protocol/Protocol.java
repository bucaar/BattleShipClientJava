package protocol;

import gameobjects.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import gameobjects.ShipLayout;

/**
 *
 * @author aaron
 */
public class Protocol implements BattleShipProtocol {

    private Player player;
    
    private int[] lastShot;
    
    private final String host;
    private final int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    
    private boolean running;
    
    public Protocol(String host, int port){
        this.host = host;
        this.port = port;
        
        player = new Player();
        
        this.running = true;
    }
    
    @Override
    public boolean connect(){
        try{
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            return true;
        }
        catch(IOException e){
            disconnect();
            return false;
        }
    }
    
    @Override
    public void start(){
        while(running){
            try{
                String message;
                while((message = in.readLine())!= null){
                    processMessage(message);
                }
            }
            catch(IOException e){
                e.printStackTrace();
                disconnect();
            }
        }
    }

    @Override
    public void disconnect(){
        running = false;
        try{socket.close();}
        catch(IOException e){}
    }
            
    private void processMessage(String message) {
        switch(message.toUpperCase().substring(0, 3)){
            case "NAM":
                sendName(player.getName());
                break;
            case "SHI":
                sendShipLayout(player.getShipLayout());
                break;
            case "SHO":
                lastShot = player.getShotLocation();
                sendShotLocation(lastShot);
                break;
            case "HIT":
                player.shotHit(lastShot[0], lastShot[1]);
                break;
            case "MIS":
                player.shotMiss(lastShot[0], lastShot[1]);
                break;
            case "SUN":
                String sunk = "" + message.toUpperCase().charAt(5);
                player.shotSunk(lastShot[0], lastShot[1], sunk);
                break;
            case "WIN":
                player.won();
                break;
            case "LOS":
                player.lost();
                break;
            case "ERR":
                String error = message.substring(6);
                player.error(error);
                break;
        }
    }

    private void sendName(String name) {
        if(!running) return;
        out.println(name);
    }

    private void sendShotLocation(int[] coords) {
        if(!running) return;
        out.printf("[%d, %d]", coords[0], coords[1]);
    }

    private void sendShipLayout(ShipLayout layout) {
        if(!running) return;
        out.printf("%s", layout.toJSON());
    }
}
