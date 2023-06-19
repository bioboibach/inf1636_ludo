package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

class Peca {
	private int id;		// Indice da peca pro player 
	private int cor;
	private int x;
	private int y;
	

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
			
		//	Azul
		coordInicial[8] = new int[] {57, 492};
		coordInicial[9] = new int[] {197, 492};
		coordInicial[10] = new int[] {57, 612};
		coordInicial[11] = new int[] {197, 612};
			
		//	Amarelo
		coordInicial[12] = new int[] {632, 312};
		coordInicial[13] = new int[] {632, 492};
		coordInicial[14] = new int[] {492, 492};
		coordInicial[15] = new int[] {492, 612};
	}
	
	protected int[][] coordInicial1 = new int[16][2];		// Coordenadas das casas iniciais
	
	private int[][] coordInicial = LudoBoard.getInstance().get_coordInicial();		// Coordenadas de todas as casas iniciais em ordem dos jogadores
	
	private int[][] coordsMapeadasDesenho			= LudoBoard.getInstance().get_coordsMapeadasDesenho();
	private int[][] coordsMapeadasDesenho_vermelho 	= LudoBoard.getInstance().get_coordsMapeadasDesenho_vermelho();
	private int[][] coordsMapeadasDesenho_azul		= LudoBoard.getInstance().get_coordsMapeadasDesenho_azul();
	private int[][] coordsMapeadasDesenho_amarelo 	= LudoBoard.getInstance().get_coordsMapeadasDesenho_amarelo();
	private int[][] coordsMapeadasDesenho_verde 	= LudoBoard.getInstance().get_coordsMapeadasDesenho_verde();
	
	private static final Color[] COLORS = { Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE, Color.BLACK };

	
	protected Peca(Graphics g, int id, int cor){
		this.id = id;
		this.cor = cor;
		x = coordInicial[4*cor + id][0];
		y = coordInicial[4*cor + id][1];
		
	}
	
	protected void draw_inicio(Graphics g) {
//		int x = coordInicial[4*cor + id][0];
//		int y = coordInicial[4*cor + id][1];
		x = coordInicial1[0][0];
		y = coordInicial1[0][1];
		
		System.out.println("CHECK --> ");
		System.out.println(x);
		System.out.println(y);
		
		x = 10;
		y = 10;
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 25, 25);
		g.setColor(Color.BLUE);
		g.fillOval(x, y, 25, 25);
	}


	protected void draw_peca(Graphics g, int indice) {
		x = coordsMapeadasDesenho[indice][0];
		y = coordsMapeadasDesenho[indice][1];
		
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 25, 25);
		g.setColor(COLORS[cor]);
		g.fillOval(x, y, 25, 25);
		
	}
	
	protected void draw_abrigo_stack(Graphics g, int indice, Peca p) {
		x = coordsMapeadasDesenho[indice][0];
		y = coordsMapeadasDesenho[indice][1];
		
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 35, 35);
		g.setColor(COLORS[cor]);
		g.fillOval(x, y, 35, 35);
		
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 25, 25);
		g.setColor(COLORS[p.get_cor()]);
		g.fillOval(x, y, 25, 25);
	}
	
	protected void draw_barreira(Graphics g, int indice) {
		x = coordsMapeadasDesenho[indice][0];
		y = coordsMapeadasDesenho[indice][1];
		
		g.setColor(COLORS[cor]);
		g.drawOval(x, y, 35, 35);
		g.setColor(Color.white);
		g.fillOval(x, y, 31, 31);
		
		g.setColor(Color.BLACK);
		g.drawOval(x, y, 25, 25);
		g.setColor(COLORS[cor]);
		g.fillOval(x, y, 25, 25);
	}
	
	protected void draw_finish(Graphics g, int qtd) {
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
		g.setFont(font);
		String text = Integer.toString(qtd);
		int textX = x; // Posição x do texto
		int textY = y; // Posição y do texto
		g.drawString(text, textX, textY);
		
		
	}
	
	
	protected int get_cor() {
		return cor;
	}
}


//
//protected Peca(int cor, int x, int y) {
//	
//}
//
//protected void draw_inicio(int cor, int indice) {
//	int x = coordInicial[indice + 4*cor][0];
//	int y = coordInicial[indice + 4*cor][1];
//	
//	g.setColor(Color.BLACK);
//	g.drawOval(x, y, 25, 25);
//	g.setColor(COLORS[cor]);
//	g.fillOval(x, y, 25, 25);
//}
//
//
//protected void draw_peca(int indice) {
//	int x = coordsMapeadasDesenho[indice][0];
//	int y = coordsMapeadasDesenho[indice][1];
//	
//	g.setColor(Color.BLACK);
//	g.drawOval(x, y, 25, 25);
//	g.setColor(COLORS[cor]);
//	g.fillOval(x, y, 25, 25);
//	
//}
//
//protected void draw_abrigo_stack(int cor1, int cor2, int indice) {
//	int x = coordsMapeadasDesenho[indice][0];
//	int y = coordsMapeadasDesenho[indice][1];
//	
//	g.setColor(Color.BLACK);
//	g.drawOval(x, y, 35, 35);
//	g.setColor(COLORS[cor1]);
//	g.fillOval(x, y, 35, 35);
//	
//	g.setColor(Color.BLACK);
//	g.drawOval(x, y, 25, 25);
//	g.setColor(COLORS[cor2]);
//	g.fillOval(x, y, 25, 25);
//}
//
//protected void draw_barreira(int indice, int cor) {
//	int x = coordsMapeadasDesenho[indice][0];
//	int y = coordsMapeadasDesenho[indice][1];
//	
//	g.setColor(COLORS[cor]);
//	g.drawOval(x, y, 35, 35);
//	g.setColor(Color.white);
//	g.fillOval(x, y, 31, 31);
//	
//	g.setColor(Color.BLACK);
//	g.drawOval(x, y, 25, 25);
//	g.setColor(COLORS[cor]);
//	g.fillOval(x, y, 25, 25);
//}
//
//protected void draw_finish(int indice, int qtd) {
//	int x = coordsMapeadasDesenho[indice][0];
//	int y = coordsMapeadasDesenho[indice][1];
//	
//	g.setColor(Color.BLACK);
//	g.drawOval(x, y, 25, 25);
//	g.setColor(COLORS[cor]);
//	g.fillOval(x, y, 25, 25);
//	
//	Font font = new Font("Arial", Font.BOLD, 14);
//	g.setFont(font);
//	String text = Integer.toString(qtd);
//	int textX = 900; // Posição x do texto
//	int textY = 310; // Posição y do texto
//	g.drawString(text, textX, textY);
//	
//	
//}
