package clientPack;


public class Joueur extends Client {
	private int id;
	private int score;
	private int nbrJeuxEffectue;
	private int scoreDerPartie;
	private int classement;
	private int nbrJeuxGagne;
	
	public Joueur(int id, String pseudo, String mdp, int score, int nbrJeuxEffectue, int scoreDerPartie, int classement, int nbrJeuxGagne)
	{
		super(pseudo, mdp);
		this.id=id;
		this.score=score;
		this.nbrJeuxEffectue=nbrJeuxEffectue;
		this.scoreDerPartie=scoreDerPartie;
		this.classement=classement;
		this.nbrJeuxGagne=nbrJeuxGagne;
	}	
	
	public Joueur(String pseudo, String mdp)
	{
		super(pseudo, mdp);
		this.score=0;
		this.nbrJeuxEffectue=0;
		this.scoreDerPartie=0;
		this.nbrJeuxGagne=0;
	}
	
	public String toString()
	{
		String res="\n" + "Pseudo: " + this.pseudo + "\n";
		res+="Id: " + this.id + "\n";
		res+="Score: " + this.score + "\n";
		res+="Nombre de jeux effectués: " + this.nbrJeuxEffectue + "\n";
		res+="Score de la dernière partie: " + this.scoreDerPartie + "\n";
		res+="Classement: " + this.classement + "\n";
		res+="Nombre de jeux gagnés: " + this.nbrJeuxGagne + "\n";
		return  res;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNbrJeuxEffectue() {
		return nbrJeuxEffectue;
	}

	public void setNbrJeuxEffectue(int nbrJeuxEffectue) {
		this.nbrJeuxEffectue = nbrJeuxEffectue;
	}

	public int getScoreDerPartie() {
		return scoreDerPartie;
	}

	public void setScoreDerPartie(int scoreDerPartie) {
		this.scoreDerPartie = scoreDerPartie;
	}

	public int getClassement() {
		return classement;
	}

	public void setClassement(int classement) {
		this.classement = classement;
	}

	public int getNbrJeuxGagne() {
		return nbrJeuxGagne;
	}

	public void setNbrJeuxGagne(int nbrJeuxGagne) {
		this.nbrJeuxGagne = nbrJeuxGagne;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
