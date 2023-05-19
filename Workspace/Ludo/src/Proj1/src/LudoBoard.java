import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LudoBoard extends JPanel {

    private static final int SIZE = 700/15; // Tamanho de cada célula do tabuleiro
    private static final int WIDTH = 15; // Largura do tabuleiro em células
    private static final int HEIGHT = 15; // Altura do tabuleiro em células

    private static final Color[] COLORS = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.WHITE};

    private JButton newGameButton;
    private JButton continueButton;

    public LudoBoard() {
       setPreferredSize(new Dimension(1200,700)); //Tamanho da janela
       setLayout(null);

        newGameButton = new JButton("Nova Partida");
        newGameButton.setBounds(950, 0, 250, 50); //Cordenada dos botoes
        newGameButton.setFont(new Font("Arial", Font.PLAIN, 18));
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });
        add(newGameButton);

        continueButton = new JButton("Continuar Partida");
        continueButton.setBounds(950, 150, 250, 50); //Cordenada do botOES
        continueButton.setFont(new Font("Arial", Font.PLAIN, 18));
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadSavedGame();
            }
        });
        add(continueButton);
    }

    public void startNewGame() {
        System.out.println("Iniciando nova partida...");
    }

    public void loadSavedGame() {
        File savedGameFile = new File("partida_salva.txt");
        if (savedGameFile.exists()) {
            System.out.println("Carregando partida salva...");
        } else {
            System.out.println("Nenhuma partida salva encontrada.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Color color;
        // Desenha o tabuleiro
        for (int row = 0; row < HEIGHT; row++) { //Branco
            for (int col = 0; col < WIDTH; col++) {
                int x = col * SIZE;
                int y = row * SIZE;
                
                //Define a cor de fundo da célula com base na posição
                color = COLORS[4]; 
                g.setColor(color);
                
                // Desenha a célula
                g.fillRect(x, y, SIZE, SIZE);

                // Desenha a borda da célula
                g.setColor(Color.BLACK);
                g.drawRect(x, y, SIZE, SIZE);
            }
        }
        
        for(int row = 0 ; row < 6; row++) { //Vermelho
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
        
        for(int row = 0 ; row < 6; row++) { //Verde
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
        
        for(int row = 9 ; row < 15; row++) { // Azul
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
        
        for(int row = 9 ; row < 15; row++) { //Amarelo
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
        
        int row = 7;
        	for(int col = 1; col <6; col++) { //Caminho vitoria Vermelho
        		
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
      
         	for(int col = 9; col <14; col++) { //Caminho vitoria Amarelo
         		
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
      
         	for(int row2 = 1; row2 <6; row2++) { //Caminho vitoria Amarelo
         		
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
         	for(int row2 = 9; row2 < 14; row2++) { //Caminho vitoria Azul
         		
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
         	
        // Desenha o retângulo cinza no local dos botões
        Rectangle buttonRectangle = new Rectangle(950, 0, 250, 750);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(buttonRectangle.x, buttonRectangle.y, buttonRectangle.width, buttonRectangle.height);
    }
}