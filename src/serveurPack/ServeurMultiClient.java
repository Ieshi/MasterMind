package serveurPack;

import java.io.IOException;
import java.net.ServerSocket;

public class ServeurMultiClient{
	private static int port=60042;

    public static void main(String[] args) 
    {
        ServerSocket socket;
        try 
        {
	        socket = new ServerSocket(port);
	        Thread t = new Thread(new Service(socket));
	        t.start();
	        System.out.println("J'attends des connexions mais pas trop!");
        } catch (IOException e) {e.printStackTrace();}
    }
}
