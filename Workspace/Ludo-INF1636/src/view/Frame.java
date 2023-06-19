package view;
import javax.swing.JFrame;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	public Frame() {
        JFrame frame = new JFrame("Ludo Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LudoBoard board = new LudoBoard();
        frame.add(board);

        frame.pack();
        frame.getContentPane().add(board);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Acessando variáveis e métodos de LudoBoard
//        board.startNewGame();
//        board.loadSavedGame();
//        board.saveGame();

    }
}