package clientPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	protected String pseudo;
	protected String mdp;
	
	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	
	
	
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public Client(String pseudo, String mdp)
	{
		this.pseudo=pseudo;
		this.mdp=mdp;
	}
	
	public String toString()
	{
		return "Pseudo: "+ pseudo;
	}
	
	public static void main(String[] args) {

	    Socket socket;
	    BufferedReader in;
	    PrintWriter out;
	    try {
	       //demande d'ouverture d'une connexion sur le serveur local
	    socket = new Socket(InetAddress.getLocalHost(),60042);  

	      //attente d'une requete
	      in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	    while(in!=null) {
	      in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
	      boolean read=true;
	      String message_distant="";
	      boolean nr=false;
	      while(read)
	      {
	    	  String tmp=in.readLine();
	    	  if(tmp==null || tmp.equals("end"))
	    	  {
	    		  read=false;
	    	  }
	    	  else
	    		  message_distant += tmp+"\n";
	      }  

	    //envoi d'une reponse
	     if(message_distant.length()!=0 && !message_distant.toLowerCase().contains("aurevoir"))
	      {
		      System.out.println("message :"+ message_distant);  
		      
	    out = new PrintWriter(socket.getOutputStream());
	    Scanner sc = new Scanner(System.in);
	    String tmp = sc.nextLine();
	    out.println(tmp);
	    
	    out.flush();
	      }
	      else {
	    	  if(message_distant.length()!=0)
	    		  System.out.println(message_distant+"\n Vous êtes déconnecté !");
	    	  in=null;
	      }
	    }
	        //fermeture de la connexion
	        socket.close();

	        }catch (UnknownHostException e) {      
	         e.printStackTrace();
	        }catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
}
