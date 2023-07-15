package view;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;

import controller.ObservadoIF;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	
// ____________________________________________________________________________________________________________________________
//
			
	public Frame()  {
        JFrame frame = new JFrame("Ludo Board");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LudoBoard board = new LudoBoard();
        frame.add(board);

        frame.pack();
        frame.getContentPane().add(board);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
	}
}