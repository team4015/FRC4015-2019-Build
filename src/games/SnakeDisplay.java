package games;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

class SnakeDisplay {

	//private Frame bigFrame;
	//private Panel[][] panels;
	
	SnakeDisplay() {
		/*
		bigFrame = new Frame();
		panels = new Panel[11][11];
		bigFrame.setSize(300, 300);
		for (int x = 0; x < 11; x++) {
			for (int y = 0; y < 11; y++) {
				panels[x][y] = new Panel();
				panels[x][y].setBounds(20*x,300 - 20*y, 20, 20);
				panels[x][y].setVisible(true);
				bigFrame.add(panels[x][y]);
			}
		}
		bigFrame.setVisible(true);*/
	}
	
	void displayStuff(boolean[][] toShow, int foodX, int foodY) {
		int y = 0;
		for (boolean[] row : toShow) {
			String toPrint = "";
			int x = 0;
			for (boolean elem : row) {
				if (x == foodX && y == foodY) {
					toPrint = toPrint + "F";
				}
				else if (elem) {
					toPrint = toPrint + "X";
				}
				else {
					toPrint = toPrint + "E";
				}
				x++;
			}
			y++;
			System.out.println(toPrint);
		}
		/*for (int x = 0; x < 11; x++) {
			for (int y = 0; y < 11; y++) {
				if (toShow[x][y]) {
					panels[x][y].setBackground(Color.BLACK);
				}
				else {
					panels[x][y].setBackground(Color.WHITE);
				}
			}
		}
		panels[foodX][foodY].setBackground(Color.RED);*/
	}
	
}
