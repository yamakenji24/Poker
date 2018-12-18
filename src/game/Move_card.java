package game;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.*;
import java.net.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class Move_card extends JPanel implements Runnable{
	private Thread th;
	JButton card;
	int tmp=0;
	public Move_card() {
	}
	public void set_card(JButton card1, int a) {
		card = card1;
		tmp = a;
	}
	public void run() {
		int i,j=550;
		card.setLocation(tmp, 550);
		for ( i = tmp; i <= 400; i += 100) {
			card.setLocation(i, j);
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			j -= 150;
		}
	}
	public void Card_to_center() {
		th = new Thread(this);
		th.start();
	}
}
