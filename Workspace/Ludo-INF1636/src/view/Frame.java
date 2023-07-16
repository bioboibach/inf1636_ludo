package view;
import javax.swing.JFrame;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	
// ____________________________________________________________________________________________________________________________
//
			
	public Frame()  {
        JFrame frame = new JFrame("Ludo Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LudoBoard board = LudoBoard.getInstance();
        frame.add(board);

        frame.pack();
        frame.getContentPane().add(board);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
	}
}