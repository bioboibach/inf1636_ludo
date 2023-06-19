package view;
import modal.*;
import controller.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;


public class LudoBoard extends JPanel{

	private static final int SIZE = 720 / 15; 	// Tamanho de cada célula do tabuleiro
	private static final int WIDTH = 15; 		// Largura do tabuleiro em células
	private static final int HEIGHT = 15; 		// Altura do tabuleiro em células

	private static final Color[] COLORS = { Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE, Color.BLACK };
	
	private JButton newGameButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton launchDice;
	
	
	int[][] array = new int[51][2];

	array[0] = new int[] {107 - SIZE, 300};
	array[1] = new int[] {107, 300};
	array[2] = new int[] {107 + SIZE, 300};
	array[3] = new int[] {107 + 2 * SIZE, 300};
	array[4] = new int[] {107 + 3 * SIZE, 300};
	array[5] = new int[] {107 + 4 * SIZE, 300 - SIZE};
	array[6] = new int[] {107 + 4 * SIZE, 300 - 2 * SIZE};
	array[7] = new int[] {107 + 4 * SIZE, 300 - 3 * SIZE};
	array[8] = new int[] {107 + 4 * SIZE, 300 - 4 * SIZE};
	array[9] = new int[] {107 + 4 * SIZE, 300 - 5 * SIZE};
	array[10] = new int[] {107 + 4 * SIZE, 300 - 6 * SIZE};
	array[11] = new int[] {107 + 5 * SIZE, 300 - 6 * SIZE};
	array[12] = new int[] {107 + 6 * SIZE, 300 - 6 * SIZE};
	array[13] = new int[] {107 + 6 * SIZE, 300 - 5 * SIZE};
	array[14] = new int[] {107 + 6 * SIZE, 300 - 4 * SIZE};
	array[15] = new int[] {107 + 6 * SIZE, 300 - 3 * SIZE};
	array[16] = new int[] {107 + 6 * SIZE, 300 - 2 * SIZE};
	array[17] = new int[] {107 + 6 * SIZE, 300 - SIZE};
	array[18] = new int[] {107 + 7 * SIZE, 300};
	array[19] = new int[] {107 + 8 * SIZE, 300};
	array[20] = new int[] {107 + 9 * SIZE, 300};
	array[21] = new int[] {107 + 10 * SIZE, 300};
	array[22] = new int[] {107 + 11 * SIZE, 300};
	array[23] = new int[] {107 + 12 * SIZE, 300};
	array[24] = new int[] {107 + 12 * SIZE, 300 + SIZE};
	array[25] = new int[] {107 + 12 * SIZE, 300 + 2 * SIZE};
	array[26] = new int[] {107 + 11 * SIZE, 300 + 2 * SIZE};
	array[27] = new int[] {107 + 10 * SIZE, 300 + 2 * SIZE};
	array[28] = new int[] {107 + 9 * SIZE, 300 + 2 * SIZE};
	array[29] = new int[] {107 + 8 * SIZE, 300 + 2 * SIZE};
	array[30] = new int[] {107 + 7 * SIZE, 300 + 2 * SIZE};
	array[31] = new int[] {107 + 6 * SIZE, 300 + 3 * SIZE};
	array[32] = new int[] {107 + 6 * SIZE, 300 + 4 * SIZE};
	array[33] = new int[] {107 + 6 * SIZE, 300 + 5 * SIZE};
	array[34] = new int[] {107 + 6 * SIZE, 300 + 6 * SIZE};
	array[35] = new int[] {107 + 6 * SIZE, 300 + 7 * SIZE};
	array[36] = new int[] {107 + 6 * SIZE, 300 + 8 * SIZE};
	array[37] = new int[] {107 + 5 * SIZE, 300 + 8 * SIZE};
	array[38] = new int[] {107 + 4 * SIZE, 300 + 8 * SIZE};
	array[39] = new int[] {107 + 4 * SIZE, 300 + 7 * SIZE};
	array[40] = new int[] {107 + 4 * SIZE, 300 + 6 * SIZE};
	array[41] = new int[] {107 + 4 * SIZE, 300 + 5 * SIZE};
	array[42] = new int[] {107 + 4 * SIZE, 300 + 4 * SIZE};
	array[43] = new int[] {107 + 4 * SIZE, 300 + 3 * SIZE};
	array[44] = new int[] {107 + 3 * SIZE, 300 + 2 * SIZE};
	array[45] = new int[] {107 + 2 * SIZE, 300 + 2 * SIZE};
	array[46] = new int[] {107 + SIZE, 300 + 2 * SIZE};
	array[47] = new int[] {107 - SIZE, 300 + 2 * SIZE};
	array[48] = new int[] {107 - 2 * SIZE, 300 + 2 * SIZE};
	array[49] = new int[] {107 - 2 * SIZE, 300 + SIZE};
	array[50] = new int[] {107 - 2 * SIZE, 300};


	Image i[] = new Image[6];
	int die_val = 1;
	int turn = 0;
	
	Observavel obs;
	Object lob[];

	int vez;
	int val_dado;
	int res; 
	
	int id_jogador;
	int id_peca;
	int qual_array;
	int index_do_array;

	public LudoBoard() {
		setPreferredSize(new Dimension(1200, 700)); // Tamanho da janela
		setLayout(null);
		
			try {
				i[0] = ImageIO.read(new File("res/images/Dado1.png"));
				i[1] = ImageIO.read(new File("res/images/Dado2.png"));
				i[2] = ImageIO.read(new File("res/images/Dado3.png"));
				i[3] = ImageIO.read(new File("res/images/Dado4.png"));
				i[4] = ImageIO.read(new File("res/images/Dado5.png"));
				i[5] = ImageIO.read(new File("res/images/Dado6.png"));
			}
			catch(IOException e){
				System.out.println(e.getMessage());
				System.exit(1);
			}
		
		newGameButton = new JButton("Nova Partida");
		newGameButton.setBounds(835, 25, 250, 50); // Cordenada dos botoes
		newGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startNewGame();
			}
		});
		add(newGameButton);

		loadButton = new JButton("Carrega Partida");
		loadButton.setBounds(835, 115, 250, 50); // Cordenada do botoes
		loadButton.setFont(new Font("Arial", Font.PLAIN, 18));
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSavedGame();
			}
		});
		add(loadButton);
		saveButton = new JButton("Salvar");
		saveButton.setBounds(835, 215, 250, 50); // Cordenada do botoes
		saveButton.setFont(new Font("Arial", Font.PLAIN, 18));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}
		});
		add(saveButton);

		launchDice = new JButton("Lançar Dado");
		launchDice.setBounds(835, 530, 250, 50); // Cordenada dos botoes
		launchDice.setFont(new Font("Arial", Font.PLAIN, 18));
		launchDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchDice();
			}
		});
		add(launchDice);

	}

	public void startNewGame() {
		System.out.println("Iniciando nova partida...");
	}

	public void loadSavedGame() {
		File savedGameFile = new File("partida_salva.txt");
		if (savedGameFile.exists()) {
			System.out.println("Carregando partida salva...");
		} else {
			System.out.println("Nenhuma partida salva encontrada.");
		}
	}

	public void saveGame() {
		// Salvar as posições do jogo com algo tipo fwrite sendo que vai ter que adicionar a opção de colocar esse jogo salvo na matriz de tabuleiro
		System.out.println("Jogo salvo! ");
	}

	public void launchDice() {
		Modal_interface.getInstance().run_turn();
		die_val = Modal_interface.getInstance().get_roll();
		turn = Modal_interface.getInstance().get_player_turn(); // remover dps TODO
		repaint();
	}
	


//public void drawAbrigo(Graphics g){
//		g.setColor(Color.BLACK);
//		g.drawOval(posX, posY, 35,35);
//		g.setColor(Color.GREEN);
//		g.fillOval(posX, posY, 35,35)
//		g.setColor(Color.RED);
//		g.fillOval(posX, posY, 25,25);
//	
//}


public void drawPieces(Graphics g) {
		
		// Peças Vermelho
		g.setColor(Color.BLACK);
		g.drawOval(57,57, 25, 25);
		g.setColor(Color.RED);
		g.fillOval(57, 57, 25, 25);
		g.setColor(Color.BLACK);
		g.drawOval(197, 57, 25, 25);
		g.setColor(Color.RED);
		g.fillOval(197, 57, 25, 25);
		g.setColor(Color.BLACK);
		g.drawOval(57, 177, 25, 25);
		g.setColor(Color.RED);
		g.fillOval(57, 177, 25, 25);		
		g.setColor(Color.BLACK);
		g.drawOval(197, 177, 25, 25);
		g.setColor(Color.RED);
		g.fillOval(197, 177, 25, 25);
		
		// Peças Verde
		g.setColor(Color.BLACK);
		g.drawOval(492,57, 25, 25);
		g.setColor(Color.GREEN);
		g.fillOval(492, 57, 25, 25);	
		g.setColor(Color.BLACK);
		g.drawOval(632, 57, 25, 25);
		g.setColor(Color.GREEN);
		g.fillOval(632, 57, 25, 25);
		g.setColor(Color.BLACK);
		g.drawOval(632, 177, 25, 25);
		g.setColor(Color.GREEN);
		g.fillOval(632, 177, 25, 25);				
		g.setColor(Color.BLACK);
		g.drawOval(492, 177, 25, 25);
		g.setColor(Color.GREEN);
		g.fillOval(492, 177, 25, 25);
		
		// Peças Azul
		g.setColor(Color.BLACK);
		g.drawOval(57,492, 25, 25);
		g.setColor(Color.BLUE);
		g.fillOval(57, 492, 25, 25);	
		g.setColor(Color.BLACK);
		g.drawOval(197, 492, 25, 25);
		g.setColor(Color.BLUE);
		g.fillOval(197, 492, 25, 25);
		g.setColor(Color.BLACK);
		g.drawOval(57, 612, 25, 25);
		g.setColor(Color.BLUE);
		g.fillOval(57, 612, 25, 25);
		g.setColor(Color.BLACK);
		g.drawOval(197, 612, 25, 25);
		g.setColor(Color.BLUE);
		g.fillOval(197, 612, 25, 25);
		
		// Pecas amarelo
		g.setColor(Color.BLACK);
		g.drawOval(632, 612, 25, 25);
		g.setColor(Color.YELLOW);
		g.fillOval(632, 612, 25, 25);
		g.setColor(Color.BLACK);
		g.drawOval(632, 492, 25, 25);
		g.setColor(Color.YELLOW);
		g.fillOval(632, 492, 25, 25);
		g.setColor(Color.BLACK);
		g.drawOval(492, 492, 25, 25);
		g.setColor(Color.YELLOW);
		g.fillOval(492, 492, 25, 25);
		g.setColor(Color.BLACK);
		g.drawOval(492, 612, 25, 25);
		g.setColor(Color.YELLOW);
		g.fillOval(492, 612, 25, 25);
				
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color color;
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setPaint(COLORS[turn]);
		Rectangle2D rt = new Rectangle2D.Double(890.0,350.0,150.0,150.0);
		g2D.fill(rt);

		g2D.drawImage(i[die_val-1], 915, 375, null);
		
		
		
		// Desenha o tabuleiro
		for (int row = 0; row < HEIGHT; row++) { // Branco
			for (int col = 0; col < WIDTH; col++) {
				int x = col * SIZE;
				int y = row * SIZE;

				// Define a cor de fundo da célula com base na posição
				color = COLORS[4];
				g.setColor(color);

				// Desenha a célula
				g.fillRect(x, y, SIZE, SIZE);

				// Desenha a borda da célula
				g.setColor(Color.BLACK);
				g.drawRect(x, y, SIZE, SIZE);
			}
		}

		for (int row = 0; row < 6; row++) { // Vermelho base
			for (int col = 0; col < 6; col++) {
				int x = col * SIZE;
				int y = row * SIZE;
				color = COLORS[0];
				g.setColor(color);

				// Desenha a célula
				g.fillRect(x, y, SIZE, SIZE);

				// Desenha a borda da célula
				g.drawRect(x, y, SIZE, SIZE);

			}
		}
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 6 * SIZE, 6 * SIZE);

		for (int row = 0; row < 6; row++) { // Verde base
			for (int col = 9; col < 15; col++) {
				int x = col * SIZE;
				int y = row * SIZE;
				color = COLORS[1];
				g.setColor(color);
				// Desenha a célula
				g.fillRect(x, y, SIZE, SIZE);
				// Desenha a borda da célula
				g.drawRect(x, y, SIZE, SIZE);
			}

		}

		g.setColor(Color.BLACK);
		g.drawRect(432, 0, 6 * SIZE, 6 * SIZE);

		for (int row = 9; row < 15; row++) { // Azul base
			for (int col = 0; col < 6; col++) {
				int x = col * SIZE;
				int y = row * SIZE;
				color = COLORS[3];
				g.setColor(color);

				// Desenha a célula
				g.fillRect(x, y, SIZE, SIZE);

				// Desenha a borda da célula
				g.drawRect(x, y, SIZE, SIZE);

			}
		}
		g.setColor(Color.BLACK);
		g.drawRect(0, 432, 6 * SIZE, 6 * SIZE);

		for (int row = 9; row < 15; row++) { // Amarelo base
			for (int col = 9; col < 15; col++) {
				int x = col * SIZE;
				int y = row * SIZE;
				color = COLORS[2];
				g.setColor(color);

				// Desenha a célula
				g.fillRect(x, y, SIZE, SIZE);

				// Desenha a borda da célula
				g.drawRect(x, y, SIZE, SIZE);

			}
		}

		g.setColor(Color.BLACK);
		g.drawRect(432, 432, 6 * SIZE, 6 * SIZE);

		int row = 7;
		for (int col = 1; col < 6; col++) { // Caminho vitoria Vermelho

			int x = col * SIZE;
			int y = row * SIZE;
			color = COLORS[0];
			g.setColor(color);

			// Desenha a célula
			g.fillRect(x, y, SIZE, SIZE);

			// Desenha a borda da célula
			g.setColor(Color.BLACK);
			g.drawRect(x, y, SIZE, SIZE);
		}

		for (int col = 9; col < 14; col++) { // Caminho vitoria Amarelo
			int x = col * SIZE;
			int y = row * SIZE;
			color = COLORS[2];
			g.setColor(color);

			// Desenha a célula
			g.fillRect(x, y, SIZE, SIZE);

			// Desenha a borda da célula

			g.setColor(Color.BLACK);
			g.drawRect(x, y, SIZE, SIZE);

		}
		int col = 7;

		for (int row2 = 1; row2 < 6; row2++) { // Caminho vitoria Amarelo

			int x = col * SIZE;
			int y = row2 * SIZE;
			color = COLORS[1];
			g.setColor(color);

			// Desenha a célula
			g.fillRect(x, y, SIZE, SIZE);

			// Desenha a borda da célula

			g.setColor(Color.BLACK);
			g.drawRect(x, y, SIZE, SIZE);

		}
		for (int row2 = 9; row2 < 14; row2++) { // Caminho vitoria Azul

			int x = col * SIZE;
			int y = row2 * SIZE;
			color = COLORS[3];
			g.setColor(color);

			// Desenha a célula
			g.fillRect(x, y, SIZE, SIZE);

			// Desenha a borda da célula

			g.setColor(Color.BLACK);
			g.drawRect(x, y, SIZE, SIZE);

		}
		// Bolas base vermelho
		Graphics2D g2d = (Graphics2D) g;
		int circleX = 45; // Posição x do círculo
		int circleY = 45; // Posição y do círculo
		int circleSize = 50; // Tamanho do círculo

		for (int i = 0; i < 5; i++) {
			// Desenha o círculo preenchido
			g2d.setColor(Color.WHITE);
			g2d.fillOval(circleX, circleY, circleSize, circleSize);
			// Desenha o contorno do círculo
			g2d.setColor(Color.BLACK);
			g2d.drawOval(circleX, circleY, circleSize, circleSize);

			if (i == 1) {
				circleX += 140;
			}
			if (i == 2) {
				circleY += 120;
			}

			if (i == 3) {
				circleX = 45;
				circleY = 165;
			}

		}

		// Bolas base verde
		circleX = 45 + 435; // Posição x do círculo
		circleY = 45; // Posição y do círculo
		for (int i = 0; i < 5; i++) {
			// Desenha o círculo preenchido
			g2d.setColor(Color.WHITE);
			g2d.fillOval(circleX, circleY, circleSize, circleSize);
			// Desenha o contorno do círculo
			g2d.setColor(Color.BLACK);
			g2d.drawOval(circleX, circleY, circleSize, circleSize);

			if (i == 1) {
				circleX += 140;
			}
			if (i == 2) {
				circleY += 120;
			}

			if (i == 3) {
				circleX = 480;
				circleY = 165;
			}

		}
		// Bolas base verde
		circleX = 45; // Posição x do círculo
		circleY = 45 + 435; // Posição y do círculo
		for (int i = 0; i < 5; i++) {
			// Desenha o círculo preenchido
			g2d.setColor(Color.WHITE);
			g2d.fillOval(circleX, circleY, circleSize, circleSize);
			// Desenha o contorno do círculo
			g2d.setColor(Color.BLACK);
			g2d.drawOval(circleX, circleY, circleSize, circleSize);

			if (i == 1) {
				circleX += 140;
			}
			if (i == 2) {
				circleY += 120;
			}

			if (i == 3) {
				circleX = 45;
				circleY = 480 + 120;
			}

		}
		// Bolas base amarelo
		circleX = 45 + 435; // Posição x do círculo
		circleY = 45 + 435; // Posição y do círculo
		for (int i = 0; i < 5; i++) {
			// Desenha o círculo preenchido
			g2d.setColor(Color.WHITE);
			g2d.fillOval(circleX, circleY, circleSize, circleSize);
			// Desenha o contorno do círculo
			g2d.setColor(Color.BLACK);
			g2d.drawOval(circleX, circleY, circleSize, circleSize);

			if (i == 1) {
				circleX += 140;
			}
			if (i == 2) {
				circleY += 120;
			}

			if (i == 3) {
				circleX = 480;
				circleY = 480 + 120;
			}

		}

		// Casa inicio vermelho
		col = 1;
		row = 6;
		int x = col * SIZE;
		int y = row * SIZE;
		color = COLORS[0];
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE, SIZE);

		// Barreira Esq
		col = 1;
		row = 8;
		x = col * SIZE;
		y = row * SIZE;
		color = COLORS[5];
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE, SIZE);

		// Casa inicio amarelo
		col = 13;
		row = 8;
		x = col * SIZE;
		y = row * SIZE;
		color = COLORS[2];
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE, SIZE);

		// Barreira Dir
		col = 13;
		row = 6;
		x = col * SIZE;
		y = row * SIZE;
		color = COLORS[5];
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE, SIZE);

		// Casa inicio verde
		col = 8;
		row = 1;
		x = col * SIZE;
		y = row * SIZE;
		color = COLORS[1];
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE, SIZE);
		// Barreira Cima
		col = 6;
		row = 1;
		x = col * SIZE;
		y = row * SIZE;
		color = COLORS[5];
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE, SIZE);
		// Casa inicio azul
		col = 6;
		row = 13;
		x = col * SIZE;
		y = row * SIZE;
		color = COLORS[3];
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE, SIZE);
		// Barreira Baixo
		col = 8;
		row = 13;
		x = col * SIZE;
		y = row * SIZE;
		color = COLORS[5];
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, SIZE, SIZE);
		
		
		// Triangulos casa inicio verde
		int[] xs = {394,409,424};
		int[] ys = {58,85,58};
		g.setColor(Color.WHITE);
		g.fillPolygon(xs,ys,3);
		g.setColor(Color.BLACK);
		g.drawPolygon(xs,ys,3);
		
		// Triangulos casa inicio vermelho
		int[] xs2 = {60,90,60};
		int[] ys2 = {300,315,330};
		g.setColor(Color.WHITE);
		g.fillPolygon(xs2,ys2,3);
		g.setColor(Color.BLACK);
		g.drawPolygon(xs2,ys2,3);
		
		// Triangulos casa inicio amarelo
		int[] xs3 = {663,633,663};
		int[] ys3 = {394,409,424};
		g.setColor(Color.WHITE);
		g.fillPolygon(xs3,ys3,3);
		g.setColor(Color.BLACK);
		g.drawPolygon(xs3,ys3,3);
		
		// Triangulos casa inicio azul
		int[] xs4 = {295,310,325};
		int[] ys4 = {663,633,663};
		g.setColor(Color.WHITE);
		g.fillPolygon(xs4,ys4,3);
		g.setColor(Color.BLACK);
		g.drawPolygon(xs4,ys4,3);
		
		//Triangulo meio verde
		int[] xs5 = {287,360,432};
		int[] ys5 = {287,356,287};
		g.setColor(Color.GREEN);
		g.fillPolygon(xs5,ys5,3);
		g.setColor(Color.BLACK);
		g.drawPolygon(xs5,ys5,3);
		
		//Triangulo meio azul
		int[] xs6 = {287,360,432};
		int[] ys6 = {432,356,432};
		g.setColor(Color.BLUE);
		g.fillPolygon(xs6,ys6,3);
		g.setColor(Color.BLACK);
		g.drawPolygon(xs6,ys6,3);
		
		//Triangulo meio vermelho
		int[] xs7 = {287,360,287};
		int[] ys7 = {287,356,432};
		g.setColor(Color.RED);
		g.fillPolygon(xs7,ys7,3);
		g.setColor(Color.BLACK);
		g.drawPolygon(xs7,ys7,3);

		//Triangulo meio amarelo
		int[] xs8 = {432,360,432};
		int[] ys8 = {432,356,287};
		g.setColor(Color.YELLOW);
		g.fillPolygon(xs8,ys8,3);
		g.setColor(Color.BLACK);
		g.drawPolygon(xs8,ys8,3);

		Font font = new Font("Arial", Font.BOLD, 32);
		g.setFont(font);
		// Desenha o texto "A jogar"
		String text = "A jogar";
		int textX = 900; // Posição x do texto
		int textY = 310; // Posição y do texto
		g.drawString(text, textX, textY);
	}
	
	public void notify(Observavel o) {
		obs = o;
		lob = (Object []) obs.get();
		vez = (Integer) lob[0];
		val_dado = (Integer) lob[1];
		res = (Integer) lob[2];
		id_jogador = (Integer) lob[3];
		id_peca = (Integer) lob[4];
		qual_array = (Integer) lob[5];
		index_do_array = (Integer) lob[6];
	}
}


