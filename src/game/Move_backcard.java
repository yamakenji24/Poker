package game;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Move_backcard extends JPanel implements Runnable {
	private Thread thread;
	int dx,x;
	
	public Move_backcard() {
		 thread = new Thread(this);
		 thread.start();
	}
	
	public void Move_from_deck(BufferedImage tmpcard, int k) {
		 BufferedImage backcard = null;
		 backcard = tmpcard;
		 x = check_place(k);
	}
	public int check_place(int k) {
		int place=0;
		switch(k) {
		case 1: place = 100; break;
		case 2: place = 250; break;
		case 3: place = 400; break;
		case 4: place = 550; break;
		case 5: place = 700; break;
		default: place = 100; break;
		}
		return place;
	}
	public void run() {
		int l;
		for ( l = 100; l <= x; l+=20) {
			
			repaint();
			try {
				Thread.sleep(150);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
