package game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
	public Main() {
		setBounds(0, 0, 1000, 800);
		MainPanel Mypane =new MainPanel(0);
		Container MyContainer = getContentPane();
		MyContainer.add(Mypane);
		setVisible(true);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				dispose();
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new Main();
		});
	}
}
