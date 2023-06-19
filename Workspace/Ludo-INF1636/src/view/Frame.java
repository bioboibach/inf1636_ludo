package view;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFrame;

import controller.Observavel;

public class Frame extends JFrame implements Observavel {

	private static final long serialVersionUID = 1L;

	
	List<Observador> observers = new ArrayList<Observador>();
	
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
	public void addObservador(Observador o) {
		observers.add(o);
	}
	public void removeObservador(Observador o) {
		observers.remove(o);
	}
    public void notifyObservers() {
        ListIterator<Observador> li = observers.listIterator();
        while(li.hasNext()) {
        	li.next().notify(this);
        	repaint();
        }
    }
    public Object[] get() {
    	Object dados[] = new Object[6];
    	return dados;
    }
}