package view;

import controller.*;

import java.awt.*;
import javax.swing.*;

public class LudoBoard extends JPanel{	
	private static 	LudoBoard 		instance;
	private 		Desenho 		desenho = Desenho.getInstance();
	private 		Menu 			menu 	= Menu.getInstance();
	private 		Moment 			moment 	= Moment.getInstance();
	
	// Dimensoes do tabuleiro
	private static final long 	serialVersionUID 	= 1L;
	private static final int 	BOARD_SIZE 			= 720;				// Tamanho do lado do tabuleiro
	private static final int 	SIZE 				= BOARD_SIZE / 15; 	// Tamanho de cada célula do tabuleiro
	private static final int 	WIDTH 				= 15; 				// Largura do tabuleiro em células
	private static final int 	HEIGHT 				= 15; 				// Altura do tabuleiro em células

	//	Mapeamento das coordenadas
	private static int[][][] 	arrCasasIniciais 	= new int[4][4][2];		// Cordenadas cartesianas das casas iniciais - Ex: (230, 480)
	private static int[][] 		arrPathIndex 		= new int[52][2];		// Coordenadas de indices das casas do path - Ex: (5, 20)
	private static int[][] 		arrPath		 		= new int[52][2];		// Coordenadas cartesianas das casas do path
	private static int[][][] 	arrRetasFinaisIndex	= new int[4][6][2];		// Coordenadas de indices das casas da reta final de todos os jogadores
	private static int[][][] 	arrRetasFinais	 	= new int[4][6][2];		// Coordenadas cartesianas das casas da reta final de todos os jogadores
	
// ____________________________________________________________________________________________________________________________
//
		
	//	TESTES------------------------------------------------------------------------------------------
	private void teste() {		
		int[] 	casas_iniciais 		= moment.getCasasIniciais();
		int[][]	path				= moment.getPath();
		int[] 	reta_final_vermelho = moment.getRetaFinalVermelho();
		int[] 	reta_final_verde 	= moment.getRetaFinalVerde();
		int[] 	reta_final_amarelo 	= moment.getRetaFinalAmarelo();
		int[] 	reta_final_azul 	= moment.getRetaFinalAzul();
		int[][]	podio 				= moment.getPodio();
		int		turno				= moment.getTurno();
		
		// Casas iniciais
		casas_iniciais[0] = 1;
		casas_iniciais[1] = 2;
		casas_iniciais[2] = 0;
		casas_iniciais[3] = 3;
		
		// Path
		path[10][0] = 0; path[10][1] = -1;	// normal
		path[13][0] = 1; path[13][1] = 1;	// barreira
		path[23][0] = 3; path[23][1] = 2;	// stack
		
		path[30][0] = 3; path[30][1] = -1;	// stack
		
		// Retas finais
		int [][] 	rfs = {reta_final_vermelho, reta_final_verde, reta_final_amarelo, reta_final_azul}; 
		int [] 		rf 	= rfs[0];	// escolhe qual reta final voce quer adicionar pecas
		rf[0] = 1;
		rf[1] = 1;
		rf[2] = 1;
		rf[3] = 1;
		rf[4] = 1;
		rf[5] = 3;		
		
		// Podio
		podio[0][0] = 0; podio[0][1] = 10;
		podio[1][0] = 2; podio[1][1] = 5;
		podio[2][0] = 1; podio[2][1] = 8;
		podio[3][0] = 3; podio[3][1] = 3;
		
		//Turno
		turno  = 2;
		
		moment.setAll(
				casas_iniciais,
				path,
				reta_final_vermelho,
				reta_final_verde,
				reta_final_amarelo,
				reta_final_azul,
				podio,
				turno
				);
	}
	//	------------------------------------------------------------------------------------------------

	private LudoBoard() {
		
		//	Inicializando as arrarys de mapeamento
		start_arr_casas_iniciais();
		start_arr_path_index();
		start_arr_path();
		start_arr_retas_finais_index();
		start_arr_retas_finais();

		
		//	Tamanho da janela
		setPreferredSize(new Dimension(1200, 700)); 
		setLayout(null);
		
		//	Desenho
		desenho.setValues(HEIGHT, WIDTH, SIZE);
                
        // Menu
        menu.setAll(SIZE, BOARD_SIZE, this);
        menu.botoes();
        Menu.addButtonsToPanel();
        
        teste(); // ---------------------------------------------------------------------------------->>>>>> TESTE <<<<<<<<
	}

	
	//	Operacoes ---------------------------------------------
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int turno = moment.getTurno();
		
		menu.drawDiceImg(g, turno);
        
		// Executando as auxiliares
		desenho.draw_tabuleiro				(g);
		desenho.draw_base					(g);
		desenho.draw_caminho_vitoria		(g);
		desenho.draw_bolas_brancas_bases	(g);
		desenho.draw_casa_inicio			(g);
		desenho.draw_barreira				(g);
		desenho.draw_triangulo_inicio		(g);
		desenho.draw_triangulo_meio			(g);
		desenho.draw_msg_turno_atual		(g);

		// Peoes
		desenho.draw_peoes(g);
		
		// Podio
//		TODO: Parar de pintar a caixa de dialogo do podio depois de clicar 'ok'
//		TODO: Colocar isso no lugar certo
//		if(podio != null) {
//			desenho.showPodium();
//		}
		
	}

	//	Atualizacao do Board apos a execucao de um turno
	public void updateBoardInfo() {		
		this.moment = Moment.getInstance();
		repaint();
	}
	

	
	// Inicializacao das coordenadas das casas do tabuleiro
	private void start_arr_casas_iniciais	() {
		int[][][] arr = {
			//	Vermelho
			{
				{57, 57},
				{197, 57},
				{57, 177},
				{197, 177}
			},
			//	Verde
			{
				{492, 57},
				{632, 57},
				{632, 177},
				{492, 177}
			},
			//	Amarelo
			{
				{632, 612},
				{632, 492},
				{492, 492},
				{492, 612}
			},
			//	Azul
			{
				{57, 492},
				{197, 492},
				{57, 612},
				{197, 612}
			}			
		};
		arrCasasIniciais = arr;
	}
	private void start_arr_path_index		() {
		int[][] arr = {
				{1, 6},
				{0, 6},
				{0, 7},
				{0, 8},
				{1, 8},
				{2, 8},
				{3, 8},
				{4, 8},
				{5, 8},
				{6, 9},
				{6, 10},
				{6, 11},
				{6, 12},
				{6, 13},
				{6, 14},
				{7, 14},
				{8, 14},
				{8, 13},
				{8, 12},
				{8, 11},
				{8, 10},
				{8, 9},
				{9, 8},
				{10, 8},
				{11, 8},
				{12, 8},
				{13, 8},
				{14, 8},
				{14, 7},
				{14, 6},
				{13, 6},
				{12, 6},
				{11, 6},
				{10, 6},
				{9, 6},
				{8, 5},
				{8, 4},
				{8, 3},
				{8, 2},
				{8, 1},
				{8, 0},
				{7, 0},
				{6, 0},
				{6, 1},
				{6, 2},
				{6, 3},
				{6, 4},
				{6, 5},
				{5, 6},
				{4, 6},
				{3, 6},
				{2, 6}
		};
		
		arrPathIndex = arr;
	}
	private void start_arr_path		() {
		int[][] arr = new int[52][2];
		for(int i = 0; i < 52; i++) {
			arr[i][0] = arrPathIndex[i][0]*SIZE + 12;
			arr[i][1] = arrPathIndex[i][1]*SIZE + 12;
		}
		arrPath = arr;
	}
	private void start_arr_retas_finais		() {
		int[][][] arr = new int[4][6][2];
		arr = arrRetasFinaisIndex;
		for(int i = 0; i < arr.length; i++) {
			for(int k = 0; k < arr[i].length; k++) {
				arr[i][k][0] = arr[i][k][0]*SIZE + 12;
				arr[i][k][1] = arr[i][k][1]*SIZE + 12;
			}
		}
		arrRetasFinais = arr;
	}
	private void start_arr_retas_finais_index() {
		int[][][] arr = {
				// Vermelho
				{
					{1, 7},
					{2, 7},
					{3, 7},
					{4, 7},
					{5, 7},
					{6, 7}
				},
				// Verde
				{
					{7, 1},
					{7, 2},
					{7, 3},
					{7, 4},
					{7, 5},
					{7, 6}
				},
				// Amarelo
				{
					{13, 7},
					{12, 7},
					{11, 7},
					{10, 7},
					{9, 7},
					{8, 7},
				},
				// Azul
				{
					{7, 13},
					{7, 12},
					{7, 11},
					{7, 10},
					{7, 9},
					{7, 8}
				}			
			};
		arrRetasFinaisIndex = arr;
	}
	
	
	
	//	GET ------------------------------------------------
	protected int[][][] get_arrCasasIniciais() {
		return arrCasasIniciais;
	}
	protected int[][] 	get_arrPathIndex(){
		return arrPathIndex;
	}
	protected int[][] 	get_arrPath(){
		return arrPath;
	}
	protected int[][][] get_arrRetasFinaisIndex(){
		return arrRetasFinaisIndex;
	}
	protected int[][][] get_arrRetasFinais(){
		return arrRetasFinais;
	}
	
	
	//	Singleton ------------------------------------------
	public static LudoBoard getInstance() {
		if (instance == null) {
			instance = new LudoBoard();
		}
		return instance;
	}

}


