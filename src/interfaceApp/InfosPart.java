package interfaceApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InfosPart extends JPanel{

	private JPanel etat;
	private JTextArea etatDescription;
	private JLabel labelEtat; 
	
	public InfosPart()
	{
		etat = new JPanel();
		etat.setPreferredSize(new Dimension(450,200));
		etat.setBackground(Color.black);
		
		labelEtat = new JLabel("Messages . . .");
	    labelEtat.setVerticalAlignment(JLabel.CENTER);
		labelEtat.setPreferredSize(new Dimension(450,15));
		labelEtat.setFont(new Font("Arial", Font.ITALIC, 12));
		labelEtat.setForeground(Color.WHITE);
		
		etatDescription = new JTextArea();
		etatDescription.setPreferredSize(new Dimension(450,200));
		etatDescription.setEditable(false);
		etatDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		
		etat.add(labelEtat);
		etat.add(new JScrollPane(etatDescription));
		
		this.add(etat);
	}

	public JTextArea getEtatDescription() {
		return etatDescription;
	}

	public void setEtatDescription(JTextArea etatDescription) {
		this.etatDescription = etatDescription;
	}
	
	
}
