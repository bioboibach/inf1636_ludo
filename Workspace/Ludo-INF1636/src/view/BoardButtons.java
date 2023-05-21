import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;


public class BoardButtons extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
	
		// Desenha o retângulo cinza no local dos botões
		Rectangle buttonRectangle = new Rectangle(725, 0, 500, 750);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(buttonRectangle.x, buttonRectangle.y, buttonRectangle.width, buttonRectangle.height);
				
		Font font = new Font("Arial", Font.BOLD, 32);
		g2d.setFont(font);
		// Desenha o texto "A jogar"
		String text = "A jogar";
		int textX = 900; // Posição x do texto
		int textY = 310; // Posição y do texto
		g2d.drawString(text, textX, textY);
		
		super.paintComponent(g);
	}
	
	
}
