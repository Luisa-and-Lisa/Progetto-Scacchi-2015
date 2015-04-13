import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * Classe che comunica la fine di una partita (Scacco Matto o Patta).
 * Si puo' iniziare una nuova partita o uscire dal gioco.
 */
public class FinePartita implements ActionListener{

	private Clip clip;
	private JFrame Frame;
	private JFrame FinePartita;
	private JButton partita;
	private JButton esci;
	private String Colore;
	private String Tipo;
	private JLabel label;
	private JLabel vittoria;
	
	public FinePartita(JFrame Frame, String Colore, String Tipo, Clip clip){

		this.Frame = Frame;
		this.Colore = Colore;
		this.Tipo = Tipo;
		this.clip = clip;
		
		FinePartita = new JFrame("Fine della partita");
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		if (Tipo == "ScaccoMatto"){

			if (Colore == "bianco")
				Colore = "Bianchi";
			else if (Colore == "nero")
				Colore = "Neri";
			
			label = new JLabel("SCACCO MATTO", SwingConstants.CENTER);
			label.setFont(new Font("Garamond", Font.BOLD, 40));
			label.setForeground(Color.red);
		
			vittoria = new JLabel("Vittoria ai " + Colore, SwingConstants.CENTER);
			vittoria.setFont(new Font("Garamond", Font.BOLD, 30));
		}
		else if (Tipo == "Patta"){
			label = new JLabel("PATTA", SwingConstants.CENTER);
			label.setFont(new Font("Garamond", Font.BOLD, 40));
			label.setForeground(Color.red);
			
			vittoria = new JLabel("Vittoria ai bianchi e ai neri", SwingConstants.CENTER);
			vittoria.setFont(new Font("Garamond", Font.BOLD, 30));
		}
		
		partita = new JButton("INIZIA NUOVA PARTITA");
		partita.addActionListener(this);
		partita.setActionCommand("partita");
		
		esci = new JButton("ESCI");
		esci.addActionListener(this);
		esci.setActionCommand("esci");
		
		Container cont = FinePartita.getContentPane();
		cont.setLayout(new FlowLayout());
		cont.add(p1);
		cont.add(p2);
		
		p1.setLayout(new GridLayout(2,1));
		p1.add(label);
		p1.add(vittoria);

		p2.setLayout(new FlowLayout());
		p2.add(partita);
		p2.add(esci);
		
		FinePartita.setSize(400, 200);
		FinePartita.setLocationRelativeTo(null);
		FinePartita.setAlwaysOnTop(true);
		FinePartita.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		FinePartita.setResizable(false);
		FinePartita.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		
		String scelta = e.getActionCommand();
		Fine(scelta);
	}
	
	public void Fine(String scelta){

		if(scelta.equals("partita")){
			FinePartita.dispose();
			Frame.dispose();
			clip.stop();
			new NuovaPartita();
		}

		if(scelta.equals("esci"))
			System.exit(0);
	}
}	
