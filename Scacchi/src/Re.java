import java.awt.*;

/** 
 * Classe che descrive il Re estendendo la classe Pezzo 
 */
public class Re extends Pezzo{

	public Re(String colore,Image img, PosizioneCaselle pos){

		super("Re", colore, img, pos);
	}	
	
	public PosizioneCaselle[] MossePermesse(PosizioneCaselle pos[][]){
		
		int i = 0;
		
		int CoordX = posizione.CoordX;
		int CoordY = posizione.CoordY;	
		
		if (CoordX != 0 && CoordY != 0 && pos[CoordX - 1][CoordY - 1].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX - 1][CoordY - 1]; //Su-Sinistra
		
		if (CoordY != 0 && pos[CoordX][CoordY - 1].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX][CoordY - 1]; //Su
		
		if (CoordX != 7 && CoordY != 0 && pos[CoordX + 1][CoordY - 1].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX + 1][CoordY - 1]; //Su-Destra
		
		if (CoordX != 0 && pos[CoordX - 1][CoordY].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX - 1][CoordY]; //Sinistra
		
		if (CoordX != 7 && pos[CoordX + 1][CoordY].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX + 1][CoordY]; //Destra
		
		if (CoordX != 0 && CoordY != 7 && pos[CoordX - 1][CoordY + 1].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX - 1][CoordY + 1]; //Giu-Sinistra
		
		if (CoordY != 7 && pos[CoordX][CoordY + 1].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX][CoordY + 1]; //Giu
		
		if (CoordX != 7 && CoordY != 7 && pos[CoordX + 1][CoordY + 1].CasellaOccupata != colore)
			PosizionePermessa[i++] = pos[CoordX + 1][CoordY + 1]; //Giu-Destra
		
		PosizionePermessa[i] = null;
		
		return PosizionePermessa;
	}
	
	public PosizioneCaselle[] MossePermesseMangia(PosizioneCaselle pos[][]){ 
		
		return null;
	}
}