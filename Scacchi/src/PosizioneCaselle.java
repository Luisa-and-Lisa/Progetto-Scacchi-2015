/**
 * Classe che identifica la posizione di ogni casella sulla scacchiera 
 */ 
public class PosizioneCaselle {

	protected int PixelX; //coordinata X in pixel
	protected int PixelY; //coordinata Y in pixel	
	
	protected int CoordX; //coordinata X della casella
	protected int CoordY; //coordinata Y della casella
	
	protected String CasellaOccupata = "";
	
	public PosizioneCaselle (int x, int y){

		CoordX = x;
		CoordY = y;
		
		PixelX = (int) (x+1)*80 - 40;
		PixelY = (int) (y+1)*80 - 40;
	}
	
	public String coordXY(){

		String x = "";

		switch(CoordX){

			case (0): x = "A"; break;
			case (1): x = "B"; break;
			case (2): x = "C"; break;
			case (3): x = "D"; break;
			case (4): x = "E"; break;
			case (5): x = "F"; break;
			case (6): x = "G"; break;
			case (7): x = "H"; break;
		}

		int y = 8 - CoordY;

		return x + y;
	}
}
