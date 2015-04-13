import static org.junit.Assert.*;
import java.awt.*;
import javax.swing.*;
import org.junit.*;

/**
 * Classe Test su ControllaMossa.java
 */ 
public class ControllaMossaTest {

	private PosizioneCaselle pos[][] = new PosizioneCaselle[8][8];
	private Image imgN[] = new Image[6];
	private Image imgB[] = new Image[6];
	private JFrame Frame;
	
	@Before
	public void setUp() throws Exception {
		//Crea le posizioni
		for(int j=0; j<8; j++)
			for(int i=0; i<8; i++)
				pos[i][j] = new PosizioneCaselle(i,j);
		
		Toolkit tk= Toolkit.getDefaultToolkit();
		//Immagini dei pezzi neri
		imgN[0] = tk.getImage("../Scacchi/src/img/NRe.png");
		imgN[1] = tk.getImage("../Scacchi/src/img/NTorre.png");
		imgN[2] = tk.getImage("../Scacchi/src/img/NCavallo.png");
		imgN[3] = tk.getImage("../Scacchi/src/img/NAlfiere.png");
		imgN[4] = tk.getImage("../Scacchi/src/img/NRegina.png");
		imgN[5] = tk.getImage("../Scacchi/src/img/NPedone.png");
		
		//Immagini dei pezzi bianchi
		imgB[0] = tk.getImage("../Scacchi/src/img/BRe.png"); 
		imgB[1] = tk.getImage("../Scacchi/src/img/BTorre.png");
		imgB[2] = tk.getImage("../Scacchi/src/img/BCavallo.png");
		imgB[3] = tk.getImage("../Scacchi/src/img/BAlfiere.png");
		imgB[4] = tk.getImage("../Scacchi/src/img/BRegina.png");
		imgB[5] = tk.getImage("../Scacchi/src/img/BPedone.png");
	}
	
	@Test
	//Controlla la mossa del Re e lo Scacco
	public void test1_Controlla() {
	
		Pezzo pBianco[] = new Pezzo[2];
		Pezzo pNero[] = new Pezzo[1];
				
		pNero[0] = new Re ("nero", imgN[0], pos[3][4]);

		pBianco[0] = new Re ("bianco", imgB[0], pos[4][7]);
		pBianco[1] = new Regina ("bianco", imgB[4], pos[3][7]);
		
		ControllaMossa mossa = new ControllaMossa(Frame, pos, pBianco, pNero);
		int Turno = 1;
		//Mossa impossibile -> Re nero contro Regina bianca, rimane il turno dei neri (1)
		assertEquals(1, mossa.Controlla(pNero[0], pNero[0].getPos(), pos[3][5], Turno, true)); 
		//Scacco al Re nero dalla Regina bianca
		assertTrue(mossa.VerificaScaccoAlRe(pBianco, pNero[0].getPos())); 
	}
	
	@Test
	//Controlla lo Scacco Matto
	public void test2_Controlla() {
		
		Pezzo pBianco[] = new Pezzo[2];
		Pezzo pNero[] = new Pezzo[1];

		pNero[0] = new Re ("nero", imgN[0], pos[4][0]);

		pBianco[0] = new Re ("bianco", imgB[0], pos[5][2]);
		pBianco[1] = new Regina ("bianco", imgB[4], pos[4][1]);
		
		ControllaMossa mossa = new ControllaMossa(Frame, pos, pBianco, pNero);
		int Turno = 1;
		//Scacco Matto al Re nero
		assertTrue(mossa.VerificaScaccoMatto(pNero, Turno)); 
	}
	
	@Test
	//Controlla la Patta
	public void test3_Controlla() {
		
		Pezzo pBianco[] = new Pezzo[2];
		Pezzo pNero[] = new Pezzo[1];

		pNero[0] = new Re ("nero", imgN[0], pos[7][0]);

		pBianco[0] = new Re ("bianco", imgB[0], pos[4][7]);
		pBianco[1] = new Regina ("bianco", imgB[4], pos[5][1]);
		
		ControllaMossa mossa = new ControllaMossa(Frame, pos, pBianco, pNero);
		int Turno = 1;
		//Patta -> tocca ai neri ma il Re non può muoversi 
		assertTrue(mossa.VerificaPatta(pBianco, pNero, Turno)); 
	}
	
	@Test
	//Controlla la Patta
	public void test4_Controlla() {
		
		Pezzo pBianco[] = new Pezzo[1];
		Pezzo pNero[] = new Pezzo[1];

		pNero[0] = new Re ("nero", imgN[0], pos[4][0]);

		pBianco[0] = new Re ("bianco", imgB[0], pos[4][7]);
		
		ControllaMossa mossa = new ControllaMossa(Frame, pos, pBianco, pNero);
		int Turno = 0;
		//Patta -> Re bianco contro Re nero 
		assertTrue(mossa.VerificaPatta(pBianco, pNero, Turno)); 
	}
	
	@Test
	//Verifica se la mossa del Pedone è consentita
	public void test5_Controlla() {
		
		Pezzo pBianco[] = new Pezzo[2];
		Pezzo pNero[] = new Pezzo[3];
		
		pNero[0] = new Re ("nero", imgN[0], pos[4][0]);
		pNero[1] = new Pedone ("nero", imgN[5], pos[3][5]);
		pNero[2] = new Pedone ("nero", imgN[5], pos[2][5]);
		
		pBianco[0] = new Re ("bianco", imgB[0], pos[4][7]);
		pBianco[1] = new Pedone ("bianco", imgB[5], pos[2][6]);
		
		ControllaMossa mossa = new ControllaMossa(Frame, pos, pBianco, pNero);
		int Turno = 0;
		//Pedone bianco mangia Pedone nero
		assertEquals(1, mossa.Controlla(pBianco[1], pBianco[1].getPos(), pos[3][5], Turno, false)); 
		//Mossa impossibile Pedone bianco
		assertEquals(0, mossa.Controlla(pBianco[1], pBianco[1].getPos(), pos[2][5], Turno, false)); 
	}
}
