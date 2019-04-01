package interfaceApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import serveurPack.Service;

public class AdminPart extends JPanel{
	private JButton razInscrit, razScore, deleteInscrit, deconnexion;
	private JLabel labelTitre, labelRazInscrit, labelRazScore, labelDeleteInscrit;
	
	public AdminPart()
	{
		this.setPreferredSize(new Dimension(500,200));
		this.setBackground(Color.BLACK);
		

		labelTitre = new JLabel("Adminitrator");
		labelTitre.setHorizontalAlignment(JLabel.CENTER);
		labelTitre.setVerticalAlignment(JLabel.CENTER);
		labelTitre.setFont(new Font("Serif", Font.BOLD, 20));
		labelTitre.setPreferredSize(new Dimension(500,35));
		labelTitre.setOpaque(true);
		labelTitre.setBackground(new Color(123,207,245));
		
		labelRazInscrit = new JLabel("Supprimer les données des inscrits");
		labelRazInscrit.setForeground(Color.WHITE);
		labelRazInscrit.setLabelFor(razInscrit);
		labelRazInscrit.setPreferredSize(new Dimension(300,35));
		labelRazInscrit.setFont(new Font("Arial", Font.PLAIN, 14));
		
		labelRazScore = new JLabel("Supprimer les scores");
		labelRazScore.setForeground(Color.WHITE);
		labelRazScore.setLabelFor(razScore);
		labelRazScore.setPreferredSize(new Dimension(300,35));
		labelRazScore.setFont(new Font("Arial", Font.PLAIN, 14));
		
		labelDeleteInscrit = new JLabel("Tout supprimer");
		labelDeleteInscrit.setForeground(Color.WHITE);
		labelDeleteInscrit.setLabelFor(deleteInscrit);
		labelDeleteInscrit.setPreferredSize(new Dimension(300,35));
		labelDeleteInscrit.setFont(new Font("Arial", Font.PLAIN, 14));
		

		
		razInscrit = new JButton("");
		razInscrit.setPreferredSize(new Dimension(30,30));
		razInscrit.setForeground(Color.white);
		razInscrit.setFont(new Font("Arial", Font.BOLD, 14));
		razInscrit.setBackground(new Color(123,207,245));
		

		
		razScore = new JButton("");
		razScore.setPreferredSize(new Dimension(30,30));
		razScore.setForeground(Color.white);
		razScore.setFont(new Font("Arial", Font.BOLD, 14));
		razScore.setBackground(new Color(123,207,245));
		

		
		deleteInscrit = new JButton("");
		deleteInscrit.setPreferredSize(new Dimension(30,30));
		deleteInscrit.setForeground(Color.white);
		deleteInscrit.setFont(new Font("Arial", Font.BOLD, 14));
		deleteInscrit.setBackground(new Color(123,207,245));
		
		this.add(labelTitre);

		this.add(labelRazInscrit);
		this.add(razInscrit);

		this.add(labelRazScore);
		this.add(razScore);
		
		this.add(labelDeleteInscrit);
		this.add(deleteInscrit);
		
		JPanel deconnect = new JPanel();
		deconnect.setPreferredSize(new Dimension(500,40));
		deconnect.setBackground(new Color(123,207,245));
		
		deconnexion = new JButton("Se deconnecter");
		deconnexion.setPreferredSize(new Dimension(200,30));
		deconnexion.setForeground(Color.white);
		deconnexion.setFont(new Font("Arial", Font.BOLD, 14));
		deconnexion.setBackground(Color.black);
		
		deconnect.add(deconnexion);
		
		this.add(deconnect);
	}

	public JButton getDeconnexion() {
		return deconnexion;
	}

	public void setDeconnexion(JButton deconnexion) {
		this.deconnexion = deconnexion;
	}

	public JButton getRazInscrit() {
		return razInscrit;
	}

	public void setRazInscrit(JButton razInscrit) {
		this.razInscrit = razInscrit;
	}

	public JButton getRazScore() {
		return razScore;
	}

	public void setRazScore(JButton razScore) {
		this.razScore = razScore;
	}

	public JButton getDeleteInscrit() {
		return deleteInscrit;
	}

	public void setDeleteInscrit(JButton deleteInscrit) {
		this.deleteInscrit = deleteInscrit;
	}
	
	

}
