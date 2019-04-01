package interfaceApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gamePack.Game;

public class GamePart extends JPanel{

	private JTextField proposition;
	private JButton envoyer, deconnexion, abandonner;
	private JLabel labelproposition, labelTitre;
	
	private Game game=new Game();
	
	public GamePart()
	{
		this.setPreferredSize(new Dimension(500,400));
		this.setBackground(Color.BLACK);
		
		proposition = new JTextField("");
		proposition.setPreferredSize(new Dimension(200,20));
		

		labelTitre = new JLabel("DA GAME");
		labelTitre.setHorizontalAlignment(JLabel.CENTER);
		labelTitre.setVerticalAlignment(JLabel.CENTER);
		labelTitre.setFont(new Font("Serif", Font.BOLD, 20));
		labelTitre.setPreferredSize(new Dimension(500,35));
		labelTitre.setOpaque(true);
		labelTitre.setBackground(new Color(123,207,245));
		
		labelproposition = new JLabel("Votre proposition: ");
		labelproposition.setForeground(Color.WHITE);
		labelproposition.setLabelFor(proposition);
		labelproposition.setPreferredSize(new Dimension(190,35));
		labelproposition.setFont(new Font("Arial", Font.PLAIN, 14));
		
		this.add(labelTitre);

		this.add(labelproposition);
		this.add(proposition);
		
		JPanel options = new JPanel();
		options.setPreferredSize(new Dimension(500,40));
		options.setBackground(new Color(123,207,245));
		
		envoyer = new JButton("Envoyer !");
		envoyer.setPreferredSize(new Dimension(150,30));
		envoyer.setForeground(Color.white);
		envoyer.setFont(new Font("Arial", Font.BOLD, 14));
		envoyer.setBackground(Color.black);

		deconnexion = new JButton("Se deconnecter");
		deconnexion.setPreferredSize(new Dimension(130,30));
		deconnexion.setForeground(Color.white);
		deconnexion.setFont(new Font("Arial", Font.BOLD, 12));
		deconnexion.setBackground(Color.black);

		abandonner = new JButton("Abandonner :(");
		abandonner.setPreferredSize(new Dimension(130,30));
		abandonner.setForeground(Color.white);
		abandonner.setFont(new Font("Arial", Font.BOLD, 12));
		abandonner.setBackground(Color.black);

		options.add(abandonner);
		options.add(envoyer);
		options.add(deconnexion);
		
		
		
		this.add(options);
	}

	public JTextField getProposition() {
		return proposition;
	}

	public void setProposition(JTextField proposition) {
		this.proposition = proposition;
	}

	public JButton getEnvoyer() {
		return envoyer;
	}

	public void setEnvoyer(JButton envoyer) {
		this.envoyer = envoyer;
	}

	public JButton getDeconnexion() {
		return deconnexion;
	}

	public void setDeconnexion(JButton deconnexion) {
		this.deconnexion = deconnexion;
	}

	public JButton getAbandonner() {
		return abandonner;
	}

	public void setAbandonner(JButton abandonner) {
		this.abandonner = abandonner;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
	
	
	
	
}
