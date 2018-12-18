package game;

import javax.swing.*;

public class MainPanel extends JLayeredPane implements Runnable{
	StartMenu sm;
	GamePanel gm;
	
	boolean in_game = true; //ƒQ[ƒ€ó‘Ô(0:•\†  1:‘€ìà–¾ 2:ƒQ[ƒ€‰æ–Ê
	public int state = 0;
	int old_state = 0;
	Thread td;

	public MainPanel(int width) {
		sm = new StartMenu(this);
		sm.setBounds(0, 0, 1000, 800);
		add(sm);
		td = new Thread(this);
		td.start();
	}


	@Override
	public void run() {
		while (in_game) {
			try {
				td.sleep(100);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}

			if (state != old_state) {
				if ( old_state == 0) {
					remove(sm);
				} else if(old_state == 1) {
					
				} else if(old_state == 2) {
					remove(gm);
				}

				if ( state == 4) {
					in_game =false;
				} else {
					if ( state == 0) {
						sm = new StartMenu(this);
						sm.setBounds(0, 0, 1000, 800);
						add(sm);
					}  else if (state == 2) {
						gm = new GamePanel(this);
						gm.setBounds(0, 0, 1000, 800);
						add(gm);
					}
					validate();
					old_state=state;
				}
			}
		}
	}
}