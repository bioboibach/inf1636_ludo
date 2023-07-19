package view;

import java.awt.Color;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.ControllerAPI;


public class Menu{
	
	private static Menu instance;
	private static ViewAPI viewAPI = ViewAPI.getInstance(); 
	private static LudoBoard ludoBoard = LudoBoard.getInstance();
	private static ControllerAPI control = ControllerAPI.getInstance();
	
	private static LudoBoard panel;
	
	private static final Color[] COLORS = ViewAPI.get_colors();
	
	static int SIZE;
	static int BOARD_SIZE;

	//	Posicionamento
	private static int[][] 		arrPathIndex 		= ludoBoard.get_arrPathIndex();
	private static int[][][] 	arrRetasFinaisIndex	= ludoBoard.get_arrRetasFinaisIndex();	
	
	//	Posicao do click
	private static int coord_x;
	private static int coord_y;
	private static int casa_x;
	private static int casa_y;
	
	//	Imagens do dado
	private Image diceImgs[] = new Image[6];
	private static int diceValue = 1;
	
	//	Botoes 
	private static JButton newGameButton;
	private static JButton loadButton;
	private static JButton saveButton;
	private static JButton launchDiceButton;
	private static JButton diceManual1;
	private static JButton diceManual2;
	private static JButton diceManual3;
	private static JButton diceManual4;
	private static JButton diceManual5;
	private static JButton diceManual6;
	private static JButton[] manualDie;
	
	
// ____________________________________________________________________________________________________________________________
//
			
	public Menu() {
		manualDie = new JButton[6];
		manualDie[0] = diceManual1;
		manualDie[1] = diceManual2;
		manualDie[2] = diceManual3;
		manualDie[3] = diceManual4;
		manualDie[4] = diceManual5;
		manualDie[5] = diceManual6;
	}
	
	//	Dado
	protected void drawDiceImg(Graphics g, int player){
		
		loadDiceImgs();
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setPaint(COLORS[player]);
		Rectangle2D rt = new Rectangle2D.Double(890.0,350.0,150.0,150.0);
		g2D.fill(rt);
		
		g2D.drawImage(diceImgs[diceValue-1], 915, 375, null);
	}
	private void loadDiceImgs(){
		try {
			diceImgs[0] = ImageIO.read(new File("res/images/Dado1.png"));
			diceImgs[1] = ImageIO.read(new File("res/images/Dado2.png"));
			diceImgs[2] = ImageIO.read(new File("res/images/Dado3.png"));
			diceImgs[3] = ImageIO.read(new File("res/images/Dado4.png"));
			diceImgs[4] = ImageIO.read(new File("res/images/Dado5.png"));
			diceImgs[5] = ImageIO.read(new File("res/images/Dado6.png"));
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	public int alterarImgDado() {
		diceValue = roll();
		viewAPI.set_dadosRolados(true);
		panel.repaint();
		return diceValue;
	}
	public static void alterarImgDado(int valor_escolhido) {
		control.set_die_value(valor_escolhido);
		diceValue = valor_escolhido;
		viewAPI.set_dadosRolados(true);
		panel.repaint();
	}
	
	//	Botoes
	protected void botoes() {        
		//	Botoes do menu
		newGameButton = new JButton("Nova Partida");
		newGameButton.setBounds(835, 25, 250, 50); // Cordenada do botao
		newGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});

		loadButton = new JButton("Carrega Partida");
		loadButton.setBounds(835, 115, 250, 50);
		loadButton.setFont(new Font("Arial", Font.PLAIN, 18));
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadSavedGame();
			}
		});

		saveButton = new JButton("Salvar");
		saveButton.setBounds(835, 215, 250, 50);
		saveButton.setFont(new Font("Arial", Font.PLAIN, 18));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveGame();
			}
		});


		launchDiceButton = new JButton("Lançar Dado");
		launchDiceButton.setBounds(835, 530, 250, 50);
		launchDiceButton.setFont(new Font("Arial", Font.PLAIN, 18));
		launchDiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarImgDado();
			}
		});
		
		
		//	Selecionador manual do dado
		for (int i = 0; i < manualDie.length; i++) {
		    final int index = i + 1; // Cria uma variavel pra pegar o valor de 'i'
		    manualDie[i] = new JButton(String.valueOf(i + 1));
		    manualDie[i].setBounds(790 + 60 * i, 640, 50, 50);
		    manualDie[i].setFont(new Font("Arial", Font.PLAIN, 18));
		    manualDie[i].addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            alterarImgDado(index);
		            if(index == 5 || index == 6) {
						executaTurno(-2, -2, index);
					}
		        }
		    });
		}
 
	}

	//	Leitura do mouseclick no tabuleiro
	private static MouseAdapter gera_mouseAdapter() {
		MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {            	            	
                coord_x = e.getX();
                coord_y = e.getY();
                casa_x = (int) (coord_x / SIZE);
                casa_y = (int) (coord_y / SIZE);
                
                System.out.println("CLICK!");
               
                if (coord_x > 0 && coord_x < BOARD_SIZE && coord_y > 0 && coord_y < BOARD_SIZE) {
                	if(viewAPI.get_dadosRolados() == false) {
                		JOptionPane.showMessageDialog(null, "Jogue os dados antes de escolher o peao!");
                	}
                	else {
    		            int indice_path 		= -1;
    		            int indice_final_path 	= -1;
    		            int color 				= -1;
    		            
    		            int[] coords = new int[2];
    		            
    		            // Verifica os indices no Path e na Reta final correspondente
    		            //	Path
    		            for (int i = 0; i < arrPathIndex.length; i++) {
    		            	//	Busca o indice no array do path
    		                coords = arrPathIndex[i];
    		                if (coords[0] == casa_x && coords[1] == casa_y) {
    		                    indice_path = i;
    		                    break;
    		                }
    		            }
    		            
    		            //	Retas finais
    		            outerLoop: for(int i = 0; i < arrRetasFinaisIndex.length; i++) {
    		            	for(int k = 0; k < arrRetasFinaisIndex[i].length; k++) {
    		            		coords = arrRetasFinaisIndex[i][k];
    		            		if (coords[0] == casa_x && coords[1] == casa_y) {
    		            			color = i;
        		                	indice_final_path = k;
        		                    break outerLoop;
        		                }
    		            	}
    		            }
    		            
    		            executaTurno(indice_path, indice_final_path, diceValue); 

    		            // DEBUG
    		            System.out.printf("Coords Cartesianas: (%d, %d)\nCoords Indices: (%d, %d)\n", coord_x, coord_y, casa_x, casa_y);
		                System.out.println("PATH: (" + casa_x + ", " + casa_y + "): " + indice_path);
		                System.out.println("FINAL PATH: (" + casa_x + ", " + casa_y + "): " + indice_final_path);
                	}
                }
            }
        };
        
        return mouseAdapter;
	}
	
	//	Exibe o podio
	protected void showPodium(int[][] podio) {
	    String[] players = {"Vermelho", "Verde", "Amarelo", "Azul"};
	    
	    StringBuilder podiumMessage = new StringBuilder("Podio:\n");
	    for (int i = 0; i < podio.length; i++) {
	        int playerNumber = podio[i][0];
	        int score = podio[i][1];
	        podiumMessage.append("#" + (i + 1) + " Jogador	 ").append(players[playerNumber]).append(": ").append(score).append("\n");
	    }
	    
	    //	Exibe o icone do trofeu
	    ImageIcon icon = new ImageIcon("res/images/trophy.png");
	    
	    JOptionPane.showMessageDialog(null, podiumMessage.toString(), "Podium", JOptionPane.INFORMATION_MESSAGE, icon);
	}

	
	//	Operacoes -------------------------------------------
	private void newGame(){
		control.newGame();
	}
	private void loadSavedGame(){
		control.load_game();
	}
	private void saveGame() {
		control.save_game();
	}	
	protected int roll() {
		return control.roll();
	}

	private static void executaTurno(int indice_path, int indice_final_path, int dado) {
		control.executaTurno(indice_path, indice_final_path, dado);
	}		

	// REFERENCIAS -----------------------
	public static void addButtonsToPanel() {
		panel.add(newGameButton);
		panel.add(loadButton);
		panel.add(saveButton);
		panel.add(launchDiceButton);
		for(int i = 0; i < manualDie.length; i++) {
			panel.add(manualDie[i]);
		}
		panel.addMouseListener(gera_mouseAdapter());
	}
	
	// SET ---------------------------------
	protected void setAll(int SIZE, int BOARD_SIZE, LudoBoard panel) {
		Menu.SIZE = SIZE;
		Menu.BOARD_SIZE = BOARD_SIZE;
		
		Menu.panel = panel;
	}
	
	

    
    //	Singleton ------------------------------------------
   	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}
}
