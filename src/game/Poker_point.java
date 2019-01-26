package game;

public class Poker_point {
	int[] num = new int[13];
	int[] sut = new int[4];
	int p0, p1, p2, p3;
	int k,tmp;
	public Poker_point() {
		for ( k = 0; k < 13; k++) { num[k] = 0;}
		for ( k = 0; k < 4; k++) { sut[k] = 0; }
	}
	public String check_point(int hand[]) {
		for ( k = 0; k < 13; k++) { num[k] = 0;}
		for ( k = 0; k < 4; k++) { sut[k] = 0; }
		for ( k = 0; k < 5; k++) {
			tmp = hand[k] % 13; num[tmp]++;
		}
		for ( k = 0; k < 5; k++) {
			tmp = hand[k] / 13; sut[tmp]++;
		}
		p1 = point_pair(num);
		p2 = point_flash(sut);
		p3 = point_straight(num, p2);
		p0 = p1;
		if ( p0 < p2) { p0 = p2; }
		if ( p0 < p3) { p0 = p3; }
		return show_point(p0);
	}
	public String show_point(int p) {
		String role;
		switch(p) {
		case 256: role = "royal straight flush"; break;
		case 128: role = "straight flush"; break;
		case 64: role = "four card"; break;
		case 32: role = "straight"; break;
		case 24: role = "flush"; break;
		case 16: role = "full house"; break;
		case 8: role = "three card"; break;
		case 2: role = "two pair"; break;
		case 1: role = "one pair"; break;
		default: role="no pair"; break;
		}
		return role;
	}
	public int point_pair(int num[]) {
		int c2 = 0, c3 = 0;
		int k;
		//---- four card
		for ( k = 0; k < 13; k++) { 
			if ( num[k] == 4) { 
				return 64;
			}
		}
		
		for ( k = 0; k < 13; k++) {
			if ( num[k] == 3) { c3++; }
			if ( num[k] == 2) { c2++; }
		}
		
		//----- full house
		if ( c3 == 1 && c2 == 1) { return 16;}
		//----- three card
		if ( c3 == 1) { return 8;}
		//----- two pair
		if ( c2 == 2) { return 2;}
		//----- one pair
		if ( c2 == 1) { return 1;}
		//----- no pair
		return 0;
	}
	public int point_flash(int sut[]) {
		int k;
		for ( k = 0; k < 4; k++) {
			if(sut[k] == 5) { return 24;}
		}
		return 0;
	}
	public int point_straight(int num[], int p) {
		int len = 0, k;
		for ( k = 0; k < 13; k++) {
			if ( num[k] > 0) {
				len++;
				if ( len == 5) { break;}
			} else { len = 0; }
		}
		
		//------ royal straight
		if ( len == 4 && num[0] == 1) { len = 6;}
		//------ royal straight flash
		if ( len == 6) { if ( p > 0) { return 256;} else { return 32;}}
		//------ normal straight
		if ( len == 5) { if ( p > 0) { return 128;} else { return 32;}}
		
		return 0;
	}
}
