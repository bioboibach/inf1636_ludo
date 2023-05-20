package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Tabuleiro extends JPanel{
	
	
	
	Rectangle2D grid[][] = new Rectangle2D[15][15];
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		draw_casa(g2d);
	}
	
	public void draw_casa(Graphics2D g) {
		double lado_casa = 40.0;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				grid[i][j] = new Rectangle2D.Double(1 + i * lado_casa, 1 + j * lado_casa , lado_casa, lado_casa);
				g.draw(grid[i][j]);
			}
		}
	}
}
