package game;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, ActionListener{
	 MainPanel mp;
	 BufferedImage ground;
	 BufferedImage[] cardimage = new BufferedImage[52];
	 BufferedImage backcard,chipimage, player = null;
	 ImageIcon[] card = new ImageIcon[52];
	 JButton[] playerhand = new JButton[5];
	 JButton[] npchand = new JButton[5];
	 JButton[] bet = new JButton[3];
	 JButton start, raise_bet;
	 int[] place = new int[5];
	 int[] deck = new int[52];
	 int p1score, chip=50, chipbet = 0;                  //掛け金要素
	 int i,x,point=0, backplace=-1;
	 int l=-100,m = 200, dy,speed;  //deck動作用
	 int hx,hy=550,my, hspeed;   	 //hand動作用
	 String role;
	 Font f1 = new Font("Serif", Font.PLAIN, 24);
	 private Thread thread;
	 boolean in_game = true;
	 boolean back_flag = false, hand_flag = false;
	 boolean chip_bet = false, raise_flag = false;
	 Poker_point pp = new Poker_point();
	 
	 public GamePanel(MainPanel panel) {
		p1score = 1000;
		mp = panel;
		setLayout(null);
		
		try {
			ground = ImageIO.read(new File("images/background.jpg"));
			backcard = ImageIO.read(new File("./images/backcard.gif"));
			player = ImageIO.read(new File("./images/player.png"));
			chipimage = ImageIO.read(new File("./images/chip.png"));
			for ( i = 0; i < 52; i++) {
				card[i] = new ImageIcon("./images/"+(i+1)+".png");
				cardimage[i] = ImageIO.read(new File("./images/"+(i+1)+".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		qrand(deck, 52);
		sethand();
		role = pp.check_point(place);
		setButton();
		repaint();
		back_to_start();
		thread = new Thread(this);
		thread.start();
	 }
	 public void sethand() {
		 int p = 100;
		 for ( i = 0; i < 5; i++) {
				playerhand[i] = new JButton(card[deck[i]]);
				playerhand[i].addActionListener(this);
				playerhand[i].setLocation(p, 550);
				playerhand[i].setSize(130, 190);
				p += 150;
				add(playerhand[i]);
				place[i] = deck[i];
			}
	 }
	 public void setButton() {
		int k,x =300;
		bet[0] = new JButton("フォールド");
		bet[1] = new JButton("チェック");
		bet[2] = new JButton("ベット");
		raise_bet = new JButton("レイズ");
		
		for ( k = 0; k < 3; k++) {
			bet[k].addActionListener(this);
			bet[k].setLocation(x, 300);
			bet[k].setSize(100,30);
			add(bet[k]);
			x += 150;
		}
		raise_bet.addActionListener(this);
		raise_bet.setLocation(450, 350);
		raise_bet.setSize(100, 30);
		add(raise_bet);
		
		setvisible(false);
	}
		
	public void setvisible(boolean flag) {
		int k;
		for ( k = 0; k < 3; k++) {
			bet[k].setVisible(flag);
		}
		if ( chip_bet==true && flag==true) {
			bet[1].setVisible(false);
			raise_bet.setVisible(true);
		} else if (flag == false ) {
			raise_bet.setVisible(false);
		}
	}
	public void back_to_start() {
		setLayout(null);
		setOpaque(true);
		start = new JButton("スタート画面");
		start.setLocation(0, 0);
		start.setSize(115,50);
		start.addActionListener(this);
		add(start);
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
	public void check_place(int k) {
		switch(k) {
		case 0: x = 100; dy = 40; my = 10; speed = 70; hspeed = 20; break;
		case 1: x = 250; dy = 25; my = 15; speed = 50; hspeed = 30; break;
		case 2: x = 400; dy = 15; my = 15; speed = 40; hspeed = 30; break;
		case 3: x = 550; dy = 10; my = 25; speed = 30; hspeed = 40; break;
		case 4: x = 700; dy = 10; my = 30; speed = 20; hspeed = 50; break;
		default: x = 100; break;
		}
	}
	public void ButtonEnabled(boolean flag) {
		int k;
		for ( k = 0; k < 5; k++) {
			if ( k != backplace) {
				playerhand[k].setEnabled(flag);
			}
		}
	}
	public void move_from_deck() {
		while (back_flag) {
			for ( l = -100; l <= x; l+=30) {
				m += dy;
				repaint();
				try {
					Thread.sleep(speed);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			l = -100; m = 200;
			playerhand[backplace].setIcon(card[deck[i]]);
			place[backplace] = deck[i];
			playerhand[backplace].setVisible(true);
			i += 1;
			back_flag = false;
			setvisible(true);
			repaint();
		}
	}
	public void move_from_hand() {
		int state = 0;
		while(hand_flag) {
			playerhand[backplace].setVisible(false);
			for ( hx = x; hx <= 1150; hx+=30) {
				hy -= my;
				repaint();
				try {
					Thread.sleep(hspeed);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			hy = 550;
			hand_flag = false;
			repaint();
			state = 1;
		}
		if ( state == 1) {
			ButtonEnabled(true);
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, 1000, 800); 
		g.drawImage(ground,  0,  0,  1000, 800, null);
		g.drawImage(player, 20, 450, 300, 100, null);
		g.setColor(new Color(34, 200, 34));
		g.setFont(new Font("Serif", Font.PLAIN, 26));
		g.drawString(String.valueOf(p1score),  110,  530);
		g.setFont(f1);
		g.setColor(Color.red);
		g.drawImage(backcard, l,  m,  100,  150,  null);
		g.drawString(role,  460,  200);	
		if ( hand_flag) {
			g.drawImage(cardimage[place[backplace]], hx, hy,  100,  150,  null);	
		}
		if ( chip_bet) {
			g.drawImage(chipimage, 440, 400,  120,  100,  null);
			g.setColor(new Color(34, 200, 34));
			g.setFont(new Font("Serif", Font.PLAIN, 22));
			g.drawString(String.valueOf(chipbet),  485,  460);
		}
	}
	@Override
	public void run() {
		requestFocus();
		while (in_game) {
			move_from_hand();
			move_from_deck();
			role = pp.check_point(place);
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
		int k,l;
		if ( e.getSource() == start) {
			mp.state = 0;
		}
		for (k = 0; k < 5; k++) {
			if ( e.getSource() == playerhand[k]) {
				backplace = k;
				ButtonEnabled(false);
				check_place(k);
				back_flag = true;
				hand_flag = true;
				break;
			}
		}
		for ( l = 0; l < 3; l++) {
			if ( e.getSource() == bet[l]) {
				if ( l == 0) {
					chipbet = 0;
					chip_bet = false;
					raise_flag = false;
				} else if ( l == 1) {
				
				} else if ( l == 2) {
					p1score -= chip;
					chipbet += chip;
					chip_bet = true;
					raise_flag = true;
				}
				setvisible(false);
			}
		}
		if ( e.getSource() == raise_bet) {
			p1score -= chip*2;
			chipbet += chip*2;
			setvisible(false);
		}
	}
}