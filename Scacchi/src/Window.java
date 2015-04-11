import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Window {

	private static JLabel img;
	private static JButton partita;
	private static JButton esci;
	private static JLabel label;
	private static JFrame Scacchi;

	/**
	 * Lancia i metodi per la creazione delle finestre
	 */
	public static void main(String args[]){
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run(){
				
				JPanel p1 = new JPanel();
				JPanel p2 = new JPanel();
				
				Scacchi = new JFrame("8 x 8 - il Re è morto");
				
				ImageIcon image = new ImageIcon("../Scacchi/src/img/logo.jpg");
				img = new JLabel(image, SwingConstants.CENTER);
				img.setVerticalAlignment(SwingConstants.CENTER);
				partita = new JButton("NUOVA PARTITA");
				esci = new JButton("ESCI");
				label = new JLabel("© Luisa Zanini & Lisa Bianconi");

				partita.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Scacchi.dispose();
						new NuovaPartita();
					}

				});
				
				esci.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					} 
				});

				Container cont = Scacchi.getContentPane();

				cont.setLayout(new FlowLayout());
				cont.add(p1);
				cont.add(p2);
				
				p1.setLayout(new FlowLayout());
				p1.add(img);

				p2.setLayout(new GridLayout(3,1,10,10));
				p2.add(partita);
				p2.add(esci);
				p2.add(label);

				Scacchi.setSize(500, 480);
				Scacchi.setLocationRelativeTo(null);
				Scacchi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Scacchi.setResizable(false);
				Scacchi.setVisible(true);
			}
		});
	}
}