import java.awt.*;

/** 
 * Classe che descrive l'Alfiere estendendo la classe Pezzo 
 */
public class Alfiere extends Pezzo{

	public Alfiere(String colore,Image img, PosizioneCaselle pos){

		super("Alfiere", colore, img, pos);
	}	
	
	public PosizioneCaselle[] MossePermesse(PosizioneCaselle pos[][]){
		
		int i = 0;
		
		// Direzione Su-Sinistra
		int CoordX = posizione.CoordX;
		int CoordY = posizione.CoordY;						

		do{
			CoordX = CoordX - 1;
			CoordY = CoordY - 1;
			if(CoordX >= 0 && CoordY >= 0 && pos[CoordX][CoordY].CasellaOccupata != colore) 
				PosizionePermessa[i++] = pos[CoordX][CoordY];
		}while (CoordX > 0 && CoordY > 0 && pos[CoordX][CoordY].CasellaOccupata != colore && pos[CoordX][CoordY].CasellaOccupata == "");
		
		// Direzione Su-Destra
		CoordX = posizione.CoordX;
		CoordY = posizione.CoordY;	

		do{
			CoordX = CoordX + 1;
			CoordY = CoordY - 1;
			if(CoordX <= 7 && CoordY >= 0 && pos[CoordX][CoordY].CasellaOccupata != colore) 
				PosizionePermessa[i++] = pos[CoordX][CoordY];
		}while (CoordX < 7 && CoordY > 0 && pos[CoordX][CoordY].CasellaOccupata != colore && pos[CoordX][CoordY].CasellaOccupata == "");
		
		// Direzione Giu-Sinistra
		CoordX = posizione.CoordX;
		CoordY = posizione.CoordY;	

		do{
			CoordX  = CoordX - 1;
			CoordY = CoordY + 1;
			if(CoordX >= 0 && CoordY <= 7 && pos[CoordX][CoordY].CasellaOccupata != colore) 
				PosizionePermessa[i++] = pos[CoordX][CoordY];
		}while (CoordX > 0 && CoordY < 7 && pos[CoordX][CoordY].CasellaOccupata != colore && pos[CoordX][CoordY].CasellaOccupata == "");
		
		// Direzione Giu-Destra
		CoordX = posizione.CoordX;
		CoordY = posizione.CoordY;	

		do{
			CoordX  = CoordX + 1;
			CoordY = CoordY + 1;
			if(CoordX <= 7 && CoordY <= 7 && pos[CoordX][CoordY].CasellaOccupata != colore) 
				PosizionePermessa[i++] = pos[CoordX][CoordY];
		}while (CoordX < 7 && CoordY < 7 && pos[CoordX][CoordY].CasellaOccupata != colore && pos[CoordX][CoordY].CasellaOccupata == "");
		
		PosizionePermessa[i] = null;
		
		return PosizionePermessa;
	}
	
	public PosizioneCaselle[] MossePermesseMangia(PosizioneCaselle pos[][]){ 
		
		return null;
	}
}