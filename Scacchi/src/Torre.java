import java.awt.*;

/** 
 * Classe che descrive la Torre estendendo la classe Pezzo 
 */
public class Torre extends Pezzo{

	public Torre(String colore,Image img, PosizioneCaselle pos){

		super("Torre", colore, img, pos);
	}
	
	public PosizioneCaselle[] MossePermesse(PosizioneCaselle pos[][]){
		
		int i = 0;
		
		// Direzione Su
		int CoordX = posizione.CoordX;
		int CoordY = posizione.CoordY;

		do{
			CoordY = CoordY - 1;
			if(CoordY >= 0 && pos[CoordX][CoordY].CasellaOccupata != colore) 
				PosizionePermessa[i++] = pos[CoordX][CoordY];
		}while (CoordY > 0 && pos[CoordX][CoordY].CasellaOccupata != colore && pos[CoordX][CoordY].CasellaOccupata == "");
		
		// Direzione Giu
		CoordX = posizione.CoordX;
		CoordY = posizione.CoordY;

		do{
			CoordY = CoordY + 1;
			if(CoordY <= 7 && pos[CoordX][CoordY].CasellaOccupata != colore) 
				PosizionePermessa[i++] = pos[CoordX][CoordY];
		}while (CoordY < 7 && pos[CoordX][CoordY].CasellaOccupata != colore && pos[CoordX][CoordY].CasellaOccupata == "");
		
		// Direzione Destra
		CoordX = posizione.CoordX;
		CoordY = posizione.CoordY;

		do{
			CoordX  = CoordX + 1;
			if(CoordX <= 7 && pos[CoordX][CoordY].CasellaOccupata != colore) 
				PosizionePermessa[i++] = pos[CoordX][CoordY];
		}while (CoordX < 7 && pos[CoordX][CoordY].CasellaOccupata != colore && pos[CoordX][CoordY].CasellaOccupata == "");
		
		// Direzione Sinistra
		CoordX = posizione.CoordX;
		CoordY = posizione.CoordY;

		do{
			CoordX  = CoordX - 1;
			if(CoordX >= 0 && pos[CoordX][CoordY].CasellaOccupata != colore) 
				PosizionePermessa[i++] = pos[CoordX][CoordY];
		}while (CoordX > 0 && pos[CoordX][CoordY].CasellaOccupata != colore && pos[CoordX][CoordY].CasellaOccupata == "");
		
		PosizionePermessa[i] = null;
		
		return PosizionePermessa;
	}
	
	public PosizioneCaselle[] MossePermesseMangia(PosizioneCaselle pos[][]){ 
		
		return null;
	}
}
