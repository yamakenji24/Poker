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
	 BufferedImage backcard = null;
	 ImageIcon[] card = new ImageIcon[52];
	 JButton[] hand = new JButton[5];
	 int[] place = new int[5];
	 int[] deck = new int[52];
	 int i, point=0;
	 String role;
	 Font f1 = new Font("Serif", Font.PLAIN, 24);
	 private Thread thread;
	 boolean in_game = true;
	 //Move_card mc = new Move_card();
	 Poker_point pp = new Poker_point();
	 Move_backcard mb = new Move_backcard();
	 
	public GamePanel(MainPanel panel) {
		int p = 100;
		setLayout(null);
		try {
			ground = ImageIO.read(new File("images/background.jpg"));
			backcard = ImageIO.read(new File("./images/backcard.gif"));
			for ( i = 0; i < 52; i++) {
				card[i] = new ImageIcon("./images/"+(i+1)+".png");
				cardimage[i] = ImageIO.read(new File("./images/"+(i+1)+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		qrand(deck, 52);
		for ( i = 0; i < 5; i++) {
			hand[i] = new JButton(card[deck[i]]);
			hand[i].addActionListener(this);
			hand[i].setLocation(p, 550);
			hand[i].setSize(130, 190);
			p += 150;
			add(hand[i]);
			place[i] = deck[i];
		}
		role = pp.check_point(place);
		repaint();
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
		g.setFont(f1);
		g.setColor(Color.red);
		g.drawString(role,  460,  200);
		g.drawImage(backcard, 100,  200,  100,  150,  null);	
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
		for (int k = 0; k < 5; k++) {
			if ( e.getSource() == hand[k]) {
				hand[k].setIcon(card[deck[i]]);
				place[k] = deck[i];
				mb.Move_from_deck(backcard, k);
				i += 1;
				break;
			}
		}
		role = pp.check_point(place);
		repaint();
	}
}