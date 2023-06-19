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

import javax.swing.JComboBox;

public class LudoBoard extends JPanel{

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
	
	private Controller_interface control = Controller_interface.getInstance();
	
//	Posicionamento
	int[][] coordsMapeadas = new int[52][2];				// Coordenadas do tabuleiros convertidas para a casa correspondente
	int[][] coordsMapeadasDesenho = new int[52][2];			// Coordenadas do tabuleiros convertidas para a coordenada da casa correspondente
	
	int[][] coordsMapeadas_vermelho = new int[6][2];		// Coordenadas do tabuleiros convertidas para a casa correspondente
	int[][] coordsMapeadasDesenho_vermelho = new int[6][2];	// Coordenadas do tabuleiros convertidas para a coordenada da casa correspondente
	
	int[][] coordsMapeadas_azul = new int[6][2];			// Coordenadas do tabuleiros convertidas para a casa correspondente
	int[][] coordsMapeadasDesenho_azul = new int[6][2];		// Coordenadas do tabuleiros convertidas para a coordenada da casa correspondente
	
	int[][] coordsMapeadas_amarelo = new int[6][2];			// Coordenadas do tabuleiros convertidas para a casa correspondente
	int[][] coordsMapeadasDesenho_amarelo = new int[6][2];	// Coordenadas do tabuleiros convertidas para a coordenada da casa correspondente
	
	int[][] coordsMapeadas_verde = new int[6][2];			// Coordenadas do tabuleiros convertidas para a casa correspondente
	int[][] coordsMapeadasDesenho_verde = new int[6][2];	// Coordenadas do tabuleiros convertidas para a coordenada da casa correspondente
	
	private int coord_x;
	private int coord_y;
	private int casa_x;
	private int casa_y;
	
	
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

		MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	            	
                coord_x = e.getX();
                coord_y = e.getY();
                casa_x = (int) (coord_x / SIZE);
                casa_y = (int) (coord_y / SIZE);
//                System.out.printf("Coordenadas: %d - %d\nPosicao: %d - %d\n", coord_x, coord_y, casa_x, casa_y);
                if (coord_x > 0 && coord_x < BOARD_SIZE && coord_y > 0 && coord_y < BOARD_SIZE) {
                	if(control.get_dadosRolados() == true) {
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

                		// Use the captured coordinates as needed
    		            // control.movimenta(pos, coord);
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
				lancarDado();
			}
		});
		add(launchDice);
		
//		Selecioandor de dado manual para testes
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

                // Exibindo o valor selecionado
                System.out.println("Valor selecionado: " + selectedValue);
            }
        });
        
		
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
		
		
		for(int i = 0; i < 52; i++) {
			coordsMapeadasDesenho[i][0] += coordsMapeadas[i][0]*SIZE + 24;
			coordsMapeadasDesenho[i][1] += coordsMapeadas[i][1]*SIZE + 24;
		}
	
		for(int i = 0; i < 6; i++) {
			coordsMapeadasDesenho_vermelho[i][0] += coordsMapeadas_vermelho[i][0]*SIZE + 24;
			coordsMapeadasDesenho_vermelho[i][1] += coordsMapeadas_vermelho[i][1]*SIZE + 24;
			coordsMapeadasDesenho_azul[i][0] += coordsMapeadas_azul[i][0]*SIZE + 24;
			coordsMapeadasDesenho_azul[i][1] += coordsMapeadas_azul[i][1]*SIZE + 24;
			coordsMapeadasDesenho_amarelo[i][0] += coordsMapeadas_amarelo[i][0]*SIZE + 24;
			coordsMapeadasDesenho_amarelo[i][1] += coordsMapeadas_amarelo[i][1]*SIZE + 24;
			coordsMapeadasDesenho_verde[i][0] += coordsMapeadas_verde[i][0]*SIZE + 24;
			coordsMapeadasDesenho_verde[i][1] += coordsMapeadas_verde[i][1]*SIZE + 24;
		}

	}

	
//	Operacoes ---------------------------------------------
	public void startNewGame() {
		System.out.println("Iniciando nova partida...");
	}

	public void loadSavedGame() {
		try {
			Controller_interface.getInstance().load_game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveGame() {
		try {
			Controller_interface.getInstance().save_game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void lancarDado() {
		Modal_interface.getInstance().run_turn();
		die_val = Modal_interface.getInstance().get_roll();
		turn = Modal_interface.getInstance().get_player_turn(); // remover dps TODO
		repaint();
	}
	
	public void drawBarreira(Graphics g){
			g.setColor(Color.BLACK);
			g.drawOval(casa_x, casa_y, 35,35);
			g.setColor(Color.GREEN);
			g.fillOval(casa_x, casa_y, 35,35);
			g.setColor(Color.RED);
			g.fillOval(casa_x, casa_y, 25,25);
		
	}


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
		
//		Desenha as pecas
		drawPieces(g);
	}
	
//	Implementacao da interface Observervavel ----------------
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


