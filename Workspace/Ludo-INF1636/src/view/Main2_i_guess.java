package view;
import javax.swing.JFrame;

public class Main2_i_guess {
    public static void Main2_i_guess() {
        JFrame frame = new JFrame("Ludo Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LudoBoard board = new LudoBoard();
        frame.add(board);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Acessando variáveis e métodos de LudoBoard
        board.startNewGame();
        board.loadSavedGame();
        board.saveGame();
        
    }
}