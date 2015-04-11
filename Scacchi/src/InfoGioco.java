import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe che mostra le informazioni dell'applicazione
 */
public class InfoGioco implements ActionListener{

	private JFrame Info;
	private JLabel label;
	private JLabel img;
	
	public InfoGioco(){
		
		Info = new JFrame("Informazioni: 8 x 8 - il Re e' morto");
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		JPanel p2a = new JPanel();
		JPanel p2b = new JPanel();
	
		Container cont = Info.getContentPane();
		
		cont.setLayout(new GridLayout(1,2));
		cont.add(p1);
		cont.add(p2);
		
		p2.setLayout(new FlowLayout());
		p2.add(p2a);
		p2.add(p2b);

		ImageIcon image = new ImageIcon("../Scacchi/src/img/logo.jpg");
		img = new JLabel(image, SwingConstants.CENTER);
		p1.add(img);
		
		label = new JLabel("<html><br /><font size=5 color=red><center><i> INFORMAZIONI </i></center></font><br /><br /><font size=4><i> Titolo: </i> 8 x 8 - il Re e' morto <br /><br /><font size=4><i> Autrici: </i> Luisa Zanini & Lisa Bianconi <br /><br /> <i> Anno di creazione: </i> 2015 <br /><br /><center> Realizzato in Java </center></font><br /><br /><br /></html>", SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		p2a.add(label);
		
		JButton Ok = new JButton("        OK        ");
		p2b.add(Ok);
		Ok.addActionListener(this);
		
		Info.setSize(750,358);
		Info.setLocationRelativeTo(null);
		Info.setAlwaysOnTop(true);
		Info.setResizable(false);
		Info.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){

		Info.dispose();
	}
}
