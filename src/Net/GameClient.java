package Net;

import Screen.game;

import java.io.IOException;
import java.net.*;
import java.util.Date;

public class GameClient extends Thread{
    private InetAddress ipAdress;
    private DatagramSocket socket;
    private Screen.game game;
    public GameClient(Screen.game game,String ipAdress){
this.game = game;
        try {
            this.socket = new DatagramSocket();
        this.ipAdress = InetAddress.getByName(ipAdress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.getStackTrace();
        }

    }
    @Override
    public void run(){
        byte[] data =new byte[1024];
        DatagramPacket packet  =new DatagramPacket(data,data.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Server > "+new String( packet.getData()));

    }
}
