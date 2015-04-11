import java.awt.*;

import javax.swing.*;

import java.awt.image.*;

/**
 * Classe dei pezzi mangiati
 */
public class PezziMangiati extends JPanel {

	private Pezzo pBianco[];
	private Pezzo pNero[];
	private String colore;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Dimensioni schermo
	private int width = (int) screenSize.getWidth(); //Larghezza schermo
	private PosizioneCaselle[] posBianco= new PosizioneCaselle[15]; // Posso mangiare al max 15 pezzi (il re non viene mangiato)
	private PosizioneCaselle[] posNero= new PosizioneCaselle[15];

	public PezziMangiati(Pezzo pBianco[],Pezzo pNero[], String colore){

		super();
		this.pBianco = pBianco;
		this.pNero = pNero;	
		this.colore = colore;	
		
		for(int i=0; i<5; i++)
			posBianco[i] = posNero[i] = new PosizioneCaselle(i, 0);

		for(int i=5; i<10; i++)
			posBianco[i] = posNero[i] = new PosizioneCaselle(i-5, 1);
	
		for(int i=10; i<15; i++)
			posBianco[i] = posNero[i] = new PosizioneCaselle(i-10, 2);
		
		setBackground(Color.lightGray);
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.black);
		g.setFont(new Font("Garamond", Font.BOLD, 30));	
		String s = "Pezzi " + colore + " mangiati";
		g.drawString(s, ((width-740) - (s.length()*13))/2, 30);
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(1, 1, width-743, 307);
		
		int j=0, m=0;
		for(int i=1; i<16; i++){
			if(pBianco[i].mangiato && colore == "bianchi"){
				g.drawImage(pBianco[i].immagine, posBianco[j].PixelX+(width-850)/4, posBianco[j].PixelY+15, null);j++;
			}
			if(pNero[i].mangiato && colore == "neri"){
				g.drawImage(pNero[i].immagine, posNero[m].PixelX+(width-850)/4, posNero[m].PixelY+15, null);m++;
			}	
		}
		repaint();
	}	
}	