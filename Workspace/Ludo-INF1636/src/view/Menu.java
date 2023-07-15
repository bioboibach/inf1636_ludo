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
	
	private static Menu instance;
	private static ControllerAPI control = ControllerAPI.getInstance(); 
	private static ViewAPI viewAPI = ViewAPI.getInstance(); 
	
	
	private static JPanel ludoBoard;
	
	private static final Color[] COLORS = ViewAPI.get_colors();
	
	static int SIZE;
	static int BOARD_SIZE;
	
	
	//	Posicionamento
	private static int[][][] 	arrCasasIniciais 	= new int[4][4][2];		// Cordenadas cartesianas das casas iniciais - Ex: (230, 480)
	private static int[][] 		arrPathIndex 		= new int[52][2];		// Coordenadas de indices das casas do path - Ex: (5, 20)
	private static int[][] 		arrPath		 		= new int[52][2];		// Coordenadas cartesianas das casas do path
	private static int[][][] 	arrRetasFinaisIndex	= new int[4][6][2];		// Coordenadas de indices das casas da reta final de todos os jogadores
	private static int[][][] 	arrRetasFinais	 	= new int[4][6][2];		// Coordenadas cartesianas das casas da reta final de todos os jogadores
	
	
	//	Posicao do click
	private static int coord_x;
	private static int coord_y;
	private static int casa_x;
	private static int casa_y;
	
	
	private Image diceImgs[] = new Image[6];
	private static int die_val = 1;			// valor do dado
	
	
	
	private static JButton newGameButton;
	private static JButton loadButton;
	private static JButton saveButton;
	private static JButton launchDiceButton;
	
	
	private static String[] val = new String[] { "1", "2", "3", "4", "5", "6"};
	private static JComboBox<String> dadoManual = new JComboBox<String>(val);
	
// ____________________________________________________________________________________________________________________________
//
			
	public Menu() {
		start_arr_casas_iniciais();
		start_arr_path();
		start_arr_path_bruto();
		start_arr_retas_finais_index();
		start_arr_retas_finais();
	}
	
	
	//	Dado
	protected void drawDiceImg(Graphics g, int turn){
		
		loadDiceImgs();
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setPaint(COLORS[turn]);
		Rectangle2D rt = new Rectangle2D.Double(890.0,350.0,150.0,150.0);
		g2D.fill(rt);
		
		g2D.drawImage(diceImgs[die_val-1], 915, 375, null);
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
	
	
	protected void botoes() {        
		//	Botoes do menu
		newGameButton = new JButton("Nova Partida");
		newGameButton.setBounds(835, 25, 250, 50); // Cordenada do botao
		newGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startNewGame();
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
		dadoManual.setBounds(835, 640,250,50);
        dadoManual.setFont(new Font("Arial", Font.PLAIN, 15));
        dadoManual.setForeground(Color.decode("#000000"));
        dadoManual.setBackground(Color.decode("#e9c28b"));
        dadoManual.addActionListener(new ActionListener() {
        	//@Override
	        public void actionPerformed(ActionEvent e) {
	            // Obtendo o valor selecionado no combo box
	            String selectedValue = (String) dadoManual.getSelectedItem();
	        	alterarImgDado(Integer.valueOf(selectedValue));
	        
	            viewAPI.set_dadosRolados(true);
	        	// Exibindo o valor selecionado
	        	System.out.println("Valor selecionado: " + selectedValue);
	        	System.out.println("Ok!");
	
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
                
                //	TODO: criar metodo para converter de coordenadas de indices para coordenadas cartesianas
                
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
    		            for(int i = 0; i < arrRetasFinaisIndex.length; i++) {
    		            	for(int k = 0; k < arrRetasFinaisIndex[i].length; k++) {
    		            		coords = arrRetasFinaisIndex[i][k];
    		            		if (coords[0] == casa_x && coords[1] == casa_y) {
    		            			color = i;
        		                	indice_final_path = k;
        		                    break;
        		                }
    		            	}
    		            }
    		            
    		            viewAPI.executaTurno(indice_path, indice_final_path, color); 

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
 		viewAPI.newGame();
	}
	private void loadSavedGame() {
		viewAPI.loadSavedGame();
	}
	private void saveGame() {
		viewAPI.SaveGame();
	}


	// SET ---------------------------------
	

	//	SET
	protected void setAll(int SIZE, int BOARD_SIZE, JPanel panel) {
		Menu.SIZE = SIZE;
		Menu.BOARD_SIZE = BOARD_SIZE;	
		
		ludoBoard = panel; // tirarrr??????? -----------------------------------------<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>
	}
	
	
	// REFERENCIAS -----------------------
    public static void addToPanel(LudoBoard panel) {
    	panel.add(newGameButton);
    	panel.add(loadButton);
    	panel.add(saveButton);
    	panel.add(launchDiceButton);
    	panel.add(dadoManual);
    	panel.addMouseListener(gera_mouseAdapter());
    }

    
	// Inicializacao padrao de todas as casas do tabuleiro 
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
	private void start_arr_path				() {
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
	private void start_arr_path_bruto		() {
		int[][] arr = new int[52][2];
		for(int i = 0; i < 52; i++) {
			arr[i][0] = arrPathIndex[i][0]*SIZE + 12;
			arr[i][1] = arrPathIndex[i][1]*SIZE + 12;
		}
		arrPath = arr;
	}
	private void start_arr_retas_finais		() {
		int[][][] arr = new int[4][6][2];
		for(int k = 0; k < arr.length; k++) {
			for(int i = 0; i < arr[k].length; i++) {
				arr[k][i][0] = arr[k][i][0]*SIZE + 12;
				arr[k][i][1] = arr[k][i][1]*SIZE + 12;
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
	
    //	Singleton ------------------------------------------
   	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}
}
