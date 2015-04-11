import java.awt.*;

/** 
 * Classe che descrive il Pedone estendendo la classe Pezzo 
 */
public class Pedone extends Pezzo{

	public Pedone(String colore,Image img, PosizioneCaselle pos){

		super("Pedone", colore, img, pos);
	}	
	
	public PosizioneCaselle[] MossePermesse(PosizioneCaselle pos[][]){
		
		int i = 0;
		
		int CoordX = posizione.CoordX;
		int CoordY = posizione.CoordY;
		
		// PEDONI BIANCHI
		if (colore == "bianco" && CoordY != 0 && pos[CoordX][CoordY - 1].CasellaOccupata == "" && pos[CoordX][CoordY - 1].CasellaOccupata != colore) // Direzione Su
			PosizionePermessa[i++] = pos[CoordX][CoordY - 1];
		
		if (colore == "bianco" && CoordX != 0 && CoordY != 0 && pos[CoordX - 1][CoordY - 1].CasellaOccupata != "" && pos[CoordX - 1][CoordY - 1].CasellaOccupata != colore) // Direzione Su-Sinistra
			PosizionePermessa[i++] = pos[CoordX - 1][CoordY - 1];
		
		if (colore == "bianco" && CoordX != 7 && CoordY != 0 && pos[CoordX + 1][CoordY - 1].CasellaOccupata != "" && pos[CoordX + 1][CoordY - 1].CasellaOccupata != colore) // Direzione Su-Destra
			PosizionePermessa[i++] = pos[CoordX + 1][CoordY - 1];
		
		// PEDONI NERI
		if (colore == "nero" && CoordY != 7 && pos[CoordX][CoordY + 1].CasellaOccupata == "" && pos[CoordX][CoordY + 1].CasellaOccupata != colore) // Direzione giu
			PosizionePermessa[i++] = pos[CoordX][CoordY + 1];	 
		
		if (colore == "nero" && CoordX != 0 && CoordY != 7 && pos[CoordX - 1][CoordY + 1].CasellaOccupata != "" && pos[CoordX - 1][CoordY + 1].CasellaOccupata != colore) // Direzione Giu-Sinistra
			PosizionePermessa[i++] = pos[CoordX - 1][CoordY + 1];
		
		if (colore == "nero" && CoordX != 7 && CoordY != 7 && pos[CoordX + 1][CoordY + 1].CasellaOccupata != "" && pos[CoordX + 1][CoordY + 1].CasellaOccupata != colore) // Direzione Giu-Destra
			PosizionePermessa[i++] = pos[CoordX + 1][CoordY + 1];
		
		PosizionePermessa[i] = null;
		
		return PosizionePermessa;
	}
	
	public PosizioneCaselle[] MossePermesseMangia(PosizioneCaselle pos[][]){
		
		int i = 0;
		
		int CoordX = posizione.CoordX;
		int CoordY = posizione.CoordY;
		
		// PEDONI BIANCHI
		if (colore == "bianco" && CoordX != 0 && CoordY != 0 && pos[CoordX - 1][CoordY - 1].CasellaOccupata != colore) // Direzione Su-Sinistra
			PosizionePermessa[i++] = pos[CoordX - 1][CoordY - 1];
		
		if (colore == "bianco" && CoordX != 7 && CoordY != 0 && pos[CoordX + 1][CoordY - 1].CasellaOccupata != colore) // Direzione Su-Destra
			PosizionePermessa[i++] = pos[CoordX + 1][CoordY - 1];
		
		// PEDONI NERI
		if (colore == "nero" && CoordX != 0 && CoordY != 7 && pos[CoordX - 1][CoordY + 1].CasellaOccupata != colore) // Direzione Giu-Sinistra
			PosizionePermessa[i++] = pos[CoordX - 1][CoordY + 1];
		
		if (colore == "nero" && CoordX != 7 && CoordY != 7 && pos[CoordX + 1][CoordY + 1].CasellaOccupata != colore) // Direzione Giu-Destra
			PosizionePermessa[i++] = pos[CoordX + 1][CoordY + 1]; 
		
		PosizionePermessa[i] = null;
		
		return PosizionePermessa;
	}
}