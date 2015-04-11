import java.awt.*;
import java.io.*;

import javax.swing.*;

public class RegoleGioco{

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Dimensioni schermo
	private int width = (int) screenSize.getWidth(); //Larghezza schermo
	private int height = (int) screenSize.getHeight(); //Altezza schermo
	private JFrame Regole;
	private BufferedReader fileTesto;
	
	public RegoleGioco() throws IOException{
		
		Regole = new JFrame("Regole");
		
		Container cont = Regole.getContentPane();
		cont.setLayout(new FlowLayout());
		cont.setBackground(new Color(43, 85, 31));
		
		JTextPane text = new JTextPane();
		text.setContentType("text/html");
		text.setEditable(false);
		text.setBackground(new Color(244, 240, 119));

		FileReader Testo = new FileReader("../Scacchi/src/html/Regole.html");
		fileTesto = new BufferedReader(Testo);
	    String s = "";
	    String test;
	    while(true) {
	    	test = fileTesto.readLine();
	    	s = s + test;
	    	if(test==null)
	    		break;
	    	text.setText(s);
	    }

		JScrollPane scroll = new JScrollPane(text);
		scroll.setPreferredSize(new Dimension(900, height-40));
		cont.add(scroll);
		
		Regole.setSize(width,height);
		Regole.setAlwaysOnTop(true);
		Regole.setResizable(false);
		Regole.setVisible(true);
	}
}