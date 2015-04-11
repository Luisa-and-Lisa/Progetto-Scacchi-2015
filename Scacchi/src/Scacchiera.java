import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.*; 

public class Scacchiera extends JPanel implements MouseListener, MouseMotionListener{
	
	private Shape [][] board; 
	private Pezzo [] pBianco;
	private Pezzo [] pNero;
	private PosizioneCaselle pos[][];
	protected int Turno;
	private int x, y;
	
	private ControllaMossa Mossa;
	private Image imgPezzo;
	private PosizioneCaselle temp = new PosizioneCaselle(0,0);
	private Pezzo ProxPezzo;
	private long tm;
	private PosizioneCaselle posIniziale;
	private boolean trovato;
	private static int Coord[] = new int[2]; //Coordinate x e y della scacchiera
	private int pCliccato = 0;
	
	public Scacchiera(Pezzo[] pBianco,Pezzo[] pNero, PosizioneCaselle[][] pos, int Turno, ControllaMossa Mossa){
		
		super();
		board = new Shape[8][8];
		this.pBianco = pBianco;
		this.pNero = pNero;
		this.pos = pos;
		this.Turno = Turno;
		this.Mossa = Mossa;
		
		MediaTracker mt= new MediaTracker(this);
		for(int i=0;i<16;i++){

			mt.addImage(pBianco[i].immagine, i+1);
			mt.addImage(pNero[i].immagine, i+17);
		}
		
		try{
			mt.waitForAll();
		}catch(InterruptedException e){}
			addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g; 
		
		//Disegna il bordo scacchiera
		g2.setStroke(new BasicStroke(40));
		g2.setColor(new Color(43, 85, 31));
		g2.drawRect(20, 20, 680, 680);

		//Disegna le coordinate (numeri e lettere) che identificano ogni casella della scacchiera
		char lettera = 'A';
		int numero = 8;
		for (int m=1; m<9; m++){
			g.setColor(Color.white);
		    g.setFont(new Font("Andalus", Font.PLAIN, 25));
			g.drawString(""+numero, 0*80+10, m*80+10);
			g.drawString(""+numero, 9*80-30, m*80+10);
			g.drawString(""+lettera, m*80-10, 0*80+30);
			g.drawString(""+lettera, m*80-10, 9*80-10);
			lettera++;
			numero--;
		}
		
		//Disegna la scacchiera
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){ 
				g2.setColor(((i+j)%2 == 0) ? new Color(244, 240, 119) : new Color(129, 90, 15)); 
				board [i][j] = new Rectangle2D.Double((i+0.5)*80, (j+0.5)*80, 80, 80); 
				g2.fill(board[i][j]);	
			}	
		}
		
		//Disegna i pezzi bianchi e neri 
		for(int i=0;i<16;i++){
			
			if(!pNero[i].mangiato)
				g.drawImage(pNero[i].immagine, pNero[i].getPos().PixelX+15, pNero[i].getPos().PixelY+15, null);
			
			if(!pBianco[i].mangiato)
				g.drawImage(pBianco[i].immagine, pBianco[i].getPos().PixelX+15, pBianco[i].getPos().PixelY+15, null);	
			
			if (pCliccato==1)
				g.drawImage(imgPezzo, temp.PixelX+15, temp.PixelY+15, null);
		}	
	}		
	
	/**
	 * Convertitore da Pixel a Coordinata
	 */
	public static int[] PixelToCoord(int x, int y){

		int rigaprec = 0;
		int rigasucc = 80;
		for (int i=0; i<8; i++){ 
			int colonnaprec = 0; 
			int colonnasucc = 80;
			for (int j=0; j<8; j++){
				if ((x>=colonnaprec && x<=colonnasucc && y>=rigaprec && y<=rigasucc)){
					Coord[0] = j;
					Coord[1] = i;
				return Coord;
				}	
				else{
					colonnaprec = colonnasucc;
					colonnasucc = colonnaprec + 80;
				}
			}	
			rigaprec = rigasucc;
			rigasucc = rigaprec + 80;
		}
		return Coord;
	}

	public void mousePressed(MouseEvent e) {
		
		addMouseMotionListener(this);
		
        x = e.getX() - 40;
        y = e.getY() - 40;
		
		Coord = PixelToCoord(x,y);
		trovato = false;
		
		//Turno dei bianchi
		if(Turno==0){	
			for(int i=0; i<16; i++){
				if (!pBianco[i].mangiato && pBianco[i].getPos().CoordX == Coord[0] && pBianco[i].getPos().CoordY == Coord[1]){
					ProxPezzo = pBianco[i];
					trovato = true;
					break;
				}
			}
		}
		
		//Turno dei neri
		if(Turno==1){	
			for(int i=0; i<16; i++){
				if (!pNero[i].mangiato && pNero[i].getPos().CoordX == Coord[0] && pNero[i].getPos().CoordY == Coord[1]){
					ProxPezzo = pNero[i];
					trovato = true;
					break;
				}
			}
		}
		
		if (trovato){
			posIniziale = ProxPezzo.getPos();
			temp.PixelX = x;
			temp.PixelY = y;
			ProxPezzo.setPos(temp);
			imgPezzo = ProxPezzo.immagine;
			pCliccato++;
			repaint(tm, x-5, y-5, 90, 90);
		}
	}
	
    public void mouseDragged(MouseEvent e) {
    	
    	if (trovato){			
			temp.PixelX = e.getX() - 40;
			temp.PixelY = e.getY() - 40;
			repaint(tm, temp.PixelX-300, temp.PixelY-300, 720, 720); //40 40 160 160
			e.consume();		
    	}
	}
    
    public void mouseReleased(MouseEvent e) {
   	
    	if (trovato){	
	    	x = e.getX() - 40;
			y = e.getY() - 40;
	    	Coord = PixelToCoord(x,y);
	        PosizioneCaselle posFinale = pos[Coord[0]][Coord[1]];
	        Turno = Mossa.Sposta(ProxPezzo, posIniziale, posFinale, Turno, false);
			repaint();
			pCliccato--;
			removeMouseMotionListener(this);
			repaint();
			e.consume();
    	}	
    }	
    
	public void mouseClicked(MouseEvent e) {

	}

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }
	
}
