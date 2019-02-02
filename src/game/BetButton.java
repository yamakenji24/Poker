package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BetButton extends JPanel implements ActionListener {
	//JButton[] bet = new JButton[3];
	GamePanel gp;
	public BetButton(GamePanel tmp) {
		gp = tmp;
	}
	public void setButton() {
		int k,x =100;
		gp.bet[0] = new JButton("フォールド");
		gp.bet[1] = new JButton("チェック");
		gp.bet[2] = new JButton("ベット");
		
		for ( k = 0; k < 3; k++) {
			gp.bet[k].addActionListener(gp);
			gp.bet[k].setLocation(x, 300);
			gp.bet[k].setSize(30,60);
			add(gp.bet[k]);
			x += 100;
		}
		setvisible(false);
	}
	
	public void setvisible(boolean flag) {
		int k;
		for ( k = 0; k < 3; k++) {
			gp.bet[k].setVisible(flag);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
}
