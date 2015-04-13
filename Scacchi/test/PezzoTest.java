import static org.junit.Assert.*;
import java.awt.*;
import org.junit.*;

/**
 * Classe Test su Pezzo.java
 */ 
public class PezzoTest {
	
	protected PosizioneCaselle[] PosizionePermessa = new PosizioneCaselle[28]; //al massimo 8 posizioni possibili
	private PosizioneCaselle pos[][] = new PosizioneCaselle[8][8];
	
	@Before
	public void setUp() throws Exception {
		//Crea le posizioni
		for(int j=0; j<8; j++)
			for(int i=0; i<8; i++)
				pos[i][j] = new PosizioneCaselle(i,j);
	}	
	
	@Test
	//Controlla le Mosse Permesse del Re
	public void test1_MossePermesseRe() {
		
		Pezzo ReNero;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imgNRe = tk.getImage("../Scacchi/src/img/NRe.png");
		ReNero = new Re ("nero", imgNRe, pos[4][0]);
		
		PosizionePermessa[0] = pos[3][0]; //Sinistra
		PosizionePermessa[1] = pos[5][0]; //Destra
		PosizionePermessa[2] = pos[3][1]; //Giu-Sinistra
		PosizionePermessa[3] = pos[4][1]; //Giu
		PosizionePermessa[4] = pos[5][1]; //Giu-Destra
		
		assertArrayEquals(PosizionePermessa, ReNero.MossePermesse(pos));
	}
	
	@Test
	//Controlla le Mosse Permesse della Torre
	public void test2_MossePermesseTorre() {
		
		Pezzo TorreNera;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imgNTorre = tk.getImage("../Scacchi/src/img/NTorre.png");
		TorreNera = new Torre ("nero", imgNTorre, pos[7][0]);
		Image imgNAlfiere = tk.getImage("../Scacchi/src/img/NAlfiere.png");
		new Alfiere ("nero", imgNAlfiere, pos[5][0]);
		
		PosizionePermessa[0] = pos[7][1]; //Giu
		PosizionePermessa[1] = pos[7][2]; 
		PosizionePermessa[2] = pos[7][3]; 
		PosizionePermessa[3] = pos[7][4]; 
		PosizionePermessa[4] = pos[7][5]; 
		PosizionePermessa[5] = pos[7][6]; 
		PosizionePermessa[6] = pos[7][7];
		PosizionePermessa[7] = pos[6][0]; //Sinistra
		//L'Alfiere nero blocca le mosse permesse dalla Torre nera
		
		assertArrayEquals(PosizionePermessa, TorreNera.MossePermesse(pos));
	}
		
	@Test
	//Controlla le Mosse Permesse del Cavallo
	public void test3_MossePermesseCavallo() {
		
		Pezzo CavalloNero;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imgNCavallo = tk.getImage("../Scacchi/src/img/NCavallo.png");
		CavalloNero = new Cavallo ("nero", imgNCavallo, pos[1][0]);
		//Il Cavallo salta il Pedone
		Image imgBPedone = tk.getImage("../Scacchi/src/img/BPedone.png");
		new Pedone ("bianco", imgBPedone, pos[1][1]);
		
		PosizionePermessa[0] = pos[0][2]; //Giu-Sinistra
		PosizionePermessa[1] = pos[2][2]; //Giu-Destra
		PosizionePermessa[2] = pos[3][1]; //Destra-Giu
		
		assertArrayEquals(PosizionePermessa, CavalloNero.MossePermesse(pos));
	}
	
	@Test
	//Controlla le Mosse Permesse dell'Alfiere
	public void test4_MossePermesseAlfiere() {
	
		Pezzo AlfiereNero;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imgNAlfiere = tk.getImage("../Scacchi/src/img/NAlfiere.png");
		AlfiereNero = new Alfiere ("nero", imgNAlfiere, pos[2][0]);
		Image imgBRegina = tk.getImage("../Scacchi/src/img/BRegina.png");
		new Regina ("bianco", imgBRegina, pos[4][2]);
	
		PosizionePermessa[0] = pos[1][1]; //Giu-Sinistra
		PosizionePermessa[1] = pos[0][2]; 
		PosizionePermessa[2] = pos[3][1]; //Giu-Destra
		PosizionePermessa[3] = pos[4][2];
		//L'Alfiere nero mangia la Regina bianca
		
		assertArrayEquals(PosizionePermessa, AlfiereNero.MossePermesse(pos));
	}
	
	@Test
	//Controlla le Mosse Permesse della Regina
	public void test5_MossePermesseRegina() {
	
		Pezzo ReginaNera;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image imgNRegina = tk.getImage("../Scacchi/src/img/NRegina.png");
		ReginaNera = new Regina ("nero", imgNRegina, pos[0][0]);
		Image imgNPedone = tk.getImage("../Scacchi/src/img/NPedone.png");
		new Pedone ("nero", imgNPedone, pos[0][1]);
		new Pedone ("nero", imgNPedone, pos[1][1]);
		Image imgNCavallo = tk.getImage("../Scacchi/src/img/NCavallo.png");
		new Cavallo ("nero", imgNCavallo, pos[1][0]);

		//Nessuna mossa permessa -> Regina nera bloccata da 2 pedoni neri e da 1 cavallo nero 
		
		assertArrayEquals(PosizionePermessa, ReginaNera.MossePermesse(pos));
	}
}