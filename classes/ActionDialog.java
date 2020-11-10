package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ActionDialog extends JDialog {

	private int state;
	private int numberOfSoldiers;
	JRadioButton add;
	JRadioButton attack;
	JRadioButton move;
	JTextField numberTxt;
	
	public ActionDialog(JFrame parent, String title, boolean mode) {
		super(parent, title, mode);	
		
		JPanel background = new JPanel();
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
		add(background);
		
		add = new JRadioButton("Adicionar soldados");
		add.addActionListener(new EditableListener());
		attack = new JRadioButton("Atacar");
		attack.addActionListener(new IneditableListener());
		move = new JRadioButton("Mover");
		move.addActionListener(new IneditableListener());
		
		ButtonGroup group = new ButtonGroup();
		group.add(add);
		group.add(attack);
		group.add(move);
		
		background.add(add);
		background.add(attack);
		background.add(move);
		
		JPanel southPanel = new JPanel();
		numberTxt = new JTextField(2);
		numberTxt.setEditable(false);
		southPanel.add(new JLabel("Quantidade de soldados: "));
		southPanel.add(numberTxt);
		
		background.add(southPanel);
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(new OkListener());
		
		background.add(ok);
		
		pack();
		setLocation(800, 400);
		setVisible(true);
	}

	public int getState() {
		return state;
	}

	public int getNumberOfSoldiers() {
		return numberOfSoldiers;
	}


	private void setNumberOfSoldiers() {
		String n = numberTxt.getText();
		
		if (n.matches("[1 - 3]")) {
			numberOfSoldiers = Integer.parseInt(n);
		} else {
			JOptionPane.showMessageDialog(this, "Nenhuma ação pode ser feita com mais de 3 soldados.");
		}
	}
	
	
	
	public class EditableListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			numberTxt.setEditable(true);
		}

	}
	
	public class IneditableListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			numberTxt.setEditable(false);
		}

	}
	
	public class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (add.isSelected()) {
				state = 1;
				setNumberOfSoldiers();
				dispose();
			} else if (attack.isSelected()) {
				state = 2;
				numberTxt.setEditable(false);
				dispose();
			} else if (move.isSelected()) {
				state = 3;
				numberTxt.setEditable(false);
				dispose();
			}
		}

	}
}
