package game;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Move_backcard extends JPanel implements Runnable {
	private Thread mv;
	private int l=300,dx,x,y=200;
	BufferedImage backcard = null;
	boolean flag;
	public Move_backcard() {
	}
	
	public void Move_from_deck(BufferedImage tmpcard, int k) {
		 backcard = tmpcard;
		 x = check_place(k);
		 flag = true;
		 repaint();
		 mv = new Thread(this);
		 mv.start();
	}
	public int check_place(int k) {
		int place=0;
		switch(k) {
		case 0: place = 100; break;
		case 1: place = 250; break;
		case 2: place = 400; break;
		case 3: place = 550; break;
		case 4: place = 700; break;
		default: place = 100; break;
		}
		return place;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.printf("%d\n",  l);
		g.drawImage(backcard, l,  y,  100,  150,  this);	
	}
	@Override
	public void run() {
		while (flag == true) {
			for ( l = 100; l <= x; l+=10) {
				System.out.printf("%d\n",  l);
				repaint();
				try {
					Thread.sleep(200);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
			flag = false;
		}
	}
}
