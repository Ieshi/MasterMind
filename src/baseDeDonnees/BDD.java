package baseDeDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import clientPack.*;

public class BDD {
	
	private Connection con;
	private Statement state;
	
	public void connexion(){
		 try { 
		  Class.forName("com.mysql.jdbc.Driver");
	      System.out.println("Driver O.K.");

	      String url = "jdbc:mysql://localhost:3306/masterMind";
	      String user = "root";
	      String passwd = "";

	      con = DriverManager.getConnection(url, user, passwd);
	     state = con.createStatement();
	      System.out.println("Connexion effective !");         
	         
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	}
	
	public Joueur getJoueur(String pseudo, String mdp)
	{
		try {
			ResultSet rsJoueur = state.executeQuery("Select * from joueur where pseudo='"+pseudo + "' and mdp='" + mdp+"'");
			while(rsJoueur.next()) //Normalement on en a qu'un seul donc si ça rentre c'est qu'il trouve un joueur sinon bah il trouve pas
			{         
				int id=rsJoueur.getInt("id");
				int score=rsJoueur.getInt("score");
				int nbrJeuxEffectue=rsJoueur.getInt("nbrJeuxEffectue");
				int scoreDerPartie=rsJoueur.getInt("scoreDerPartie");
				int nbrJeuxGagne=rsJoueur.getInt("nbrJeuxGagne");
				
				ResultSet rsClassement = state.executeQuery("Select * from classementglobal where id="+id);
				rsClassement.next();//Normalement il n'y a qu'un seul :)
				int classement=rsClassement.getInt("classement");
				return new Joueur(id, pseudo, mdp, score, nbrJeuxEffectue, scoreDerPartie, classement, nbrJeuxGagne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public Admin getAdmin(String pseudo, String mdp)
	{
		try {
			ResultSet rsAdmin = state.executeQuery("Select * from administrateur where pseudo='"+pseudo + "' and mdp='" + mdp+"'");
			while(rsAdmin.next()) //Normalement on en a qu'un seul donc si ça rentre c'est qu'il trouve un joueur sinon bah il trouve pas
			{         
				return new Admin(pseudo,mdp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Integer> sort(List<Joueur> joueurSwap) {
		List<Integer> idSwap=new ArrayList<Integer>();
				List<Integer> classement=new ArrayList<Integer>();
				System.out.println("Size of joueurSwap: " + joueurSwap);
				
				//On récupère les classement
				for(int i=0; i<joueurSwap.size(); i++)
				{
					classement.add(joueurSwap.get(i).getClassement());
				}
				System.out.println("Size of classement: " + classement);
				//On les trie en ordre croissant
				for(int i=0; i<classement.size(); i++)
				{
					//On défini le min une première fois
					int min=classement.get(i);
					int indiceMin=i;
					//On cherche le min dans le reste de la liste
					for(int j=i+1; j<classement.size(); j++)
					{
						if(min>classement.get(j))
						{
							min=classement.get(j);
							indiceMin=j;
						}
					}
					//On permute les cases i et indiceMin
					if (indiceMin!=i)
					{
						int tmp=classement.get(i);
						classement.set(i, min);
						classement.set(indiceMin, tmp);

						idSwap.add(joueurSwap.get(indiceMin).getId());
					}
				}
				System.out.println("Size of idswap: " + idSwap);
				
				return idSwap;
	}
	
	
	public void updateScore(int score, Joueur j)
	{
	      String query = "update joueur set score = ?, nbrJeuxEffectue = ?,   scoreDerPartie = ?,  nbrJeuxGagne= ? where id = ?";
	      PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);
		      preparedStmt.setInt   (1, j.getScore()+score);
		      
		      preparedStmt.setInt   (2, j.getNbrJeuxEffectue()+1);
		      
		      preparedStmt.setInt   (3,score);

		      int nbr=j.getNbrJeuxGagne();
		      if(score>0)
		    	  nbr++;

		      preparedStmt.setInt   (4, nbr);

		      preparedStmt.setInt   (5, j.getId());

		      // execute the java preparedstatement
		     preparedStmt.executeUpdate();
		     updateClassement(j);
		     
		     
		     preparedStmt.close();
		 
		       
		      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateClassement(Joueur j)
	{
		//On doit récupérer les joueurs qui ont +10 si score>0 et-5 sinon
		try {
			ResultSet rsClassement = state.executeQuery("Select classementglobal.id, classement, score from joueur inner join classementglobal on joueur.id=classementglobal.id where score>="+(j.getScore()-10)+" and score<="+(j.getScore()+20)+" order by classement ASC");
			//rsClassement ne sera jamais vide puisqu'il contiendra le joueur courant (son score appartient à l'intervalle)
			
			List<Integer> idSwap = new ArrayList<Integer>();
			List<Joueur> joueurSwap=new ArrayList<Joueur>();
			
			while(rsClassement.next())//On récupère tout les joueurs ayant un score inférieur au score du joueur courant(leur classement sera potentiellement affecté)
			{         
				int id=rsClassement.getInt("classementglobal.id");
				int classement=rsClassement.getInt("classement");
				int scoreJ=rsClassement.getInt("score");
				if(j.getScore()>=scoreJ)
				{
					joueurSwap.add(new Joueur(id,"--","--", scoreJ, 0,0,classement,0));
				}
			}
			//Ici on récupère le classement du joueur ayant le score maximal
			//Les classements des joueurs entre le joueur courant et celui la seront affectés
			if(joueurSwap.size()>0) {
				int maxScore=joueurSwap.get(0).getScore();
				int classement=joueurSwap.get(0).getClassement();
				for(int i=1; i<joueurSwap.size(); i++)
				{
					if(maxScore<joueurSwap.get(i).getScore())
					{
						maxScore=joueurSwap.get(i).getScore();
						classement=joueurSwap.get(i).getClassement();
					}
				}
				idSwap=sort(joueurSwap);
			      PreparedStatement preparedStmt=null;
				//On a trouvé son nouveau classement  donc maintenant il faut le mettre à jouuur et mettre à jour le classement des autres joueurs avec un score plus faible/elevé
				if(classement>j.getClassement() && idSwap.size()!=0) //Son score a baissé
				{
					//On modifie les classements des joueurs entre l'ancien classement et le nouveau
					for(int i=classement; i>=j.getClassement(); i--)
					{
						String query = "update classementglobal set classement = ? where id = ?";
							preparedStmt = con.prepareStatement(query);
						      preparedStmt.setInt(1, i);
						      preparedStmt.setInt(2, idSwap.get(i));
	
						      // execute the java preparedstatement
						     preparedStmt.executeUpdate();
					}

				}
				else if(classement<j.getClassement() && idSwap.size()!=0)//Son score a augmenté (pour le cas ou c'est égal ça ne rentrera pas dans la boucle)
				{
					//On modifie les classements des joueurs entre l'ancien classement et le nouveau
					for(int i=j.getClassement(); i>=classement; i--)
					{
						String query = "update classementglobal set classement = ? where id = ?";
							preparedStmt = con.prepareStatement(query);
						      preparedStmt.setInt(1, i);
						      preparedStmt.setInt(2, idSwap.get(i));
	
						      // execute the java preparedstatement
						     preparedStmt.executeUpdate();
					}
					
				}
				if(preparedStmt!=null)
					preparedStmt.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertJoueur(String pseudo,String mdp)
	{
		try {
			PreparedStatement preparedStmtJ = con.prepareStatement("insert into joueur (pseudo,mdp) values (?,?)");
		      preparedStmtJ.setString(1, pseudo);
		      preparedStmtJ.setString(2, mdp);
		      // execute the java preparedstatement
		      preparedStmtJ.executeUpdate();
		     preparedStmtJ.close();
		     

		     ResultSet rsClassement=state.executeQuery("SELECT classement FROM classementGlobal ORDER BY classement DESC LIMIT 1");
		     rsClassement.next();//Normalement il n'y qu'une seule ligne
		     int classement=rsClassement.getInt("classement");
		     classement+=1;
		     
		     ResultSet rsId=state.executeQuery("SELECT id FROM joueur where pseudo='" + pseudo + "'");
		     rsId.next();//Normalement il n'y qu'une seule ligne
		     int id=rsId.getInt("id");
		     
		     System.out.println(id);
		     PreparedStatement preparedStmt=con.prepareStatement("insert into classementglobal (id,classement) values(?,?)");
		     preparedStmt.setInt(1, id);
		     preparedStmt.setInt(2, classement);
		     preparedStmt.executeUpdate();
		     preparedStmt.close();
		     
		 
		       
		      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean userNameExists(String userName) throws SQLException
	{
		ResultSet rs=state.executeQuery("select pseudo from joueur where pseudo='" + userName + "'");
	    if(!rs.next())
	    {
	    	rs.close();
	    	return false;
	    }
    	rs.close();
	    return true;
		
	}
	
	public void razInscrit() throws SQLException
	{
		ResultSet rsSelect = state.executeQuery("select id from joueur");
		while(rsSelect.next())
		{
			PreparedStatement psJoueur=con.prepareStatement("update joueur set score = 0, nbrJeuxEffectue=0, scoreDerPartie=0, nbrJeuxGagne=0 where id = ?");
			psJoueur.setInt(1, rsSelect.getInt("id"));
			psJoueur.executeUpdate();
			psJoueur.close();
			
			PreparedStatement psClassement=con.prepareStatement("update classementglobal set classement = ? where id = ?");
			psClassement.setInt(1, rsSelect.getInt("id"));
			psClassement.setInt(2, rsSelect.getInt("id"));
			psClassement.executeUpdate();
			psClassement.close();
		}
		rsSelect.close();
		
	}
	
	public void razScores() throws SQLException
	{
		ResultSet rsSelect = state.executeQuery("select id from joueur");
		while(rsSelect.next())
		{
			PreparedStatement psJoueur=con.prepareStatement("update joueur set score = 0 where id = ?");
			psJoueur.setInt(1, rsSelect.getInt("id"));
			psJoueur.executeUpdate();
			psJoueur.close();
			
			PreparedStatement psClassement=con.prepareStatement("update classementglobal set classement = ? where id = ?");
			psClassement.setInt(1, rsSelect.getInt("id"));
			psClassement.setInt(2, rsSelect.getInt("id"));
			psClassement.executeUpdate();
			psClassement.close();
		}
		rsSelect.close();
	}
	
	public void deleteBDD() throws SQLException
	{
		//On commence par supprimer classement puisqu'elle contient une clé étrangère
		PreparedStatement psClassement=con.prepareStatement("delete from classementglobal");
		psClassement.executeUpdate();
		psClassement.close();
		
		//Puis on supprime le contenu de joueur
		PreparedStatement psJoueur=con.prepareStatement("delete from joueur");
		psJoueur.executeUpdate();
		psJoueur.close();
	}
    
		  
}
