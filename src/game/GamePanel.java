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
	 BufferedImage[] cardimage = new BufferedImage[52];
	 ImageIcon[] card = new ImageIcon[52];
	 JButton[] spade = new JButton[5];
	 int[] place = new int[5];
	 int[] deck = new int[52];
	 int i;
	 private Thread thread;
	 boolean in_game = true;
	 Move_card mc = new Move_card();
	 
	public GamePanel(MainPanel panel) {
		int p = 100;
		setLayout(null);
		try {
			ground = ImageIO.read(new File("images/background.jpg"));
			for ( i = 0; i < 52; i++) {
				card[i] = new ImageIcon("./images/"+(i+1)+".png");
				cardimage[i] = ImageIO.read(new File("./images/"+(i+1)+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		qrand(deck, 52);
		for ( i = 0; i < 5; i++) {
			spade[i] = new JButton(card[deck[i]]);
			spade[i].addActionListener(this);
			spade[i].setLocation(p, 550);
			spade[i].setSize(130, 190);
			p += 150;
			add(spade[i]);
			place[i] = deck[i];
		}
		thread = new Thread(this);
		thread.start();
	}
	
	public void qrand(int seq[], int n) {
		int k,p;
		for ( k = 0; k < n; k++) {
			seq[k] = k;
		}
		for ( k = 1; k < n; k++) {
			p = irand(k+1);
			arr_swap(seq, k, p);
		}
	}
	public void arr_swap(int arr[], int p1, int p2) {
		int tmp;
		tmp = arr[p1]; arr[p1] = arr[p2]; arr[p2] = tmp;
	}
	public int irand(int n) {
		double d = Math.random();
		int rand = (int) (d * 32767);
		double tmp = rand / (32767 + 1.0);
		return (int)(n*tmp);
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
			spade[0].setIcon(card[deck[i]]);
			i += 1;
		} else if ( e.getSource() == spade[1]) {
			spade[1].setIcon(card[deck[i]]);
			i+=1;
		} else if (e.getSource() == spade[2]) {
			spade[2].setIcon(card[deck[i]]);
			i+=1;
		} else if (e.getSource() == spade[3]) {
			spade[3].setIcon(card[deck[i]]);
			i+=1;
		} else if (e.getSource() == spade[4]) {
			spade[4].setIcon(card[deck[i]]);
			i+=1;
		}
	}

}
