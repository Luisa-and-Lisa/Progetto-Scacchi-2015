import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * Classe che crea una nuova partita
 */
public class NuovaPartita{

	private static Clip clip;
	private Pezzo [] pBianco = new Pezzo[16];
	private Pezzo [] pNero = new Pezzo[16];
	private PosizioneCaselle[][] pos = new PosizioneCaselle[8][8]; //Posizione dei pezzi sulla scacchiera
	
	private JFrame NewScacchi;
	private JMenuBar menu = new JMenuBar();
	private JMenu File = new JMenu("File");
	private JMenuItem NuovaPartita = new JMenuItem("Nuova Partita");
	private JMenuItem Regole = new JMenuItem("Regole Gioco");
	private JMenuItem Informazioni = new JMenuItem("Informazioni");
	private JMenuItem Esci = new JMenuItem("Uscita");

	public NuovaPartita(){
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize(); //Dimensioni schermo
		int width = (int) screenSize.getWidth(); //Larghezza schermo
		
		try {
			File fileSuono = new File("../Scacchi/src/img/River_flows_in_you.wav");
			AudioInputStream audio = AudioSystem.getAudioInputStream(fileSuono);
			clip = AudioSystem.getClip();
			clip.open(audio);
			
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.loop(-1);
			//clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		//Crea le posizioni
		for(int j=0; j<8; j++)
			for(int i=0; i<8; i++)
				pos[i][j] = new PosizioneCaselle(i,j);
		
		Image imgB[] = new Image[6];
		Image imgN[] = new Image[6];
		
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
		
		//Crea i pezzi neri
		pNero[0] = new Re ("nero", imgN[0], pos[4][0]);
		pNero[1] = new Torre ("nero", imgN[1], pos[0][0]);
		pNero[2] = new Cavallo ("nero", imgN[2], pos[1][0]);
		pNero[3] = new Alfiere ("nero", imgN[3], pos[2][0]);
		pNero[4] = new Regina ("nero", imgN[4], pos[3][0]);
		pNero[5] = new Alfiere ("nero", imgN[3], pos[5][0]);
		pNero[6] = new Cavallo ("nero", imgN[2], pos[6][0]);
		pNero[7] = new Torre ("nero", imgN[1], pos[7][0]);
		
		for(int i=8; i<16; i++) 

			pNero[i] = new Pedone("nero", imgN[5], pos[i-8][1]);
		
		//Crea i pezzi bianchi
		pBianco[0] = new Re ("bianco", imgB[0], pos[4][7]);
		pBianco[1] = new Torre ("bianco", imgB[1], pos[0][7]);
		pBianco[2] = new Cavallo ("bianco", imgB[2], pos[1][7]);
		pBianco[3] = new Alfiere ("bianco", imgB[3], pos[2][7]);
		pBianco[4] = new Regina ("bianco", imgB[4], pos[3][7]);
		pBianco[5] = new Alfiere ("bianco", imgB[3], pos[5][7]);
		pBianco[6] = new Cavallo ("bianco", imgB[2], pos[6][7]);
		pBianco[7] = new Torre ("bianco", imgB[1], pos[7][7]);
		
		for(int i=8; i<16; i++) 

			pBianco[i] = new Pedone("bianco", imgB[5], pos[i-8][6]);
		
		NewScacchi = new JFrame("8 x 8 - il Re e' morto");

		Container cont = NewScacchi.getContentPane();
		NewScacchi.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		NewScacchi.setUndecorated(true);
		NewScacchi.setVisible(true);
		NewScacchi.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		PezziMangiati pmB = new PezziMangiati(pBianco, pNero, "bianchi");
		pmB.setBounds(730, 0, width-740, 310);
		
		PezziMangiati pmN = new PezziMangiati(pBianco, pNero, "neri");
		pmN.setBounds(730, 410, width-740, 310);
		
		ControllaMossa m = new ControllaMossa(NewScacchi, pos, pBianco, pNero);
		m.setPointer(m);
		m.setBounds(730, 310, width-740, 100);
		
		Scacchiera s = new Scacchiera(pBianco, pNero, pos, 0, m);
		s.setBounds(0, 0, 720, 720);
		
		cont.add(pmB);
		cont.add(pmN);
		cont.add(m);
		cont.add(s);
		
		JPanel panel = new JPanel();
		cont.add(panel);

		NewScacchi.setJMenuBar(menu);
		menu.add(File);

		NuovaPartita.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				NewScacchi.dispose();
				clip.stop();
				new NuovaPartita();	
			}
		});
		
		Regole.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

					try {
						new RegoleGioco();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		Informazioni.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				new InfoGioco();
			}
		});
		
		Esci.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				final JFrame question = new JFrame();
				int risultato = JOptionPane.showConfirmDialog(question, "Sei sicuro di voler uscire?", "Esci dal gioco", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (risultato == JOptionPane.YES_OPTION) 
					System.exit(0);
			}
		});
		File.add(NuovaPartita);
		File.add(Regole);
		File.add(Informazioni);
		File.add(Esci);
	}
}
