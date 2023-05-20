package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Frame extends JFrame{
	final int LARG_DEFAULT = 1200;
	final int ALT_DEFAULT = 700;
	
	public Frame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int sl = screenSize.width;
		int sa = screenSize.height;
		int x = sl / 2 - LARG_DEFAULT / 2;
		int y = sa / 2 - ALT_DEFAULT / 2;
		setBounds(x, y, LARG_DEFAULT, ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(new Tabuleiro());
		setTitle("Ludo Board");
	}
}
