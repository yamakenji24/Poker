package game;

import javax.swing.*;

public class MainPanel extends JLayeredPane implements Runnable{
	StartMenu sm;
	GamePanel gm;
	Difficulty diff;
	
	boolean in_game = true; //ゲーム状態(0:表紙  1:操作説明 2:ゲーム画面 3:難易度選択
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
					remove(sm);
				} else if(old_state == 2) {
					remove(gm);
				} else if (old_state == 3) {
					remove(diff);
				}

				if ( state == 4) {
					in_game =false;
				} else {
					if ( state == 0) {
						sm = new StartMenu(this);
						sm.setBounds(0, 0, 1000, 800);
						add(sm);
					} else if (state == 1) { 
						
					} else if (state == 2) {
						gm = new GamePanel(this);
						gm.setBounds(0, 0, 1000, 800);
						add(gm);
					} else if ( state == 3) {
						diff = new Difficulty(this);
						diff.setBounds(0, 0, 1000, 800);
						add(diff);
					}
					validate();
					old_state=state;
				}
			}
		}
	}
}