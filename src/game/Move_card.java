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
	BufferedImage ca;
	int tmp=0, i=0, j;
	public Move_card() {
	}
	public void set_card(BufferedImage c, int a) {
		ca = c;
		tmp = a;
		i = tmp;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ca, i, j, this);
	}
	@Override
	public void run() {
		j = 550;
		repaint();
		for ( i = tmp; i <= 400; i += 100) {
			repaint();
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
