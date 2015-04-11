import java.awt.*;

/** 
 * Classe che descrive il Cavallo estendendo la classe Pezzo 
 */
public class Cavallo extends Pezzo{

	public Cavallo(String colore,Image img, PosizioneCaselle pos){

		super("Cavallo", colore, img, pos);
	}	
	
	public PosizioneCaselle[] MossePermesse(PosizioneCaselle pos[][]){
		
		int i = 0;
		
		// Direzione Su-Sinistra
		int CoordX = posizione.CoordX - 1;
		int CoordY = posizione.CoordY - 2;
		
		if (CoordX >= 0 && CoordY >= 0 && pos[CoordX][CoordY].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX][CoordY];
		
		// Direzione Su-Destra
		CoordX = posizione.CoordX + 1;
		CoordY = posizione.CoordY - 2;
		
		if (CoordX <= 7 && CoordY >= 0 && pos[CoordX][CoordY].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX][CoordY];
		
		// Direzione Giu-Sinistra
		CoordX = posizione.CoordX - 1;
		CoordY = posizione.CoordY + 2;
		
		if (CoordX >= 0 && CoordY <= 7 && pos[CoordX][CoordY].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX][CoordY];

		// Direzione Giu-Destra
		CoordX = posizione.CoordX + 1;
		CoordY = posizione.CoordY + 2;

		if (CoordX <= 7 && CoordY <= 7 && pos[CoordX][CoordY].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX][CoordY];
	
		// Direzione Sinistra-Giu
		CoordX = posizione.CoordX - 2;
		CoordY = posizione.CoordY + 1;

		if (CoordX >= 0 && CoordY <= 7 && pos[CoordX][CoordY].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX][CoordY];
		
		// Direzione Sinistra-Su
		CoordX = posizione.CoordX - 2;
		CoordY = posizione.CoordY - 1;
		
		if (CoordX >= 0 && CoordY >= 0 && pos[CoordX][CoordY].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX][CoordY];
		
		// Direzione Destra-Giu
		CoordX = posizione.CoordX + 2;
		CoordY = posizione.CoordY + 1;

		if (CoordX <= 7 && CoordY <= 7 && pos[CoordX][CoordY].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX][CoordY];
		
		// Direzione Destra-Su
		CoordX = posizione.CoordX + 2;
		CoordY = posizione.CoordY - 1;
		
		if (CoordX <= 7 && CoordY >= 0 && pos[CoordX][CoordY].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX][CoordY];
			
		PosizionePermessa[i] = null;
		
		return PosizionePermessa;
	}
	
	public PosizioneCaselle[] MossePermesseMangia(PosizioneCaselle pos[][]){ 
		
		return null;
	}
}