package game;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class StartMenu extends JPanel implements ActionListener{
	MainPanel mp;
	BufferedImage background;
	JButton startButton, instructions, difficulty;
	
	public StartMenu(MainPanel tmp) {
		super();
		mp = tmp;
		try {
			background = ImageIO.read(new File("images/poker.jpg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		setButton();
	}
	public void setButton() {
		setLayout(null);
		setOpaque(true);
		startButton = new JButton("GAME START!");
		instructions = new JButton("ëÄçÏê‡ñæ");
		difficulty = new JButton("ìÔà’ìxëIë");
		startButton.setLocation(400, 400);
		instructions.setLocation(400, 500);
		difficulty.setLocation(400, 600);
		startButton.setSize(150,50);
		instructions.setSize(150,50);
		difficulty.setSize(150, 50);
		startButton.addActionListener(this);
		instructions.addActionListener(this);
		difficulty.addActionListener(this);
		add(startButton);
		add(instructions);
		add(difficulty);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, 1000, 800); 
		g.drawImage(background,  0,  0,  1000,  800,  null);
		g.setFont(new Font(Font.SERIF, Font.BOLD, 50));
		g.setColor(Color.red);
		g.drawString("Ç®ééÇµçÏê¨íÜ",  250,  200);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == instructions) {
			mp.state=1;
		} else if (e.getSource() == startButton) {
			mp.state=2;
		} else if (e.getSource() == difficulty) {
			mp.state = 3;
		}
	}
	
}