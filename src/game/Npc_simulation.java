package game;

import javax.swing.JPanel;

public class Npc_simulation extends JPanel{
	private static final long serialVersionUID = 1L;
	int[] left_deck = new int[52];
	int[] hand = new int[5];
	int k;
	String role;
	Poker_point point = new Poker_point();
	
	
	public Npc_simulation() {
		
	}
	public int copy_deck(int tmphand[], int ud[], int us) {
		for ( k = 0; k < 5; k++) {
			hand[k] = tmphand[k];
		}
		make_deck(ud, us);
		role = point.check_point(hand);
		
		return simulation(left_deck, hand);
	}
	public void make_deck(int ud[], int us) {
		for ( k = 0; k < 52; k++) { left_deck[k] = 1; }
		for ( k = 0; k < 5; k++) {
			left_deck[hand[k]] = 0;
		}
		for ( k = 0; k < us; k++) {
			left_deck[ud[k]] = 0;
		}
	}
	public int simulation(int left_deck[], int hand[]) {
		int k, place=-1, tmp, max = 0, card;
		
		for ( k = 0; k < 5; k++) {
			card = hand[k];
			tmp = expect(left_deck, hand, k);
			if ( max < tmp) {
				max = tmp;
				place = k;
			}
			hand[k] = card;
		}
		return place;
	}
	public int expect(int left_deck[], int hand[], int place) {
		int k, tmp=0, total=0;
		for ( k = 0; k < 52; k++) {
			if (left_deck[k] != 0) {
				hand[place] = k;
				point.check_point(hand);
				tmp = point.return_point();
				if ( tmp >= 8) { total += tmp; }
			}
		}
		return total;
	}
}
