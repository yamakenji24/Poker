package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Difficulty extends JPanel implements ActionListener{
	MainPanel mp;
	JButton easy, normal, hard, enter;
	BufferedImage background;
	ImageIcon pressed_icon;
	
	public Difficulty(MainPanel tmp) {
		super();
		mp = tmp;
		try {
			background = ImageIO.read(new File("images/difficulty.jpg"));
			pressed_icon = new ImageIcon("images/decide.png");
		} catch(IOException e) {
			e.printStackTrace();
		}
		setDifficulty();
		
	}
	public void setDifficulty() {
		setLayout(null);
		setOpaque(true);
		easy = new JButton("Easy");
		normal = new JButton("Normal");
		hard = new JButton("Hard");
		enter = new JButton("Šm’è");
		
		easy.setLocation(400, 200);
		normal.setLocation(400, 300);
		hard.setLocation(400, 400);
		enter.setLocation(400, 600);
		
		easy.setSize(150, 50);
		normal.setSize(150, 50);
		hard.setSize(150, 50);
		enter.setSize(150, 50);
		
		easy.addActionListener(this);
		normal.addActionListener(this);
		hard.addActionListener(this);
		enter.addActionListener(this);
		
		add(easy);
		add(normal);
		add(hard);
		add(enter);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, 1000, 800); 
		g.drawImage(background,  0,  0,  1000, 800, null);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource() == easy) {
			easy.setPressedIcon(pressed_icon);
		}
		else if ( e.getSource() == enter) {
			mp.state = 0;
		}
	}
}
