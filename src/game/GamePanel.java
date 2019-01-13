package game;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.Image;
import javax.swing.*;
import java.applet.*;
import java.net.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, ActionListener{
	 BufferedImage ground;
	 BufferedImage[] cardimage = new BufferedImage[13];
	 ImageIcon[] card = new ImageIcon[13];
	 JButton[] spade = new JButton[5];
	 int[] place = new int[5];
	 int i;
	 private Thread thread;
	 boolean in_game = true;
	 Move_card mc = new Move_card();
	 
	public GamePanel(MainPanel panel) {
		int p = 100;
		setLayout(null);
		try {
			ground = ImageIO.read(new File("images/background.jpg"));
			for ( i = 0; i < 13; i++) {
				card[i] = new ImageIcon("./images/"+(i+1)+".png");
				cardimage[i] = ImageIO.read(new File("./images/"+(i+1)+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for ( i = 0; i < 5; i++) {
			spade[i] = new JButton(card[i]);
			spade[i].addActionListener(this);
			spade[i].setLocation(p, 550);
			spade[i].setSize(130, 190);
			p += 150;
			add(spade[i]);
			place[i] = i;
		}
		thread = new Thread(this);
		thread.start();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, 1000, 800); 
		g.drawImage(ground,  0,  0,  1000, 800, null);
	}
	@Override
	public void run() {
		requestFocus();
		while (in_game) {
			repaint();
			try {
				Thread.sleep(200);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == spade[0]) {
			/*mc.set_card(cardimage[0], 100);
			mc.Card_to_center();*/
			spade[0].setIcon(card[i]);
			//spade[0].setLocation(100, 550);
			spade[0].setSize(130, 190);
			//add(spade[0]);
			i += 1;
		}
	}

}
