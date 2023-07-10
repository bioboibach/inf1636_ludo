package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Peca {
	private int id;		// Indice da peca pro player 
	private int cor;
	private int x;
	private int y;
	private int tipo = 0;
	
//	Posicionamento
	private static final int BOARD_SIZE = 720;			// Tamanho do lado do tabuleiro
	private static final int SIZE = BOARD_SIZE / 15; 	// Tamanho de cada célula do tabuleiro
	
	// Coordenadas do tabuleiros convertidas para a casa correspondente
	int[][] coordsMapeadas = new int[52][2];				
	int[][] coordsMapeadas_vermelho = new int[6][2];		
	int[][] coordsMapeadas_azul = new int[6][2];			
	int[][] coordsMapeadas_amarelo = new int[6][2];			
	int[][] coordsMapeadas_verde = new int[6][2];		
	
	// Coordenadas do tabuleiros convertidas para a coordenada da casa correspondente
	int[][] coordsMapeadasDesenho = new int[52][2];			
	int[][] coordsMapeadasDesenho_vermelho = new int[6][2];	
	int[][] coordsMapeadasDesenho_azul = new int[6][2];		
	int[][] coordsMapeadasDesenho_amarelo = new int[6][2];	
	int[][] coordsMapeadasDesenho_verde = new int[6][2];	
	
	protected int[][] coordInicial = new int[16][2];		// Coordenadas de todas as casas iniciais em ordem dos jogadores
	

	private static final Color[] COLORS = { Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE, Color.BLACK };

	
	protected Peca(Graphics g, int id, int cor){
		this.id = id;
		this.cor = cor;
		x = coordInicial[4*cor + id][0];
		y = coordInicial[4*cor + id][1];
		tipo = 0;
		start_arr_coordInicial();
		start_path_mapeado(); 		// Inicializa as casas do path
		start_final_path_mapeado(); // Inicializa as casas do final_path
		
	}
	
	protected void draw_inicio(Graphics g) {
		int x = coordInicial[4*cor + id][0];
		int y = coordInicial[4*cor + id][1];
		
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 25, 25);
		g.setColor(COLORS[cor]);
		g.fillOval(x, y, 25, 25);
	}


	protected void draw_peca(Graphics g, int indice_path, int indice_final_path) {
		
		if(tipo == 1) {
			g.setColor(Color.BLACK);
			g.drawOval(this.x, this.y, 25, 25);
			g.setColor(Color.BLACK);
			g.fillOval(this.x, this.y, 25, 25);
			
			int a = coordInicial[4*cor + id][0];
			int b = coordInicial[4*cor + id][1];			
			g.setColor(Color.WHITE);
			g.fillOval(a - 1, b - 1, 28, 28);
		}
		
		
		if(indice_final_path != -1) {
			if(cor == 0) {
				x = coordsMapeadasDesenho_vermelho[indice_final_path][0];
				y = coordsMapeadasDesenho_vermelho[indice_final_path][1];
			}
			else if(cor == 3){
				x = coordsMapeadasDesenho_azul[indice_final_path][0];
				y = coordsMapeadasDesenho_azul[indice_final_path][1];
			}
			else if(cor == 2) {
				x = coordsMapeadasDesenho_amarelo[indice_final_path][0];
				y = coordsMapeadasDesenho_amarelo[indice_final_path][1];
			}
			else if(cor == 1) {
				x = coordsMapeadasDesenho_verde[indice_final_path][0];
				y = coordsMapeadasDesenho_verde[indice_final_path][1];
			}
		
		}
		else {
			x = coordsMapeadasDesenho[indice_path][0];
			y = coordsMapeadasDesenho[indice_path][1];
		}
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 25, 25);
		g.setColor(COLORS[cor]);
		g.fillOval(x, y, 25, 25);
		tipo = 1;
		
	}
	
	protected void draw_abrigo_stack(Graphics g, int indice, Peca p) {
		
		if(tipo == 1) {
			g.setColor(Color.BLACK);
			g.drawOval(this.x, this.y, 25, 25);
			g.setColor(Color.BLACK);
			g.fillOval(this.x, this.y, 25, 25);
			
			int a = coordInicial[4*cor + id][0];
			int b = coordInicial[4*cor + id][1];			
			g.setColor(Color.WHITE);
			g.fillOval(a - 1, b - 1, 28, 28);
		}
		
		x = coordsMapeadasDesenho[indice][0];
		y = coordsMapeadasDesenho[indice][1];
		
		g.setColor(Color.BLACK);
		g.drawOval(x - 5, y - 5, 35, 35);
		g.setColor(COLORS[cor]);
		g.fillOval(x - 5, y - 5, 35, 35);
		
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 25, 25);
		g.setColor(COLORS[p.get_cor()]);
		g.fillOval(x, y, 25, 25);
		
		tipo = 1;
	}
	
	protected void draw_barreira(Graphics g, int indice) {
		
		if(tipo == 1) {
			g.setColor(Color.BLACK);
			g.drawOval(this.x, this.y, 25, 25);
			g.setColor(Color.BLACK);
			g.fillOval(this.x, this.y, 25, 25);
			
			int a = coordInicial[4*cor + id][0];
			int b = coordInicial[4*cor + id][1];			
			g.setColor(Color.WHITE);
			g.fillOval(a - 1, b - 1, 28, 28);
		}
		
		x = coordsMapeadasDesenho[indice][0];
		y = coordsMapeadasDesenho[indice][1];
		
		g.setColor(COLORS[cor]);
		g.drawOval(x - 5, y - 5, 35, 35);
		g.setColor(Color.white);
		g.fillOval(x, y, 25, 25);
		
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 25, 25);
		g.setColor(COLORS[cor]);
		g.fillOval(x, y, 25, 25);
		
		tipo = 1;
	}
	
	protected void draw_finish(Graphics g, int qtd) {
		
		if(tipo == 1) {
			g.setColor(Color.BLACK);
			g.drawOval(this.x, this.y, 25, 25);
			g.setColor(Color.BLACK);
			g.fillOval(this.x, this.y, 25, 25);
			
			int a = coordInicial[4*cor + id][0];
			int b = coordInicial[4*cor + id][1];			
			g.setColor(Color.WHITE);
			g.fillOval(a - 1, b - 1, 28, 28);
		}
		
		if(cor == 0) {
			x = coordsMapeadasDesenho_vermelho[5][0];
			y = coordsMapeadasDesenho_vermelho[5][1];
		}
		else if(cor == 1) {
			x = coordsMapeadasDesenho_azul[5][0];
			y = coordsMapeadasDesenho_azul[5][1];
		}
		else if(cor == 2) {
			x = coordsMapeadasDesenho_amarelo[5][0];
			y = coordsMapeadasDesenho_amarelo[5][1];
		}
		else if(cor == 3) {
			x = coordsMapeadasDesenho_verde[5][0];
			y = coordsMapeadasDesenho_verde[5][1];
		}
		
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 25, 25);
		g.setColor(COLORS[cor]);
		g.fillOval(x, y, 25, 25);
		
		Font font = new Font("Arial", Font.BOLD, 14);
		g.setColor(Color.BLACK);
		g.setFont(font);
		String text = Integer.toString(qtd);
		int textX = x + 9; // Posição x do texto
		int textY = y + 17; // Posição y do texto
		g.drawString(text, textX, textY);
		
		tipo = 1;	
	}
	
	
//	Inicializadores ------------------------------
	protected void start_arr_coordInicial() {
		//	Vermelho
		coordInicial[0] = new int[] {57, 57};
		coordInicial[1] = new int[] {197, 57};
		coordInicial[2] = new int[] {57, 177};
		coordInicial[3] = new int[] {197, 177};
			
		//	Verde
		coordInicial[4] = new int[] {492, 57};
		coordInicial[5] = new int[] {632, 57};
		coordInicial[6] = new int[] {632, 177};
		coordInicial[7] = new int[] {492, 177};
			
		//	Amarelo
		coordInicial[8] = new int[] {632, 612};
		coordInicial[9] = new int[] {632, 492};
		coordInicial[10] = new int[] {492, 492};
		coordInicial[11] = new int[] {492, 612};

		//	Azul
		coordInicial[12] = new int[] {57, 492};
		coordInicial[13] = new int[] {197, 492};
		coordInicial[14] = new int[] {57, 612};
		coordInicial[15] = new int[] {197, 612};
		
			
	}
	protected void start_path_mapeado() {
		coordsMapeadas[0] = new int[] {1, 6};
		coordsMapeadas[1] = new int[] {0, 6};
		coordsMapeadas[2] = new int[] {0, 7};
		coordsMapeadas[3] = new int[] {0, 8};
		coordsMapeadas[4] = new int[] {1, 8};
		coordsMapeadas[5] = new int[] {2, 8};
		coordsMapeadas[6] = new int[] {3, 8};
		coordsMapeadas[7] = new int[] {4, 8};
		coordsMapeadas[8] = new int[] {5, 8};
		coordsMapeadas[9] = new int[] {6, 9};
		coordsMapeadas[10] = new int[] {6, 10};
		coordsMapeadas[11] = new int[] {6, 11};
		coordsMapeadas[12] = new int[] {6, 12};
		coordsMapeadas[13] = new int[] {6, 13};
		coordsMapeadas[14] = new int[] {6, 14};
		coordsMapeadas[15] = new int[] {7, 14};
		coordsMapeadas[16] = new int[] {8, 14};
		coordsMapeadas[17] = new int[] {8, 13};
		coordsMapeadas[18] = new int[] {8, 12};
		coordsMapeadas[19] = new int[] {8, 11};
		coordsMapeadas[20] = new int[] {8, 10};
		coordsMapeadas[21] = new int[] {8, 9};
		coordsMapeadas[22] = new int[] {9, 8};
		coordsMapeadas[23] = new int[] {10, 8};
		coordsMapeadas[24] = new int[] {11, 8};
		coordsMapeadas[25] = new int[] {12, 8};
		coordsMapeadas[26] = new int[] {13, 8};
		coordsMapeadas[27] = new int[] {14, 8};
		coordsMapeadas[28] = new int[] {14, 7};
		coordsMapeadas[29] = new int[] {14, 6};
		coordsMapeadas[30] = new int[] {13, 6};
		coordsMapeadas[31] = new int[] {12, 6};
		coordsMapeadas[32] = new int[] {11, 6};
		coordsMapeadas[33] = new int[] {10, 6};
		coordsMapeadas[34] = new int[] {9, 6};
		coordsMapeadas[35] = new int[] {8, 5};
		coordsMapeadas[36] = new int[] {8, 4};
		coordsMapeadas[37] = new int[] {8, 3};
		coordsMapeadas[38] = new int[] {8, 2};
		coordsMapeadas[39] = new int[] {8, 1};
		coordsMapeadas[40] = new int[] {8, 0};
		coordsMapeadas[41] = new int[] {7, 0};
		coordsMapeadas[42] = new int[] {6, 0};
		coordsMapeadas[43] = new int[] {6, 1};
		coordsMapeadas[44] = new int[] {6, 2};
		coordsMapeadas[45] = new int[] {6, 3};
		coordsMapeadas[46] = new int[] {6, 4};
		coordsMapeadas[47] = new int[] {6, 5};
		coordsMapeadas[48] = new int[] {5, 6};
		coordsMapeadas[49] = new int[] {4, 6};
		coordsMapeadas[50] = new int[] {3, 6};
		coordsMapeadas[51] = new int[] {2, 6};
		
		
		
		for(int i = 0; i < 52; i++) {
			coordsMapeadasDesenho[i][0] += coordsMapeadas[i][0]*SIZE + 12;
			coordsMapeadasDesenho[i][1] += coordsMapeadas[i][1]*SIZE + 12;
		}
	}
	protected void start_final_path_mapeado() {
		coordsMapeadas_vermelho[0] = new int[] {1, 7};
		coordsMapeadas_vermelho[1] = new int[] {2, 7};
		coordsMapeadas_vermelho[2] = new int[] {3, 7};
		coordsMapeadas_vermelho[3] = new int[] {4, 7};
		coordsMapeadas_vermelho[4] = new int[] {5, 7};
		coordsMapeadas_vermelho[5] = new int[] {6, 7};
		
		coordsMapeadas_azul[0] = new int[] {7, 13};
		coordsMapeadas_azul[1] = new int[] {7, 12};
		coordsMapeadas_azul[2] = new int[] {7, 11};
		coordsMapeadas_azul[3] = new int[] {7, 10};
		coordsMapeadas_azul[4] = new int[] {7, 9};
		coordsMapeadas_azul[5] = new int[] {7, 8};
		
		coordsMapeadas_amarelo[0] = new int[] {13, 7};
		coordsMapeadas_amarelo[1] = new int[] {12, 7};
		coordsMapeadas_amarelo[2] = new int[] {11, 7};
		coordsMapeadas_amarelo[3] = new int[] {10, 7};
		coordsMapeadas_amarelo[4] = new int[] {9, 7};
		coordsMapeadas_amarelo[5] = new int[] {8, 7};
		
		coordsMapeadas_verde[0] = new int[] {7, 1};
		coordsMapeadas_verde[1] = new int[] {7, 2};
		coordsMapeadas_verde[2] = new int[] {7, 3};
		coordsMapeadas_verde[3] = new int[] {7, 4};
		coordsMapeadas_verde[4] = new int[] {7, 5};
		coordsMapeadas_verde[5] = new int[] {7, 6};
		
		for(int i = 0; i < 6; i++) {
			coordsMapeadasDesenho_vermelho[i][0] += coordsMapeadas_vermelho[i][0]*SIZE + 12;
			coordsMapeadasDesenho_vermelho[i][1] += coordsMapeadas_vermelho[i][1]*SIZE + 12;
			coordsMapeadasDesenho_azul[i][0] += coordsMapeadas_azul[i][0]*SIZE + 12;
			coordsMapeadasDesenho_azul[i][1] += coordsMapeadas_azul[i][1]*SIZE + 12;
			coordsMapeadasDesenho_amarelo[i][0] += coordsMapeadas_amarelo[i][0]*SIZE + 12;
			coordsMapeadasDesenho_amarelo[i][1] += coordsMapeadas_amarelo[i][1]*SIZE + 12;
			coordsMapeadasDesenho_verde[i][0] += coordsMapeadas_verde[i][0]*SIZE + 12;
			coordsMapeadasDesenho_verde[i][1] += coordsMapeadas_verde[i][1]*SIZE + 12;
		}
	}
	protected int get_cor() {
		return cor;
	}

//	Alteracao de atributos -----------------------
	protected void update_coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
