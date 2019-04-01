package interfaceApp;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clientPack.Admin;
import clientPack.Joueur;
import serveurPack.Service;

public class MainFrame extends JFrame implements Runnable{
	//Runnable part
	   private Socket sock;
	   
	   

	   private boolean playGame=false;
	   
	   private Joueur joueur;
	   
	   private Admin admin;
	   
	   private static int minFidelite=0;
	   
	 //Frame part
	private ConnexionPart connexion;
	private InscriptionPart inscription;
	private InfosPart infos;
	private GamePart theGame;
	private AdminPart adminPane;
	
	private CardLayout cardLayout=new CardLayout();
	private JPanel partie1;
	
	private Thread t;

	public MainFrame (Socket sock) 
	{
		this.sock=sock;
		
		this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println((Service.getNbrclient()-1) + " is closing window");
            }

            @Override
            public void windowClosed(WindowEvent e) {
            	try {
					Thread.sleep(1000);
	          System.out.println("Size listThread:"+Service.getListThreadJoueurConnecte().size());
	          sock.close();
	          Service.setNbrclient(Service.getNbrclient()-1);
	          System.out.println("déconnexion du client numéro: " + Service.getNbrclient() ); 
	              
	          Service.getListJoueurConnecte().remove(joueur);
	           
                System.out.println((Service.getNbrclient()-1) + " has closed window");
            
			} catch (InterruptedException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            }

        });
		
		this.setVisible(true);
		this.setTitle("MasterMind");
		this.setSize(500,700);
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);

		//C'est le panel principal de la fenêtre il contiendra: Partie 1: accueil/jeux Partie 2: les messages du serveurs
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setPreferredSize(new Dimension(500,700));
		panelPrincipal.setBackground(Color.black);
		this.add(panelPrincipal);
		
		//---------------Partie 1------------------
		partie1 = new JPanel();
		partie1.setBackground(Color.black);
		partie1.setPreferredSize(new Dimension(500,400));
		panelPrincipal.add(partie1);

		//Définition du layout
		partie1.setLayout(cardLayout);

		//Sous partie: Accueil
		JPanel acceuil = new JPanel();
		acceuil.setPreferredSize(new Dimension(500,400));
		acceuil.setBackground(Color.black);
		
		connexion=new ConnexionPart();
		inscription=new InscriptionPart();
		acceuil.add(connexion);
		acceuil.add(inscription);
		

		partie1.add(acceuil,"accueil");
		
		

		//Sous partie: Jeux
		JPanel partieGame = new JPanel();
		partieGame.setPreferredSize(new Dimension(500,400));
		partieGame.setBackground(Color.black);
		partie1.add(partieGame,"thegame");
		
		theGame=new GamePart();
		partieGame.add(theGame);
		
		//Sous partie admin
		adminPane = new AdminPart();
		adminPane.setPreferredSize(new Dimension(500,400));
		adminPane.setBackground(Color.black);
		

		partie1.add(adminPane,"admin");
		
		

		//---------------Partie 2------------------
		infos=new InfosPart();
		panelPrincipal.add(infos);
		
		initListeners();
		
		
	}
	
	void initListeners()
	{
		connexion.getSeConnecter().addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				
				String pseudo=connexion.getPseudoText();
				String mdp = connexion.geMdpText();
				if(verificationConnexion(pseudo, mdp))
				{
					if(connexionPossible())
					{
						if(joueur!=null)
						{
							switchCard("thegame");
							prepareGame();
						}
						if(admin!=null)
						{
							switchCard("admin");
						}
							
						infos.getEtatDescription().setText("");
					}
					else
						infos.getEtatDescription().setText("  " + infos.getEtatDescription().getText() + "\n" 
								+ "Désolé le serveur est plein et vous n'avez pas assez de fidelité . . . ." );
				}
				else if(pseudo.length()==0)
				{
					infos.getEtatDescription().setText("  " + infos.getEtatDescription().getText() + "\n" 
							+ "Veuillez entrer votre pseudo !" );
				}
				else if(mdp.length()==0)
				{
					infos.getEtatDescription().setText("  " + infos.getEtatDescription().getText() + "\n" 
							+ "Veuillez entrer votre mot de passe !" );
				}
				else
				{
					infos.getEtatDescription().setText("  " + infos.getEtatDescription().getText() + "\n" 
								+ "Mauvaise combinaison !" );
				}
				
				
			}
	
		});
		
		inscription.getsInscrire().addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				
				String pseudo=inscription.getPseudoText();
				String mdp = inscription.geMdpText();
				
				try {
					if(!Service.getBdd().userNameExists(pseudo)) //Si le pseudo n'existe pas dans la base de donnée on insère l'enregistrement
					{
						Service.getBdd().insertJoueur(pseudo, mdp);
						infos.getEtatDescription().setText("  " + infos.getEtatDescription().getText() + "\n" 
								+"Merci de vous être inscrit et bienvenue !" );
					}
					else //Sinon on le signal a l'utilisateur
					{
						infos.getEtatDescription().setText("  " + infos.getEtatDescription().getText() + "\n" 
							+ "Pseudo déjà utilisé ... \n" );
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					infos.getEtatDescription().setText("  " + infos.getEtatDescription().getText() + "\n" 
							+ e.getMessage() );
				}
			
			}
		});
		
		adminPane.getDeconnexion().addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
				switchCard("accueil");
			}
	
		});
		
		adminPane.getRazInscrit().addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
            	try {
					Service.getBdd().razInscrit();
					 infos.getEtatDescription().setText("Tout les inscrits ont été remis a zéro");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		});

		adminPane.getRazScore().addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
            	try {
	            	Service.getBdd().razScores();
					 infos.getEtatDescription().setText("Tout les scores ont été remis a zéro");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		});

		adminPane.getDeleteInscrit().addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent arg0)
			{
            	try {
	            	Service.getBdd().deleteBDD();
					 infos.getEtatDescription().setText("Le contenu de la base de donnée a été supprimé");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		});

	}
	
	void prepareGame() {
		theGame.getGame().begin(this);
	}
	
	public void switchCard(String str)
	{
		((CardLayout) partie1.getLayout()).show(partie1, str);
	}
	
	   
	   public boolean verificationConnexion(String pseudo, String mdp) {
		if(pseudo.toLowerCase().contains("/"))
		{
			admin=Service.getBdd().getAdmin(pseudo, mdp);
			if(admin!=null)
				return true;
			
			return false;
		}
		
		joueur= Service.getBdd().getJoueur(pseudo, mdp);
	   	if(joueur!=null)
	   		return true;
	   	return false;
	   }


	   public void run()
	   {
		    System.err.println("Lancement du traitement de la connexion cliente\n");
	  		this.setVisible(true);     
	   }
	  
	   
	  
	   

		
		void mettreAjourMinFidelite()
			 {
				 minFidelite=Service.getListJoueurConnecte().get(0).getNbrJeuxGagne();
				 for(int i=1; i<Service.getListJoueurConnecte().size(); i++)
				 {
					 if(minFidelite>Service.getListJoueurConnecte().get(i).getNbrJeuxGagne())
						 minFidelite=Service.getListJoueurConnecte().get(i).getNbrJeuxGagne();
				 }
			 }



		
		public boolean connexionPossible()
		{	    
		    if(Service.getNbmaxclient()<Service.getNbrclient())
		    {
		        if(joueur!=null)
		        {
		       	 if(minFidelite>=joueur.getNbrJeuxGagne())
		       	 {
		                //Si sa fidelite est inférieur a celle du min de celle des joueurs déjà connectée
		                //Alors il ne poura pas se connecter
		                // envoi d'un message de trop de connexions
		                return false;
		       	 }
		       	 else
			             //Si c'est un joueur et que sa fidelité est supérieur a celle du min
			             //On doit déconnecter le joueur avec la fidelité min et connecter celui la
			             //puis mettre a jour min
		       	 {
			          	   //On doit mettre a jour min et deconnecter celui avec la prio min
			        		 try {
								deconnecterJoueurMinFid();
							} catch (InterruptedException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			                 Service.setNbrclient(Service.getNbrclient()-1);
			                 //On ajoute le joueur a la liste des joueurs connectés
			                 Service.getListJoueurConnecte().add(joueur);
			        		 mettreAjourMinFidelite();

			    			 
			            	 //Puis le jeu peu commencer
			            	 return true;
			             }
		        }
		       
		        //Si c'est un administrateur on le laisse se connecter dans touut les cas
		        else if(admin!=null)
			       	 return true;
		    }
		    else //On ne se pose pas de questions
		    {
			   	 if(joueur!=null)
			   	 {
			   		 Service.getListJoueurConnecte().add(joueur);
			   		 mettreAjourMinFidelite();
		
		        	 return true;
			   	 }
			   	 if(admin!=null)
			       	 return true;
		    }
		    
		    return false;
		
		}

		
		void deconnecterJoueurMinFid() throws InterruptedException, IOException
		{
			//Il faut trouver le joueur avec la fidelite minimal 
			//Et s'il y'en a beaucoup il faut deconnecter le dernier puisque c'est le dernier à s'être connecte
			
			//On cherche le joueur
			int indexMin=0;
			for(int i=1; i<Service.getListJoueurConnecte().size(); i++)
			{
				if(minFidelite>=Service.getListJoueurConnecte().get(i).getNbrJeuxGagne())
					indexMin=i;
			}
			Service.getListJoueurConnecte().remove(indexMin);
			//Joueur trouve et supprimé de la liste des joueurs
			//On doit le deconnecter !
			infos.getEtatDescription().setText("Vous allez être deconnecté ... désolé vous n'avez pas assez de fidelité");
			Service.getListThreadJoueurConnecte().get(indexMin).stop();;
			
			
		}
		
		
		//---------------------Getters & Setters----------------------------


		public Socket getSock() {
			return sock;
		}

		public void setSock(Socket sock) {
			this.sock = sock;
		}

		public boolean isPlayGame() {
			return playGame;
		}

		public void setPlayGame(boolean playGame) {
			this.playGame = playGame;
		}
		
		   
		public Joueur getJoueur() {
			return joueur;
		}
		
		public void setJoueur(Joueur joueur) {
			this.joueur = joueur;
		}
		
		public Admin getAdmin() {
			return admin;
		}
		
		public void setAdmin(Admin admin) {
			this.admin = admin;
		}

		public InfosPart getInfos() {
			return infos;
		}

		public void setInfos(InfosPart infos) {
			this.infos = infos;
		}

		public GamePart getTheGame() {
			return theGame;
		}

		public void setTheGame(GamePart theGame) {
			this.theGame = theGame;
		}

		public JPanel getPartie1() {
			return partie1;
		}

		public void setPartie1(JPanel partie1) {
			this.partie1 = partie1;
		}
		
		
		
		
	
	
	
	
	
	
	

	
}
