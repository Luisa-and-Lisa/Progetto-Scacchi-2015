import java.awt.Image;

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
	 * Stringa vuota: la casella è libera
	 * Colore uguale a "nero": la casella è occupata da un pezzo nero
	 * Colore uguale a "bianco": la casella è occupata da un pezzo bianco
	 */
	public void setPos(PosizioneCaselle pos){
		
		posizione.CasellaOccupata = "";
		posizione = pos;
		posizione.CasellaOccupata = colore; 
	}
	
	public abstract PosizioneCaselle[] MossePermesse(PosizioneCaselle pos[][]);
	
	public abstract PosizioneCaselle[] MossePermesseMangia(PosizioneCaselle pos[][]);
}
