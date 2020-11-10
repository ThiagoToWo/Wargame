package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import classes.ActionDialog;
import classes.Territory;

public class Tabuleiro extends JFrame {

	private HashMap<JButton, Territory> map = new HashMap<JButton, Territory>();
	private Territory[] territories = new Territory[2];
	private boolean dialogIsEnabled = true;
	private ActionDialog act;
	
	public static void main(String[] args) {
		new Tabuleiro();
	}
	
	public Tabuleiro() {
		setTitle("War Table");
		
		// painel de fundo
		JPanel background = new JPanel();
		background.setLayout(new GridLayout(8, 8));
		add(BorderLayout.CENTER, background);		
		
		// configura o tabuleiro
		for (int i = 0; i < 8; i++) { 
			for (int j = 0; j < 8; j++) {
				JButton jtb = new JButton();
				jtb.addActionListener(new ActionHandle());
				Territory territory = new Territory(jtb, i, j);
				map.put(jtb, territory);
				background.add(jtb);
			}			
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setSize(1000, 1000);
		setLocation(450, 10);
		setVisible(true);
	}

	
	
	public class ActionHandle implements ActionListener {
		
		JButton source;
		JButton target;
		
		@Override
		public void actionPerformed(ActionEvent e) {			
			
			if (dialogIsEnabled) {
				act = new ActionDialog(null, "Escolha a ação", true);
				
				switch (act.getState()) {
				case 1:
					source = (JButton) e.getSource(); // o botão clicado
					territories[0] = map.get(source); // o território do botão clicado
					territories[0].add(act.getNumberOfSoldiers());
					break;
				case 2:					
					source = (JButton) e.getSource(); // o botão clicado					
					territories[0] = map.get(source); // o território do botão clicado
					dialogIsEnabled = false;
					break;
				case 3:					
					source = (JButton) e.getSource(); // o botão clicado					
					territories[0] = map.get(source); // o território do botão clicado
					dialogIsEnabled = false;
					break;
				}
			} else {
				switch (act.getState()) {
				case 2:
					target = (JButton) e.getSource(); // o botão clicado
					territories[1] = map.get(target); // o território do botão clicado
					territories[0].attack(territories[1]);
					dialogIsEnabled = true;
					break;
				case 3:
					target = (JButton) e.getSource(); // o botão clicado
					territories[1] = map.get(target); // o território do botão clicado
					territories[0].move(territories[1]);
					dialogIsEnabled = true;
					break;
				}
			}			
		}
	}
}
