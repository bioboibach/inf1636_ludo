package view;

import controller.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class LudoBoard extends JPanel{	
	private static 	LudoBoard 		instance;
	private static 	ViewAPI 		viewAPI = ViewAPI.getInstance();
	private 		Desenho 		desenho = Desenho.getInstance();
	private 		Menu 			menu 	= Menu.getInstance();
	private 		Moment 			moment 	= Moment.getInstance();
	
	// Dimensoes do tabuleiro
	private static final long 	serialVersionUID 	= 1L;
	private static final int 	BOARD_SIZE 			= 720;				// Tamanho do lado do tabuleiro
	private static final int 	SIZE 				= BOARD_SIZE / 15; 	// Tamanho de cada célula do tabuleiro
	private static final int 	WIDTH 				= 15; 				// Largura do tabuleiro em células
	private static final int 	HEIGHT 				= 15; 				// Altura do tabuleiro em células

	// Botoes
	private JButton newGameButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton launchDice;
	
	private JButton[] botoes = {
		newGameButton,
		loadButton,
		saveButton,
		launchDice
	};
	
	private JComboBox<String> dado;
	
 
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
		int [] 		rf 	= rfs[0];
		rf[0] = 1;
		rf[1] = 1;
		rf[2] = 1;
		rf[3] = 1;
		rf[4] = 1;
		rf[5] = 3;		
		
//		// Podio
//		podio[0][0] = 0; podio[0][1] = 10;
//		podio[1][0] = 2; podio[1][1] = 5;
//		podio[2][0] = 1; podio[2][1] = 8;
//		podio[3][0] = 3; podio[3][1] = 3;
		
		//Turno
//		turno  = 2;
		
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

	protected LudoBoard() {

		teste(); // ---------------------------------------------------------------------------------->>>>>> TESTE <<<<<<<<

		setPreferredSize(new Dimension(1200, 700)); // Tamanho da janela
		setLayout(null);
		
        desenho.setValues(HEIGHT, WIDTH, SIZE);
                
        // Menu
        menu.set(SIZE, BOARD_SIZE, botoes, this);
        menu.botoes();
        
        dadoManual();
	}

	
	//	Operacoes ---------------------------------------------
	private void dadoManual() {
		// Selecionador de dado manual para testes
			String[] val = new String[] { "1", "2", "3", "4", "5", "6"};
			dado = new JComboBox<String>(val);
	        dado.setBounds(835, 640,250,50);
	        dado.setFont(new Font("Arial", Font.PLAIN, 15));
	        dado.setForeground(Color.decode("#000000"));
	        dado.setBackground(Color.decode("#e9c28b"));
	        
	        add(dado);
	        // Adicionando o ActionListener ao combo box
	        dado.addActionListener(
	        		new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			                // Obtendo o valor selecionado no combo box
			                String selectedValue = (String) dado.getSelectedItem();
		                	menu.alterarImgDado(Integer.valueOf(selectedValue));
		                
			                viewAPI.set_dadosRolados(true);
//		                	// Exibindo o valor selecionado
		                	System.out.println("Valor selecionado: " + selectedValue);
	
			            }
	        		}
    		);
	        
//	        return dado;
	}
	
	
	
	//	Desenhar 
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int turno = moment.getTurno();
		
		menu.addToPanel(this);
		menu.carrega_img_dado(g, turno);
        
		// Executando as auxiliares
		desenho.draw_tabuleiro(g);
		desenho.draw_base(g);
		desenho.draw_caminho_vitoria(g);
		desenho.draw_bolas_brancas_bases(g);
		desenho.draw_casa_inicio(g);
		desenho.draw_barreira(g);
		desenho.draw_triangulo_inicio(g);
		desenho.draw_triangulo_meio(g);
		desenho.draw_msg_turno_atual(g);

		// Peoes
		desenho.draw_peoes(g);
		
		// Podio
////		TODO: Parar de pintar a caixa de dialogo do podio depois de clicar 'ok'
//			TODO: Colocar isso no lugar certo
//		if(podio != null) {
//			exibePodio();
//		}
		
	}
	

	//	Atualizacao do Board apos a execucao de um turno
	public void updateBoardInfo(Moment moment) {
//		casas_iniciais 		= (int[]) 	info[0];
//		path 				= (int[][]) info[1];
//		reta_final_vermelho = (int[]) 	info[2];
//		reta_final_azul 	= (int[]) 	info[3];
//		reta_final_amarelo 	= (int[]) 	info[4];
//		reta_final_verde 	= (int[]) 	info[5];
//		podio 				= (int[][]) info[6];
//		turno				= (int)		info[7];
		
		this.moment = moment;
		repaint();
	}
	

	//	Singleton ------------------------------------------
	public static LudoBoard getInstance() {
		if (instance == null) {
			instance = new LudoBoard();
		}
		return instance;
	}

}


