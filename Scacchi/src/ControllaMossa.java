import java.awt.*;
import java.util.*;

import javax.swing.*;

public class ControllaMossa extends JPanel{
	
	private ControllaMossa Mossa;
	private PosizioneCaselle pos[][];
	private PosizioneCaselle MossePermesse[];
	private Pezzo pBianco[];
	private Pezzo pNero[];
	private JFrame Frame;
	private int TurnoFinale;
	private Pezzo ReAvv;
	private String coloreAvv;
	private String MossaEff = "Nessuna mossa effettuata";
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Dimensioni schermo
	private int width = (int) screenSize.getWidth(); //Larghezza schermo
	
	public ControllaMossa(JFrame Frame, PosizioneCaselle pos[][], Pezzo pBianco[], Pezzo pNero[]){
		
		this.Frame = Frame;
		this.pos = pos;
		this.pBianco = pBianco;
		this.pNero = pNero;
		
		setBackground(new Color(244, 72, 12));
	}
	
	/**
	 * Studia l'avanzamento di un pezzo sulla scacchiera
	 */
	public int Sposta(Pezzo ProxPezzo, PosizioneCaselle posIniziale, PosizioneCaselle posFinale, int TurnoIniziale, boolean promozione){
		
		Pezzo Pezzi[];
		Pezzo PezziAvv[];
		boolean Scacco;
		
		if (!promozione)
			TurnoFinale = Controlla(ProxPezzo, posIniziale, posFinale, TurnoIniziale, false);
		else{
			if (TurnoIniziale == 0)
				TurnoFinale = 1;
			else 
				TurnoFinale = 0;
		}
		
		if (TurnoFinale == 0){
			coloreAvv = "bianco";
			ReAvv = pBianco[0];
			PezziAvv = pBianco;
			Pezzi = pNero;
		}else{
			coloreAvv = "nero";
			ReAvv = pNero[0];
			PezziAvv = pNero;
			Pezzi = pBianco;	
		}
		
		if (TurnoIniziale != TurnoFinale){
			// Controlla se la mossa provoca Scacco al Re avversario
			Scacco = VerificaScaccoAlRe(Pezzi, ReAvv.getPos());
			if (Scacco){
				ImageIcon icona = new ImageIcon(ReAvv.immagine);
				JOptionPane.showMessageDialog(null, "SCACCO AL RE " + coloreAvv.toUpperCase(), "ATTENZIONE", JOptionPane.INFORMATION_MESSAGE, icona); 
				// Controlla se è Scacco Matto
				if (VerificaScaccoMatto(PezziAvv, TurnoFinale))
					new FinePartita(Frame, coloreAvv, "ScaccoMatto");
			}
			// Verifica la situazione di Stallo e di partita Patta
			else if (!Scacco){
				if (VerificaPatta(Pezzi, PezziAvv, TurnoFinale))
					new FinePartita(Frame, coloreAvv, "Patta");
			}
		}	
		return TurnoFinale;
	}
	
	/**
	 * Controlla la validità della mossa effettuata ed eventualmente lo stato di scacco o la promozione di un pedone
	 */
	public int Controlla(Pezzo ProxPezzo, PosizioneCaselle posIniziale, PosizioneCaselle posFinale, int Turno, boolean ControlloScacco){
		
		ProxPezzo.setPos(posIniziale);
		MossePermesse = ProxPezzo.MossePermesse(pos);
	
		Pezzo Pezzi[];
		Pezzo PezziAvv[];
		boolean posTrovata = false;
		
		if (Turno == 0){
			coloreAvv = "nero";
			ReAvv = pNero[0];
			PezziAvv = pNero;
			Pezzi = pBianco;
		}else{
			coloreAvv = "bianco";
			ReAvv = pBianco[0];
			PezziAvv = pBianco;
			Pezzi = pNero;
		}
		
		for (int i=0; MossePermesse[i] != null; i++){
			if (MossePermesse[i] == posFinale && MossePermesse[i] != pBianco[0].getPos() && MossePermesse[i] != pNero[0].getPos()){
				String occupata = posFinale.CasellaOccupata;
				ProxPezzo.setPos(posFinale);
				int p = 0;
				for (int j=1; j<PezziAvv.length; j++)
					if (PezziAvv[j].getPos() == ProxPezzo.getPos() && !PezziAvv[j].mangiato){
						if (!ControlloScacco) 
							PezziAvv[j].mangiato = true;
						p = j;
					}
				posTrovata = true;
				// Controlla se la mossa lascia il proprio Re sotto Scacco 
				if (VerificaScaccoAlRe(PezziAvv, Pezzi[0].getPos())){
					posTrovata = false;
					ProxPezzo.setPos(posIniziale);
					posFinale.CasellaOccupata = occupata;
					PezziAvv[p].mangiato = false;
					if (!ControlloScacco)
						JOptionPane.showMessageDialog(null, "MOSSA IMPOSSIBILE: RE " + Pezzi[0].colore.toUpperCase() + " SOTTO SCACCO", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
				}	
			}
		}		
		
		// Controlla se il pedone mosso è da promuovere
		if (posTrovata == true && ProxPezzo.nome == "Pedone" && (ProxPezzo.getPos().CoordY == 0 || ProxPezzo.getPos().CoordY == 7))
			new Promozione(Frame, ProxPezzo, pBianco, pNero, Mossa, Turno);
		
		if (posTrovata == true){
			if (Turno == 0)
				Turno = 1;
			else 
				Turno = 0;
			if (!ControlloScacco)
				MossaEff = ProxPezzo.nome + ": " + posIniziale.coordXY() + " - " + posFinale.coordXY();
		}
		return Turno;
	}
	
	/**
	 * Aggiorna il pannello per il turno dei pezzi e comunica l'ultima mossa effettuata
	 */
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		
		String TurnoPezzo;
		if (TurnoFinale == 0){
			TurnoPezzo = "Bianchi";
			g.setColor(Color.white);
		}else{
			TurnoPezzo = "Neri";
			g.setColor(Color.black);
		}
		
		String s = "Tocca ai " + TurnoPezzo;
		g.setFont(new Font("Garamond", Font.BOLD, 30));	
		g.drawString(s, ((width-740)/4)-50, 60);
		
		if (TurnoFinale == 0)
			g.setColor(Color.black);
		else
			g.setColor(Color.white);
		g.setFont(new Font("Garamond", Font.BOLD, 20));	
		g.drawString(MossaEff, (((width-740)/4)*2)+50, 60);
		repaint();
	}	
	
	/**
	 * Verifica lo Scacco al Re 
	 */
	public boolean VerificaScaccoAlRe(Pezzo[] Pezzi, PosizioneCaselle posRe){
		
		for (int i=0; i<Pezzi.length; i++){
			if (Pezzi[i].nome == "Pedone" && !Pezzi[i].mangiato){
				PosizioneCaselle MossePermessePezzi[] = Pezzi[i].MossePermesseMangia(pos);
				for (int j=0; MossePermessePezzi[j] != null; j++)
					if (MossePermessePezzi[j] == posRe)
						return true;
			}else if (!Pezzi[i].mangiato){
				PosizioneCaselle MossePermessePezzi[] = Pezzi[i].MossePermesse(pos);
				for (int j=0; MossePermessePezzi[j] != null; j++)
					if (MossePermessePezzi[j] == posRe)
						return true;
			}
		}
		return false;
	}
	
	/**
	 * Verifica lo stato di Stallo e di partita Patta
	 */
	public boolean VerificaPatta(Pezzo[] PezziAvv, Pezzo[] Pezzi, int Turno){

		ArrayList<Pezzo> PezziRimasti = new ArrayList<Pezzo>();
		ArrayList<Pezzo> PezziAvvRimasti = new ArrayList<Pezzo>();
		for (int i=1; i<Pezzi.length; i++){
			if (!Pezzi[i].mangiato)
				PezziRimasti.add(Pezzi[i]);
			if (!PezziAvv[i].mangiato)				
				PezziAvvRimasti.add(PezziAvv[i]);	
		}	 
		//Re contro Re e Alfiere o Re e Cavallo
		if (PezziAvvRimasti.size() == 0 && PezziRimasti.size() == 1 && (PezziRimasti.get(0).nome == "Alfiere" || PezziRimasti.get(0).nome == "Cavallo")) 
			return true;
		//Re contro Re
		else if (PezziRimasti.size() == 0 && PezziAvvRimasti.size() == 0) 
			return true;
		//Stallo (nessuna mossa possibile)
		else if (VerificaScaccoMatto(Pezzi, Turno))
			return true;

		return false;
	}

	/**
	 * Verifica lo Scacco Matto al Re
	 */
	public boolean VerificaScaccoMatto(Pezzo[] Pezzi, int Turno){
	
		boolean ScaccoMatto = true;
		for (int i=0; i<Pezzi.length && ScaccoMatto; i++){
			if (!Pezzi[i].mangiato){
				PosizioneCaselle[] MossePermessePezzi = Pezzi[i].MossePermesse(pos);
				PosizioneCaselle posPezzo = Pezzi[i].getPos();
				for (int j=0; MossePermessePezzi[j] != null; j++){
					String occupata = MossePermessePezzi[j].CasellaOccupata;
					int TurnoFinale = Controlla(Pezzi[i], posPezzo, MossePermessePezzi[j], Turno, true);
					if(TurnoFinale != Turno)
						ScaccoMatto = false;
					Pezzi[i].setPos(posPezzo); 
					MossePermessePezzi[j].CasellaOccupata = occupata;
				}
			}
		}	
		return ScaccoMatto;
	}
	
	public void setPointer(ControllaMossa Mossa){
		this.Mossa = Mossa;
	}
}
