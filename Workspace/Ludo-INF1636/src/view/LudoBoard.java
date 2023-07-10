package view;

import controller.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import javax.swing.JComboBox;

public class LudoBoard extends JPanel implements BoardSubscriber{
	
	private static LudoBoard instance;
	private ControllerAPI control = ControllerAPI.getInstance(); 
	
	private static final long serialVersionUID = 1L;
	private static final int BOARD_SIZE = 720;			// Tamanho do lado do tabuleiro
	private static final int SIZE = BOARD_SIZE / 15; 	// Tamanho de cada célula do tabuleiro
	private static final int WIDTH = 15; 				// Largura do tabuleiro em células
	private static final int HEIGHT = 15; 				// Altura do tabuleiro em células

	private JComboBox<String> dado1;
	
	private static final Color[] COLORS = { Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE, Color.BLACK };
	
	private JButton newGameButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton launchDice;
	
	private ArrayList<Peca> arr_pecas = new ArrayList<Peca>();		// array com todas as pecas em ordem de cor (4 vermelho, 4 azul, ...)
	
//	Posicionamento
	// Coordenadas do tabuleiros convertidas para a casa correspondente
	int[][] coordsMapeadas = new int[52][2];				
	int[][] coordsMapeadas_vermelho = new int[6][2];		
	int[][] coordsMapeadas_azul = new int[6][2];			
	int[][] coordsMapeadas_amarelo = new int[6][2];			
	int[][] coordsMapeadas_verde = new int[6][2];		
	
//	Posicao do click
	private int coord_x;
	private int coord_y;
	private int casa_x;
	private int casa_y;
	
	Image i[] = new Image[6];
	int die_val = 1;
	int turn = 0;
	
	Observavel obs;
	Object lob[];

	int player;
	int val_dado;
	
	int id_jogador;
	int id_peca;
	int qual_array;
	int index_do_array;
	
	
//	Atributos de referencia para o paint
	private int[] 	casas_iniciais 		= new int[4];
	private int[][] path 				= new int[52][2];
	private int[] 	reta_final_vermelho = new int[6];
	private int[] 	reta_final_azul 	= new int[6];
	private int[] 	reta_final_amarelo 	= new int[6];
	private int[] 	reta_final_verde 	= new int[6];
	private int[][]	podio 				= new int[4][2];
	
	
	public LudoBoard() {

		MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	            	
                coord_x = e.getX();
                coord_y = e.getY();
                casa_x = (int) (coord_x / SIZE);
                casa_y = (int) (coord_y / SIZE);
                
                if (coord_x > 0 && coord_x < BOARD_SIZE && coord_y > 0 && coord_y < BOARD_SIZE) {
                	if(ViewAPI.getInstance().get_dadosRolados() == false) {
                		JOptionPane.showMessageDialog(null, "Jogue os dados antes de escolher o peao!");
                	}
                	else {
                		System.out.printf("Coordenadas: %d - %d\nPosicao: %d - %d\n", coord_x, coord_y, casa_x, casa_y);
                		
    		            int indice_path = -1;
    		            int indice_final_path = -1;

    		            for (int i = 0; i < coordsMapeadas.length; i++) {
//    		            	Busca o indice no array do path
    		                int[] coordinates = coordsMapeadas[i];
    		                if (coordinates[0] == casa_x && coordinates[1] == casa_y) {
    		                    indice_path = i;
    		                    break;
    		                }
    		            }
    		            for (int i = 0; i < coordsMapeadas_vermelho.length; i++) {
//    		                Busca o indice no array da reta_final_vermelho
    		            	int[] coordinates = coordsMapeadas_vermelho[i];
    		                if (coordinates[0] == casa_x && coordinates[1] == casa_y) {
    		                	indice_final_path = i;
    		                    break;
    		                }
//    		                Busca o indice no array da reta_final_azul
    		                coordinates = coordsMapeadas_azul[i];
    		                if (coordinates[0] == casa_x && coordinates[1] == casa_y) {
    		                	indice_final_path = i;
    		                    break;
    		                }
//    		                Busca o indice no array da reta_final_amarelo
    		                coordinates = coordsMapeadas_amarelo[i];
    		                if (coordinates[0] == casa_x && coordinates[1] == casa_y) {
    		                	indice_final_path = i;
    		                    break;
    		                }
//    		                Busca o indice no array da reta_final_verde
    		                coordinates = coordsMapeadas_verde[i];
    		                if (coordinates[0] == casa_x && coordinates[1] == casa_y) {
    		                	indice_final_path = i;
    		                    break;
    		                }
    		            }

		                System.out.println("PATH: Index of element with coordinates (" + casa_x + ", " + casa_y + "): " + indice_path);
		                System.out.println("FINAL PATH: Index of element with coordinates (" + casa_x + ", " + casa_y + "): " + indice_final_path);

//		                Envia o valor do dado e o peao selecionado ao Controller
    		             control.executaTurno(indice_path, indice_final_path, die_val);
    		            // control.imprimeTab();
		                
                	}
                }
            }
        };

        // Register the MouseListener with the panel
        this.addMouseListener(mouseAdapter);
        
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
				alterarImgDado();
			}
		});
		add(launchDice);
		
//		Selecionador de dado manual para testes
		String[] val = new String[] { "1", "2", "3", "4", "5", "6"};
		dado1 = new JComboBox<String>(val);
        dado1.setBounds(835, 640,250,50);
        dado1.setFont(new Font("Arial", Font.PLAIN, 15));
        dado1.setForeground(Color.decode("#000000"));
        dado1.setBackground(Color.decode("#e9c28b"));
        add(dado1);
        // Adicionando o ActionListener ao combo box
        dado1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtendo o valor selecionado no combo box
                String selectedValue = (String) dado1.getSelectedItem();
                alterarImgDado(Integer.valueOf(selectedValue));
                
                ViewAPI.getInstance().set_dadosRolados(true);
//                // Exibindo o valor selecionado
//                System.out.println("Valor selecionado: " + selectedValue);

            }
        });
        
        
        
//        TESTEEEEEEE---------------------------
        

//		update_board(g);
	}

	
//	Operacoes ---------------------------------------------
	public void startNewGame() {
		// TODO
		// Chamada da funcao do Model de Comecar um jogo novo
		System.out.println("Iniciando nova partida...");
	}

	public void loadSavedGame() {
		// TODO
		// Chamar a funcao de carregador jogo do model
		try {
			control.load_game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveGame() {
		// TODO
		// Chamar a funcao do Model de salvar o jogo
		try {
			control.save_game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	Lancamento e escolha manual
	public void alterarImgDado() {
		die_val = control.roll();
		ViewAPI.getInstance().set_dadosRolados(true);
		repaint();
	}
	public void alterarImgDado(int valor_escolhido) {
		die_val = valor_escolhido;
		ViewAPI.getInstance().set_dadosRolados(true);
		repaint();
	}
	

//	Desenhar -------------------------------------------------
	protected void start_draw(Graphics g) {
		// Inicializacoes
		
		start_arr_pecas(g);
		
////		Desenhas as pecas na posicao inicial
//		for(int i = 0; i < 16; i++) {
//			arr_pecas.get(i).draw_inicio(g);
//			repaint();
//		}
		
	}

	protected void start_arr_pecas(Graphics g) {
		for(int cor = 0; cor < 4; cor++) {
			for(int i = 0; i < 4; i++) {
				arr_pecas.add(new Peca(g, i, cor));
			}
		}
	}
	
	
	
	// Draw auxiliares
	private void draw_base(Graphics g, Color color) {
		int[][] rows = {{0, 6}, {0, 6}, {9, 15}, {9, 15} };
		int[][] cols = {{0, 6}, {9, 15}, {9, 15}, {0, 6}};
		int[][] vals = {{0, 0}, {432, 0}, {432, 432}, {0, 432}};
	
		for(int k = 0; k < 4; k++) {
			for (int row = rows[k][0]; row < rows[k][1]; row++) { // Vermelho base
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
	private void draw_caminho_vitoria(Graphics g, Color color) {
		
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
	private void draw_bolas_brancas_bases(Graphics g, Color color){
		
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
	private void draw_casa_inicio(Graphics g, Color color){
		int[][] coords = {{1, 6}, {8, 1}, {13, 8}, {6, 13}};
		
		int col = 1;
		int row = 6;
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
	private void draw_barreira(Graphics g, Color color) {
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
	private void draw_triangulo_inicio(Graphics g, Color color) {
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
	private void draw_triangulo_meio(Graphics g, Color color) {
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
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color color = null;		
		
		
//		TESTES---------------------------------------------
		casas_iniciais[0] = 1;
		casas_iniciais[1] = 4;
		casas_iniciais[2] = 2;
		casas_iniciais[3] = 3;
		
		
		path[3][0] = 1; path[3][1] = -1;
		
		
		podio[0][0] = 0; podio[0][1] = 10;
		podio[1][0] = 2; podio[1][1] = 5;
		podio[2][0] = 1; podio[2][1] = 8;
		podio[3][0] = 3; podio[3][1] = 3;
		
		
//		TESTES---------------------------------------------	
		
		
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
		
		// Executando as auxiliares		
		draw_base(g, color);
		draw_caminho_vitoria(g, color);
		draw_bolas_brancas_bases(g, color);
		draw_casa_inicio(g, color);
		draw_barreira(g, color);
		draw_triangulo_inicio(g, color);
		draw_triangulo_meio(g, color);


		Font font = new Font("Arial", Font.BOLD, 32);
		g.setFont(font);
		// Desenha o texto "A jogar"
		String text = "A jogar";
		int textX = 900; // Posição x do texto
		int textY = 310; // Posição y do texto
		g.drawString(text, textX, textY);
		
		start_draw(g);
	}

//	Funcoes get ----------------------------------------------


//	Implementacao da interface Observador ----------------
	public void notify(Observavel o) {
		Object[] info = (Object[]) o.get();
		
		casas_iniciais 		= (int[]) 	info[0];
		path 				= (int[][]) info[1];
		reta_final_vermelho = (int[]) 	info[2];
		reta_final_azul 	= (int[]) 	info[3];
		reta_final_amarelo 	= (int[]) 	info[4];
		reta_final_verde 	= (int[]) 	info[5];
		podio 				= (int[][]) info[6];
		
		if(podio != null) exibePodio();
		
		repaint();
	}
	
//	Pinta as casas do tabuleiro atualizadas
	public void update_board(Graphics g) {
//		Quantos peoes de cada jogador ja foram pintados		
		int[] count_peoes = {0, 0, 0, 0};
		
		paint_casas_iniciais(g, count_peoes);
		paint_path(g, count_peoes);
		paint_reta_final(g, count_peoes);	
	}
			
	public void paint_casas_iniciais(Graphics g, int[] count_peoes) {
		for(int k = 0; k < 4; k++) {
			for(int i = 4*k + count_peoes[k]; i < 4*k + casas_iniciais[k]; i++) {
				arr_pecas.get(i).draw_inicio(g);
				count_peoes[k]++;
//				repaint();
			}
		}
	}
	public void paint_path(Graphics g, int[] count_peoes) {
		int[] nova_coord = new int[2];
		
		for(int k = 0; k < 52; k++) {
			nova_coord = coordsMapeadas[k];
			
			int[] casa = path[k];
			if(casa[0] != -1) {
				if(casa[1] != -1) {
//					2 peoes na casa
					arr_pecas.get(4*casa[0] + count_peoes[casa[0]]).update_coord(nova_coord[0], nova_coord[1]);
					arr_pecas.get(4*casa[1] + count_peoes[casa[1]]).update_coord(nova_coord[0], nova_coord[1]);
					
					if(casa[0] == casa[1]) {
						if(1 == 2) {
//							Peoes iguais e a casa eh um abrigo
							arr_pecas.get(4*casa[0] + count_peoes[casa[0]]).draw_abrigo_stack(g, k, arr_pecas.get(4*casa[1] + count_peoes[casa[1]]));						
						}
						else {
//							Peoes iguais e a casa eh nao eh um abrigo
						}
						
					}
					else {
//						Peoes diferentes (soh pode ser abrigo porque conflitos ja foram verificados)
						arr_pecas.get(4*casa[0] + count_peoes[casa[0]]).draw_abrigo_stack(g, k, arr_pecas.get(4*casa[1] + count_peoes[casa[1]]));						
					}
					count_peoes[casa[0]]++;
					count_peoes[casa[1]]++;
				}
				else {
//					Um peao na casa
					System.out.println("count_peoes[casa[0]] = " + count_peoes[casa[0]] + "\n");
					arr_pecas.get(4*casa[0] + count_peoes[casa[0]]).update_coord(nova_coord[0], nova_coord[1]);
					arr_pecas.get(4*casa[0] + count_peoes[casa[0]]).draw_peca(g, k, -1);
					count_peoes[casa[0]]++;
				}
			}
			else {
//				Nenhum peao na casa
			}
		}
	}
	public void paint_reta_final(Graphics g, int[] count_peoes){}
	public void exibePodio() {
		String[] jogadores = {"vermelho", "azul", "amarelo", "verde"};
		if(podio != null) {
	        StringBuilder message = new StringBuilder("Podio:\n");
	        for (int i = 0; i < podio.length; i++) {
	        	int jog = podio[i][0];
	        	System.out.println("\njog: "+ jog + "\n");
	        	message.append("#").append(i + 1).append(": Player ").append(jogadores[jog]).append("  ---  ").append(podio[i][1]).append("\n");
	        }
	        JOptionPane.showMessageDialog(null, message.toString());
		}
	}

//	Implementacao Singleton ------------------------------------	
	public static LudoBoard getInstance() {
		if (instance == null) {
			instance = new LudoBoard();
		}
		return instance;
	}
}


