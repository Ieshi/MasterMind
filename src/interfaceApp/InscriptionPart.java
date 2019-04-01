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

public class InscriptionPart extends JPanel {
	

	private JTextField pseudo, mdp;
	private JButton sInscrire;
	private JLabel labelTitre, labelPseudo, labelMdp;
	
	public InscriptionPart()
	{
		this.setPreferredSize(new Dimension(500,200));
		this.setBackground(Color.BLACK);
		
		pseudo = new JTextField("");
		pseudo.setPreferredSize(new Dimension(200,20));
		
		mdp = new JPasswordField("");
		mdp.setPreferredSize(new Dimension(200,20));
		

		labelTitre = new JLabel("Inscription");
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
		
		JPanel insc = new JPanel();
		insc.setPreferredSize(new Dimension(500,40));
		insc.setBackground(new Color(123,207,245));
		
		sInscrire = new JButton("S'inscrire !");
		sInscrire.setPreferredSize(new Dimension(200,30));
		sInscrire.setForeground(Color.white);
		sInscrire.setFont(new Font("Arial", Font.BOLD, 14));
		sInscrire.setBackground(Color.black);
		sInscrire.addActionListener(new ActionListener()
				{

					public void actionPerformed(ActionEvent arg0)
					{
						//Quoi faire ici
					}
			
				});
		
		insc.add(sInscrire);
		
		this.add(insc);
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

	public JButton getsInscrire() {
		return sInscrire;
	}

	public void setsInscrire(JButton sInscrire) {
		this.sInscrire = sInscrire;
	}
	
	

}
