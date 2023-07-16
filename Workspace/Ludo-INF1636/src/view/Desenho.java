package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import controller.Moment;

public class Desenho {
	
	private static Desenho instance;
	private static final Color[] COLORS = ViewAPI.get_colors();
	private Moment moment = Moment.getInstance();
	private static LudoBoard ludoboard = LudoBoard.getInstance();

	// Dimensoes do tabuleiro
	private int HEIGHT;
	private int WIDTH;
	private int SIZE;		// Comprimento do lado de cada casa
	
	// Mapeamento das posicoes das casas
	private static int[][][] 	arrCasasIniciais 	= ludoboard.get_arrCasasIniciais();
	private static int[][] 		arrPath		 		= ludoboard.get_arrPath();
	private static int[][][] 	arrRetasFinais	 	= ludoboard.get_arrRetasFinais();
	
	// Quantidade de pecas na casa final de cada jogador
	private int[] 		qtds_finais			= new int[4];			
		
		
// ____________________________________________________________________________________________________________________________
//

	protected Desenho() {} 
	
	
	private void start_qtds_finais			() {
		for(int i = 0; i < qtds_finais.length; i++) {
			qtds_finais[i] = 0;
		}
	}
	
	// Define as dimensoes do tabuleiro
	protected void setValues(int HEIGHT, int WIDTH, int SIZE) {
		this.HEIGHT = HEIGHT;
		this.WIDTH = WIDTH;
		this.SIZE = SIZE;
		
		start_qtds_finais();
	} 
	// Define os valores para os atributos de referencia para o paint

	
	// Auxiliares do paintComponent()
	protected void draw_tabuleiro			(Graphics g) {
		// Desenha o tabuleiro
		Color color = Color.WHITE;
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
		}
	protected void draw_base				(Graphics g) {
		Color color;
		int[][] rows = {{0, 6}, {0, 6}, {9, 15}, {9, 15} };
		int[][] cols = {{0, 6}, {9, 15}, {9, 15}, {0, 6}};
		int[][] vals = {{0, 0}, {432, 0}, {432, 432}, {0, 432}};
	
		for(int k = 0; k < 4; k++) {
			for (int row = rows[k][0]; row < rows[k][1]; row++) {
				for (int col = cols[k][0]; col < cols[k][1]; col++) {
					int x = col * SIZE;
					int y = row * SIZE;
					color = COLORS[k];
					g.setColor(color);

					// Desenha a célula
					g.fillRect(x, y, SIZE, SIZE);

					// Desenha a borda da célula
					g.drawRect(x, y, SIZE, SIZE);

				}
			}
		g.setColor(Color.BLACK);
		g.drawRect(vals[k][0], vals[k][1], 6 * SIZE, 6 * SIZE);
		}
	}
	protected void draw_caminho_vitoria		(Graphics g) {
		Color color;
		// Caminho vitoria Vermelho e Amarelo
		for(int k = 0; k < 2; k++) {
			int row = 7;
			int[] cols = {1, 9};
			int[] vals = {0, 2};
			
			for (int col = cols[k]; col < cols[k] + 5; col++) { 

				int x = col * SIZE;
				int y = row * SIZE;
				color = COLORS[vals[k]];
				g.setColor(color);

				// Desenha a célula
				g.fillRect(x, y, SIZE, SIZE);

				// Desenha a borda da célula
				g.setColor(Color.BLACK);
				g.drawRect(x, y, SIZE, SIZE);
			}
		}
		
		// Caminho vitoria Azul e Verde
		for(int k = 0; k < 2; k++) {
			int col = 7;
			int[] rows = {1, 9};
			int[] vals = {1, 3};
			
			for (int row2 = rows[k]; row2 < rows[k] + 5; row2++) { // Caminho vitoria Amarelo

				int x = col * SIZE;
				int y = row2 * SIZE;
				color = COLORS[vals[k]];
				g.setColor(color);

				// Desenha a célula
				g.fillRect(x, y, SIZE, SIZE);

				// Desenha a borda da célula

				g.setColor(Color.BLACK);
				g.drawRect(x, y, SIZE, SIZE);

			}
		}
	}
	protected void draw_bolas_brancas_bases	(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		int circleSize = 50; // Tamanho do círculo

		
		int[][] circle = {{45, 45}, {45 + 435, 45}, {45, 45 + 435}, {45 + 435, 45 + 435}};
		int[][] circle_v2 = {{45, 165}, {45 + 435, 165}, {45, 45 + 435 + 120}, {45 + 435, 45 + 435 + 120}};
		
		for(int k = 0; k < 4; k++) {
			int circleX = circle[k][0]; // Posição x do círculo
			int circleY = circle[k][1]; // Posição y do círculo

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
					circleX = circle_v2[k][0];
					circleY = circle_v2[k][1];
				}

			}
		}
	}
	protected void draw_casa_inicio			(Graphics g){
		Color color;
		int[][] coords = {{1, 6}, {8, 1}, {13, 8}, {6, 13}};
		for(int k = 0; k < 4; k++) {
			int x = coords[k][0] * SIZE;
			int y = coords[k][1] * SIZE;
			color = COLORS[k];
			g.setColor(color);
			g.fillRect(x, y, SIZE, SIZE);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, SIZE, SIZE);
		}
		
	}
	protected void draw_barreira			(Graphics g) {
		Color color;
		int[][] coords = {{1, 8}, {13, 6}, {6, 1}, {8, 13}};
		
		for(int k = 0; k < 4; k++) {
			int col = coords[k][0];
			int row = coords[k][1];
			int x = col * SIZE;
			int y = row * SIZE;
			color = COLORS[5];
			g.setColor(color);
			g.fillRect(x, y, SIZE, SIZE);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, SIZE, SIZE);
		}
	}
	protected void draw_triangulo_inicio	(Graphics g) {
		int[][][] coords = {
				{{394,409,424}, {58,85,58}},
				{{60,90,60}, {300,315,330}},
				{{663,633,663}, {394,409,424}},
				{{295,310,325}, {663,633,663}}		
		};
		for(int k = 0; k < 4; k++) {
			int[] xs = coords[k][0];
			int[] ys = coords[k][1];
			g.setColor(Color.WHITE);
			g.fillPolygon(xs,ys,3);
			g.setColor(Color.BLACK);
			g.drawPolygon(xs,ys,3);
		}
	}
	protected void draw_triangulo_meio		(Graphics g) {
		int[][][] coords = {
				{{287,360,287}, {287,356,432}}, // red
				{{287,360,432}, {287,356,287}}, // green
				{{432,360,432}, {432,356,287}}, // yellow
				{{287,360,432}, {432,356,432}} 	// blue
		};
		
		for(int k = 0; k < 4; k++) {
			int[] xs = coords[k][0];
			int[] ys = coords[k][1];
			
			g.setColor(COLORS[k]);
			g.fillPolygon(xs,ys,3);
			g.setColor(Color.BLACK);
			g.drawPolygon(xs,ys,3);
		}
	}
	protected void draw_msg_turno_atual		(Graphics g) {
		Font font = new Font("Arial", Font.BOLD, 32);
		g.setFont(font);
		// Desenha o texto "A jogar"
		String text = "A jogar";
		int textX = 900; // Posição x do texto
		int textY = 310; // Posição y do texto
		g.drawString(text, textX, textY);
	}

	protected void draw_peoes				(Graphics g) {
		// Desenha os peoes durante o jogo
		draw_casas_iniciais(g);
		draw_path(g);
		draw_retas_finais(g);
		draw_qtds_finais(g);
	}

	
	// Auxiliares de draw_peoes()
	private void draw_casas_iniciais	(Graphics g) {
		Color color;
		int[] casas_iniciais = moment.getCasasIniciais();
		
		for(int i = 0; i < casas_iniciais.length; i++) {
			color = COLORS[i];
			
			for(int k = 0; k < casas_iniciais[i]; k++) {
				draw_peca(g, arrCasasIniciais[i][k], color, 0);
			}
		}
	}
	private void draw_path				(Graphics g) {
		Color color;
		int[][] path = moment.getPath();
		
		for(int i = 0; i < path.length; i++) {
			
			// Peao principal
			if(path[i][0] == -1) {
				// Se nao houver peoes na casa
				continue;
			}
			else {
				// Se houver, desenha o principal
				color = COLORS[path[i][0]];
				draw_peca(g, arrPath[i], color, 0);
			}
			
			// Peao secundario
			if(path[i][1] == -1) {
				// Se nao houver um segundo peao na casa
				continue;
			}
			else {
				if(path[i][0] != path[i][1]) {
					// Caso nao seja da mesma cor, desenha ele como stack 
					color = COLORS[path[i][1]];
					draw_peca(g, arrPath[i], color, 1);					
				}
				else {
					// Se houver e ele for da mesma cor que o principal, desenha ele como barreira
					color = COLORS[path[i][1]];
					draw_peca(g, arrPath[i], color, 2);
				}
				// Redesenha o peao principal
				color = COLORS[path[i][0]];
				draw_peca(g, arrPath[i], color, 0);
			}
			
			
		}
	}
	private void draw_retas_finais		(Graphics g) {
		Color color;
		int[] reta_final_vermelho 	= moment.getRetaFinalVermelho();
		int[] reta_final_verde 		= moment.getRetaFinalVerde();
		int[] reta_final_amarelo 	= moment.getRetaFinalAmarelo();
		int[] reta_final_azul 		= moment.getRetaFinalAzul();
		
		int[][] reta_final = {reta_final_vermelho, reta_final_verde, reta_final_amarelo, reta_final_azul};
		for(int k = 0; k < 4; k++) {
			color = COLORS[k].darker();
			for(int i = 0; i < reta_final[k].length; i++) {
				if(reta_final[k][i] >= 1) {
					qtds_finais[k] = reta_final[k][5];
					draw_peca(g, arrRetasFinais[k][i], color, 0);
				}
			}
		}
	}
	private void draw_peca				(Graphics g, int[] coords, Color cor, int tipo){
		// coords 	-> 	coordenadas cartesianas
		// tipo		-> 	tipo da casa:
		//				0 - nao eh peao secundario de stack nem barreira,
		//				1 - peao secundario de stack,
		//				2 - peao secundario de barreira
		int tam;
		if(tipo == 0) {
			tam = 25;
			g.setColor(Color.BLACK);
			g.drawOval(coords[0], coords[1], tam, tam);
			g.setColor(cor);
			g.fillOval(coords[0], coords[1], tam, tam);
		}
		else if(tipo == 1) {
			tam = 35;
			g.setColor(Color.BLACK);
			g.drawOval(coords[0] - 5, coords[1] - 5, tam, tam);
			g.setColor(cor);
			g.fillOval(coords[0] - 5, coords[1] - 5, tam, tam);
		}
		else if(tipo == 2) {
			tam = 35;
			g.setColor(cor);
			g.drawOval(coords[0] - 5, coords[1] - 5, 35, 35);
		}
				
	}
	private void draw_qtds_finais		(Graphics g) {
		// Imprime a quantidade de pecas na casa final
		Font font = new Font("Arial", Font.BOLD, 16);
		g.setFont(font);
		g.setColor(Color.BLACK);
		for(int i = 0; i < qtds_finais.length; i++){
			if(qtds_finais[i] == 0) {
				continue;
			}
			String text = String.valueOf(qtds_finais[i]);
			int textX = arrRetasFinais[i][5][0] + 8;
			int textY = arrRetasFinais[i][5][1] + 17;
			g.drawString(text, textX, textY);
		}
	}

	
	// Singelton ------------------------------------------
	public static Desenho getInstance() {
		if (instance == null) {
			instance = new Desenho();
		}
		return instance;
	}

}
