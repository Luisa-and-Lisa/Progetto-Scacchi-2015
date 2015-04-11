import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Classe per la promozione di un pedone
 */
public class Promozione implements ActionListener{
	
	private Pezzo Pezzo;
	private Pezzo PromPezzo[];
	private Pezzo pBianco[];
	private Pezzo pNero[];
	private int Turno;
	private ControllaMossa Mossa;
	private int pos;
	private JFrame Frame;
	private JFrame Promozione;
	private JButton Torre, Cavallo, Alfiere, Regina;
	private Image imgTorre, imgCavallo, imgAlfiere, imgRegina;
	
	public Promozione(JFrame Frame, Pezzo Pezzo, Pezzo pBianco[], Pezzo pNero[], ControllaMossa Mossa, int Turno){

		this.Frame = Frame;
		this.Pezzo = Pezzo;
		this.pBianco = pBianco;
		this.pNero = pNero;
		this.Mossa = Mossa;
		this.Turno = Turno;

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		Promozione = new JFrame ("Promozione");
		
		JLabel label = new JLabel("Scegli un pezzo con cui promuovere il pedone");
		label.setFont(new Font("Garamond", Font.PLAIN, 20));
		
		Container cont = Promozione.getContentPane();
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		if(Pezzo.colore == "bianco"){
			imgTorre = tk.getImage("../Scacchi/src/img/BTorre.png");
			imgCavallo = tk.getImage("../Scacchi/src/img/BCavallo.png");
			imgAlfiere = tk.getImage("../Scacchi/src/img/BAlfiere.png");
			imgRegina = tk.getImage("../Scacchi/src/img/BRegina.png");
			PromPezzo = pBianco;
		}else if(Pezzo.colore == "nero"){
			imgTorre = tk.getImage("../Scacchi/src/img/NTorre.png");
			imgCavallo = tk.getImage("../Scacchi/src/img/NCavallo.png");
			imgAlfiere = tk.getImage("../Scacchi/src/img/NAlfiere.png");
			imgRegina = tk.getImage("../Scacchi/src/img/NRegina.png");
			PromPezzo = pNero;
		}
		
		Torre = new JButton(new ImageIcon(imgTorre));
		Torre.addActionListener(this);
		Torre.setActionCommand("Torre");
		
		Cavallo = new JButton(new ImageIcon(imgCavallo));
		Cavallo.addActionListener(this);
		Cavallo.setActionCommand("Cavallo");
		
		Alfiere = new JButton(new ImageIcon(imgAlfiere));
		Alfiere.addActionListener(this);
		Alfiere.setActionCommand("Alfiere");
		
		Regina = new JButton(new ImageIcon(imgRegina));
		Regina.addActionListener(this);
		Regina.setActionCommand("Regina");		
		
		for(int i=8; i<16; i++)
			if(Pezzo == PromPezzo[i]){
				pos = i;
				break;
			}	
		
		cont.setLayout(new FlowLayout());
		cont.add(p1);
		cont.add(p2);
		
		p1.setLayout(new FlowLayout());
		p1.add(label);
		
		p2.setLayout(new GridLayout(1,4,10,10));
		p2.add(Torre);
		p2.add(Cavallo);
		p2.add(Alfiere);
		p2.add(Regina);
		
		Promozione.setSize(450, 150);
		Promozione.setLocationRelativeTo(null);
		Promozione.setAlwaysOnTop(true);
		Promozione.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Promozione.setResizable(false);
		Promozione.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
		String nomePezzo = e.getActionCommand();
		Promuovi(nomePezzo);
	}
	
	public void Promuovi(String scelta){

		if(scelta.equals("Torre")){
			PromPezzo[pos] = new Torre(PromPezzo[pos].colore, PromPezzo[1].immagine, PromPezzo[pos].getPos());
			PromPezzo[pos].promosso = true;
		}

		if(scelta.equals("Cavallo")){
			PromPezzo[pos] = new Cavallo(PromPezzo[pos].colore, PromPezzo[2].immagine, PromPezzo[pos].getPos());
			PromPezzo[pos].promosso = true;
		}

		if(scelta.equals("Alfiere")){
			PromPezzo[pos] = new Alfiere(PromPezzo[pos].colore, PromPezzo[3].immagine, PromPezzo[pos].getPos());
			PromPezzo[pos].promosso = true;
		}

		if(scelta.equals("Regina")){
			PromPezzo[pos] = new Regina(PromPezzo[pos].colore, PromPezzo[4].immagine, PromPezzo[pos].getPos());
			PromPezzo[pos].promosso = true;
		}

		Promozione.dispose();
		
		Mossa.Sposta(PromPezzo[pos], PromPezzo[pos].getPos(), PromPezzo[pos].getPos(), Turno, true);
		
		Frame.repaint();
	}
}	
