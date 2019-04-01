package serveurPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import baseDeDonnees.BDD;
import clientPack.Joueur;
import interfaceApp.MainFrame;

public class Service implements Runnable{

    private ServerSocket socketserveur  ;
    private Socket s ;

    private static int nbrclient = 0;
    private static final int nbMaxClient =3;
    private static List<Joueur> listJoueurConnecte=new ArrayList<Joueur>();
    private static List<Thread> listThreadJoueurConnecte=new ArrayList<Thread>();

    private static BDD bdd = new BDD();

    public Service(ServerSocket socket){
            socketserveur = socket;
        }
    
    
 
    public static int getNbrclient() {
		return nbrclient;
	}

    


	public static List<Joueur> getListJoueurConnecte() {
		return listJoueurConnecte;
	}



	public static void setListJoueurConnecte(List<Joueur> listJoueurConnecte) {
		Service.listJoueurConnecte = listJoueurConnecte;
	}



	public static void setNbrclient(int nbrclient) {
		Service.nbrclient = nbrclient;
	}



	public static int getNbmaxclient() {
		return nbMaxClient;
	}



	public static BDD getBdd() {
		return bdd;
	}



	public static void setBdd(BDD bdd) {
		Service.bdd = bdd;
	}

	


	public static List<Thread> getListThreadJoueurConnecte() {
		return listThreadJoueurConnecte;
	}



	public static void setListThreadJoueurConnecte(List<Thread> listThreadJoueurConnecte) {
		Service.listThreadJoueurConnecte = listThreadJoueurConnecte;
	}



	public void run(){
    	
    	try {

        PrintWriter out;
        BufferedReader in;

        bdd.connexion();
            System.out.println("Serveur en attente");

        while(true){

           // écoute d'un service entrant -association socket client et socket serveur.     
           s = socketserveur.accept();
           Thread t = new Thread(new MainFrame(s));
           listThreadJoueurConnecte.add(t);
           nbrclient++;
           t.start();
           
       }
            //cloture de l'ecoute
            //socketserveur.close();   
    }   
    catch (IOException e) {e.printStackTrace();}            
        catch (Exception e) {e.printStackTrace();}
    }

}