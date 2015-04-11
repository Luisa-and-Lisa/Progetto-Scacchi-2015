import java.awt.*;

/**
 * Classe che descrive ogni Pezzo degli scacchi
 */
public abstract class Pezzo {

	protected String nome;
	protected String colore; 
	protected Image immagine;
	protected boolean promosso = false;
	protected PosizioneCaselle posizione; 
	protected PosizioneCaselle[] PosizionePermessa = new PosizioneCaselle[28]; //al massimo 27 posizioni possibili (regina)
	protected boolean mangiato = false;
	
	public Pezzo(String nome, String colore, Image immagine, PosizioneCaselle posizione){
		
		this.nome = nome;
		this.colore = colore;
		this.immagine = immagine;
		this.posizione = posizione;
		this.posizione.CasellaOccupata = colore;
	}
	
	public PosizioneCaselle getPos(){

		return posizione;
	}
	
	/** 
	 * Stringa vuota: la casella e' libera
	 * Colore uguale a "nero": la casella e' occupata da un pezzo nero
	 * Colore uguale a "bianco": la casella e' occupata da un pezzo bianco
	 */
	public void setPos(PosizioneCaselle pos){
		
		posizione.CasellaOccupata = "";
		posizione = pos;
		posizione.CasellaOccupata = colore; 
	}
	
	/**
	 * Metodo usato per ottenere tutte le mosse permesse di un pezzo situato in una certa posizione iniziale
	 */
	public abstract PosizioneCaselle[] MossePermesse(PosizioneCaselle pos[][]);
	
	/**
	 * Metodo usato per i pedoni, dato che solo loro mangiano in modo diverso da come muovono
	 */
	public abstract PosizioneCaselle[] MossePermesseMangia(PosizioneCaselle pos[][]);
}
