package interfaceApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConnexionPart extends JPanel
{
	private JTextField pseudo, mdp;
	private JButton seConnecter;
	private JLabel labelTitre, labelPseudo, labelMdp;
	
	public ConnexionPart()
	{
		this.setPreferredSize(new Dimension(500,200));
		this.setBackground(Color.BLACK);
		
		pseudo = new JTextField("");
		pseudo.setPreferredSize(new Dimension(200,20));
		
		mdp = new JPasswordField("");
		mdp.setPreferredSize(new Dimension(200,20));
		

		labelTitre = new JLabel("Connexion");
		labelTitre.setHorizontalAlignment(JLabel.CENTER);
		labelTitre.setVerticalAlignment(JLabel.CENTER);
		labelTitre.setFont(new Font("Serif", Font.BOLD, 20));
		labelTitre.setPreferredSize(new Dimension(500,35));
		labelTitre.setOpaque(true);
		labelTitre.setBackground(new Color(123,207,245));
		
		labelPseudo = new JLabel("Pseudo: ");
		labelPseudo.setForeground(Color.WHITE);
		labelPseudo.setLabelFor(pseudo);
		labelPseudo.setPreferredSize(new Dimension(190,35));
		labelPseudo.setFont(new Font("Arial", Font.PLAIN, 14));
		
		labelMdp = new JLabel("Mot de passe: ");
		labelMdp.setLabelFor(mdp);
		labelMdp.setForeground(Color.WHITE);
		labelMdp.setPreferredSize(new Dimension(190,35));
		labelMdp.setFont(new Font("Arial", Font.PLAIN, 14));
		
		this.add(labelTitre);

		this.add(labelPseudo);
		this.add(pseudo);
		
		this.add(labelMdp);
		this.add(mdp);
		
		JPanel connect = new JPanel();
		connect.setPreferredSize(new Dimension(500,40));
		connect.setBackground(new Color(123,207,245));
		
		seConnecter = new JButton("Se connecter !");
		seConnecter.setPreferredSize(new Dimension(200,30));
		seConnecter.setForeground(Color.white);
		seConnecter.setFont(new Font("Arial", Font.BOLD, 14));
		seConnecter.setBackground(Color.black);
		
		connect.add(seConnecter);
		
		
		
		this.add(connect);
	}
	
	public String getPseudoText()
	{
		return pseudo.getText();
	}
	
	public String geMdpText()
	{
		return mdp.getText();
	}

	public JTextField getPseudo() {
		return pseudo;
	}

	public void setPseudo(JTextField pseudo) {
		this.pseudo = pseudo;
	}

	public JTextField getMdp() {
		return mdp;
	}

	public void setMdp(JTextField mdp) {
		this.mdp = mdp;
	}

	public JButton getSeConnecter() {
		return seConnecter;
	}

	public void setSeConnecter(JButton seConnecter) {
		this.seConnecter = seConnecter;
	}
	
	

}
