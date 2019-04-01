package gamePack;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import interfaceApp.MainFrame;
import serveurPack.*;

public class Game {

	   
	   public List<String> generationCombinaison() {
	   	List<String> listLettre=new ArrayList<String>();
	   	String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	   	for(int i=0; i<5; i++)
	   	{
	   		int tmp=(int)(Math.random()*26);
	   		listLettre.add(alphabet.substring(tmp, tmp+1));
	   	}
	   	return listLettre;
	   }
	   
	   public boolean verifierPropUser(String rep, String comb) {
	   	if(rep.toLowerCase().equals(comb.toLowerCase()))
	   	{
	   		return true;
	   	}
	   	return false;
	   }
	   
	   
	   public int compterScore(long time)
	   {
		   if(time<=60000)
			   return 10;
		   else if(time<=180000)
			   return 5;
		   else
			   return 1;
	   }
	   
	   public String verifierLettreEmplacement(String rep, String comb)
	   {
		   String message="Vous avez: ";
		   int nbrLettreBienPlace=0;
		   int nbrLettreJuste=0;
		   int sizeComp=rep.length();
		   if(sizeComp>comb.length())
			   sizeComp=comb.length();
		   for(int i=0; i<sizeComp; i++)
		   {
			   if(rep.substring(i, i+1).equals(comb.substring(i,i+1)))
				   nbrLettreBienPlace++;
			   if(comb.contains(rep.substring(i, i+1)))
				   nbrLettreJuste++;			
		   }
		   
		   if(nbrLettreBienPlace==0 && nbrLettreJuste==0)
			   return "";
		   else
		   {
			   if(nbrLettreJuste!=0)
				   message+=nbrLettreJuste + " lettres justes " ;
			   if(nbrLettreBienPlace!=0)
				   message+="dont " + nbrLettreBienPlace + " lettres bien placées" ;
			return message;
		   }
	   }
	   
	public void begin(MainFrame mf) 
		   {
		String ancienText=mf.getInfos().getEtatDescription().getText()+"\n";
			   mf.getInfos().getEtatDescription().setText(ancienText + "merci "+ mf.getJoueur().getPseudo()+", vous êtes connecté ! \n Voici vos stats" + mf.getJoueur());

				ancienText+=mf.getInfos().getEtatDescription().getText()+"\n";
		         try {
					Thread.sleep(1000);
					
		         
		      	   List<String> listLettre=generationCombinaison();
		      	   String comb="";
		      	   for(int i=0; i<listLettre.size(); i++)
		      	   		comb+=listLettre.get(i);
		      	   System.out.println("La combinaison à chercher est: " + comb + " mais chuut !");

				   mf.getInfos().getEtatDescription().setText(ancienText + "\n" + "La combinaison a été générée vous pouvez commencer");
					ancienText+=mf.getInfos().getEtatDescription().getText()+"\n";
				   long startTime = System.currentTimeMillis();
		           
		           mf.getTheGame().getEnvoyer().addActionListener(new ActionListener()
		   		{

		   			public void actionPerformed(ActionEvent arg0)
		   			{
				          int score=0;
				      	   String comb="";
				      	   for(int i=0; i<listLettre.size(); i++)
				      	   		comb+=listLettre.get(i);
						String prop= mf.getTheGame().getProposition().getText();
						if(verifierPropUser(prop,comb)) {
			                 score =compterScore(System.currentTimeMillis() - startTime);
				             Service.getBdd().updateScore(score, mf.getJoueur());
				             
				             mf.getInfos().getEtatDescription().setText("\n" + "Gagné ! \n Une nouvelle combinaison va être généré automatiquement dans quelques instants");
				             try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				            score=0;
					      	   List<String> listLettre=generationCombinaison();
					      	   comb="";
					      	   for(int i=0; i<listLettre.size(); i++)
					      	   		comb+=listLettre.get(i);
					      	   System.out.println("La combinaison à chercher est: " + comb + " mais chuut !");

							   mf.getInfos().getEtatDescription().setText("\n" + "La combinaison a été générée vous pouvez commencer");
					           long startTime = System.currentTimeMillis();
			             }
						else
							mf.getInfos().getEtatDescription().setText("\n" + "Perdu ! Réessayez ... " + verifierLettreEmplacement(prop,comb));
		   				
		   			}
		   		});
		           
		           mf.getTheGame().getAbandonner().addActionListener(new ActionListener()
			   		{

			   			public void actionPerformed(ActionEvent arg0)
			   			{
			   				String ancienText=mf.getInfos().getEtatDescription().getText()+"\n";
			   			 mf.getInfos().getEtatDescription().setText("\n" + "Vous venez de perdre 5 points ... ");
			                 int score=-5;
				             Service.getBdd().updateScore(score, mf.getJoueur());
			   				
			   			}
			   		});
		           

		           mf.getTheGame().getDeconnexion().addActionListener(new ActionListener()
			   		{

			   			public void actionPerformed(ActionEvent arg0)
			   			{

			   				mf.getInfos().getEtatDescription().setText("merci "+ mf.getJoueur().getPseudo()+"\n Voici vos stats" + mf.getJoueur() + " \n Vous êtes deconnecté . . . Aurevoir :)");
							mf.switchCard("accueil");
				            try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			   			}
			   		});

		         

		 		} catch (InterruptedException e) {
		 			
		 			e.printStackTrace();
		 		} 
		         
		   }
}

