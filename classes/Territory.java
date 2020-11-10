package classes;

import java.awt.Color;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Territory {
	private int x;
	private int y;
	private int point;
	private int attackPoint;
	private int movePoint;
	private JButton button;
	private SecureRandom dice = new SecureRandom(); // dados para decisão de ataque e defesa

	public Territory(JButton button, int x, int y) {
		this.button = button;
		setPosition(x, y);
		setPoints(1);
		setTeam();
		button.isSelected();
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setTeam() {
		SecureRandom rnd = new SecureRandom();
		float value = rnd.nextFloat();

		if (value < 0.5) {
			button.setBackground(Color.cyan);
		} else {
			button.setBackground(Color.green);
		}
	}

	private int getPoint() {
		return this.point;
	}

	public void setPoints(int point) {
		button.setText("" + point);
		this.point = point;
	}

	public void setAttackPoint(int attackPoint) {
		this.attackPoint = attackPoint;
	}
	
	public void add(int soldiers) {
		point += soldiers;
		setPoints(point);
	}

	public void attack(Territory territory) {
		String soldiersUnderAttack = JOptionPane.showInputDialog("Escolha quantos usar para atacar: ");
		attackPoint = Integer.parseInt(soldiersUnderAttack);
		int otherPoint = territory.getPoint();

		for (int i = 0; i < attackPoint; i++) { // para cada soldado escolhido

			// rolar dois dados
			int dice1 = 1 + dice.nextInt(6);
			int dice2 = 1 + dice.nextInt(6);

			// se o dado1 perder e ainda tiver soldado na origem, retire um soldado
			if (dice1 <= dice2 && point >= 1) {
				point -= 1;
				setPoints(point);
			}

			// se o dado1 vencer e ainda tiver soldado no alvo, retire um soldado
			if (dice1 > dice2 && otherPoint >= 1) {
				otherPoint -= 1;
				territory.setPoints(otherPoint);
			}
		}

		// se eu eliminei todos do território inimigo, mova soldados para o território
		// derrotado
		if (otherPoint == 0) {
			move(territory);
		}
	}

	public void move(Territory territory) {
		String soldiersToMove = JOptionPane.showInputDialog("Escolha quantos mover: ");
		movePoint = Integer.parseInt(soldiersToMove);
		point -= movePoint;
		setPoints(point);
		int otherPoint = territory.getPoint();
		otherPoint += movePoint;
		territory.setPoints(otherPoint);

	}
}
