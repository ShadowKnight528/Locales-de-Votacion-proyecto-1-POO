package package_00;

import javax.swing.JFrame;
import java.awt.Color;

public class Ventana extends javax.swing.JFrame {
	
	public Ventana() {
		this.setSize(420, 420);
		this.setTitle("Titulo ventana");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.getContentPane().setBackground(new Color(10, 200, 200));
		this.setVisible(true);
	}
}
