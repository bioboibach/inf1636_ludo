package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ControllerAPI;

public class Menu{
	
	private static ControllerAPI control = ControllerAPI.getInstance(); 
	private static ViewAPI viewAPI = ViewAPI.getInstance(); 
	private static Menu instance;
	
	
	private static JPanel ludoBoard;
	
	private static final Color[] COLORS = { Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE, Color.BLACK };
	static int SIZE;
	static int BOARD_SIZE;
	
	
//	Posicionamento
	// Coordenadas do tabuleiros convertidas para a casa correspondente
	static int[][] coordsMapeadas 			= new int[52][2];				
	static int[][] coordsMapeadas_vermelho 	= new int[6][2];		
	static int[][] coordsMapeadas_verde 	= new int[6][2];		
	static int[][] coordsMapeadas_amarelo 	= new int[6][2];			
	static int[][] coordsMapeadas_azul 		= new int[6][2];			
	
//	Posicao do click
	private static int coord_x;
	private static int coord_y;
	private static int casa_x;
	private static int casa_y;
	
	
	Image i[] = new Image[6];
	static int die_val = 1;			// valor do dado
	
	
	
	private static JButton newGameButton;
	private static JButton loadButton;
	private static JButton saveButton;
	private static JButton launchDice;
	
	
	public Menu() {}
	
	
	
	
	protected void carrega_img_dado(Graphics g, int turn){
		
		carrega_img_dado_bulk();
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setPaint(COLORS[turn]);
		Rectangle2D rt = new Rectangle2D.Double(890.0,350.0,150.0,150.0);
		g2D.fill(rt);
		
		g2D.drawImage(i[die_val-1], 915, 375, null);
	}
	
	private void carrega_img_dado_bulk(){
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
	}
	
	
	protected void botoes() {
        
		// Botoes do menu
		newGameButton = new JButton("Nova Partida");
		newGameButton.setBounds(835, 25, 250, 50); // Cordenada dos botoes
		newGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startNewGame();
			}
		});


		loadButton = new JButton("Carrega Partida");
		loadButton.setBounds(835, 115, 250, 50); // Cordenada do botoes
		loadButton.setFont(new Font("Arial", Font.PLAIN, 18));
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSavedGame();
			}
		});

		saveButton = new JButton("Salvar");
		saveButton.setBounds(835, 215, 250, 50); // Cordenada do botoes
		saveButton.setFont(new Font("Arial", Font.PLAIN, 18));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}
		});


		launchDice = new JButton("Lançar Dado");
		launchDice.setBounds(835, 530, 250, 50); // Cordenada dos botoes
		launchDice.setFont(new Font("Arial", Font.PLAIN, 18));
		launchDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarImgDado();
			}
		});
		
		
        
	}



	private static MouseAdapter gera_mouseAdapter() {
		MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {            	            	
                coord_x = e.getX();
                coord_y = e.getY();
                casa_x = (int) (coord_x / SIZE);
                casa_y = (int) (coord_y / SIZE);
                
                if (coord_x > 0 && coord_x < BOARD_SIZE && coord_y > 0 && coord_y < BOARD_SIZE) {
                	if(viewAPI.get_dadosRolados() == false) {
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
        
        return mouseAdapter;

	}
	
//	Lancamento e escolha manual
	public void alterarImgDado() {
		die_val = control.roll();
		viewAPI.set_dadosRolados(true);
		ludoBoard.repaint();
	}
	public static void alterarImgDado(int valor_escolhido) {
		die_val = valor_escolhido;
		viewAPI.set_dadosRolados(true);
		ludoBoard.repaint();
	}
	

	
//	Operacoes
	
 	private void startNewGame() {
		// TODO
		// Chamada da funcao do Model de Comecar um jogo novo
		System.out.println("Iniciando nova partida...");
	}

	private void loadSavedGame() {
		// TODO
		// Chamar a funcao de carregador jogo do model
		try {
			control.load_game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveGame() {
		// TODO
		// Chamar a funcao do Model de salvar o jogo
		try {
			control.save_game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	protected void set(int SIZE, int BOARD_SIZE, JButton[] botoes, JPanel panel) {
		Menu.SIZE = SIZE;
		Menu.BOARD_SIZE = BOARD_SIZE;
		
		newGameButton 	= botoes[0];
		loadButton 		= botoes[1];
		saveButton 		= botoes[2];
		launchDice 		= botoes[3];
		
		
		ludoBoard = panel;
		
	}
	
	
	
    public static void addToPanel(JPanel panel) {
    	panel.add(newGameButton);
    	panel.add(loadButton);
    	panel.add(saveButton);
    	panel.add(launchDice);
//    	panel.add(dado_manual());
    	panel.addMouseListener(gera_mouseAdapter());
    }
	
	
//	Singleton ------------------------------------------
   	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}
}
