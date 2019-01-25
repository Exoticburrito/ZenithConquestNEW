
package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class InfoPrompt extends JFrame implements ActionListener, SharedData {

	private JLabel backgroundHolder, uNamePrompt, shipPromptHolder, shipHolder, readyPrompt;
	private JTextField txt1;
	private JRadioButton ship1Rad, ship2Rad, ship3Rad;
	private JButton startButton;

	public String USER_NAME, difficulty;
	public static int shipNum = 0;

	public InfoPrompt(String difficulty) {
		super("Zenith Conquest");
		
		setSize(550, 550);

		this.difficulty = difficulty;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setIconImage(TASKBAR_ICON);

		if (difficulty == "easy") {
			backgroundHolder = new JLabel(infoBack);
			setContentPane(backgroundHolder);
		} else if (difficulty == "hard") {
			backgroundHolder = new JLabel(infoBackHARD);
			setContentPane(backgroundHolder);
		}

		uNamePrompt = new JLabel(namePrompt);
		uNamePrompt.setBounds(12, 25, 425, 25);
		add(uNamePrompt);
		txt1 = new JTextField(16);
		txt1.setBounds(50, 60, 300, 25);
		txt1.addActionListener(this);
		txt1.setBackground(new Color(200, 191, 231));
		add(txt1);

		shipPromptHolder = new JLabel(shipPrompt);
		shipPromptHolder.setBounds(12, 160, 425, 25);
		add(shipPromptHolder);

		ButtonGroup shipRads = new ButtonGroup();

		ship1Rad = new JRadioButton("Ship One: Galactacus");
		ship1Rad.setBounds(50, 200, 175, 25); // setBounds(x,y, width,height)
		ship1Rad.setFont(CALIBRI);
		ship1Rad.addActionListener(this);
		ship1Rad.setSelected(true);
		ship1Rad.setContentAreaFilled(false);
		ship1Rad.setForeground(Color.WHITE);
		shipRads.add(ship1Rad);
		add(ship1Rad);
		ship2Rad = new JRadioButton("Ship Two: Omega Ultron");
		ship2Rad.setBounds(50, 285, 190, 25);
		ship2Rad.setFont(CALIBRI);
		ship2Rad.addActionListener(this);
		ship2Rad.setContentAreaFilled(false);
		ship2Rad.setForeground(Color.WHITE);
		shipRads.add(ship2Rad);
		add(ship2Rad);
		ship3Rad = new JRadioButton("Ship Three: Gamma Odyssey");
		ship3Rad.setBounds(50, 370, 230, 25);
		ship3Rad.setFont(CALIBRI);
		ship3Rad.addActionListener(this);
		ship3Rad.setContentAreaFilled(false);
		ship3Rad.setForeground(Color.WHITE);
		shipRads.add(ship3Rad);
		add(ship3Rad);

		shipHolder = new JLabel(shipGalactacus);
		shipHolder.setBounds(270, 150, 300, 300);
		add(shipHolder);

		readyPrompt = new JLabel("");
		readyPrompt.setBounds(12, 425, 500, 100);
		readyPrompt.setFont(VCR_OSD); // if unable to get font working at school, change to Calibri
		readyPrompt.setForeground(Color.white);
		add(readyPrompt);
		startButton = new JButton("Locked n' Loaded");
		startButton.setBounds(350, 490, 160, 40);
		startButton.addActionListener(this);
		add(startButton);
		pack();

		// addKeyListener(this);

		setVisible(true);
	}

	public static ImageIcon setShipIcon(int source) {
		if (source == 1) {
			shipNum = 1;
			return shipGalactacus;

		} else if (source == 2) {
			shipNum = 2;
			return shipUltron;
		} else if (source == 3) {
			shipNum = 3;
			return shipGamma;
		} else {
			return null;
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == txt1) {
			USER_NAME = txt1.getText();
			readyPrompt.setText("Are you ready, Captain " + USER_NAME + "?");
		}

		if (e.getSource() == ship1Rad) {
			shipHolder.setIcon(setShipIcon(1));

		} else if (e.getSource() == ship2Rad) {
			shipHolder.setIcon(setShipIcon(2));

		} else if (e.getSource() == ship3Rad) {
			shipHolder.setIcon(setShipIcon(3));

		}
		if (e.getSource() == startButton) {
			EventQueue.invokeLater(() -> {
				GameInitializer ex = new GameInitializer(shipNum, difficulty,USER_NAME);
				ex.setVisible(true);
			});
			dispose();
		}

	}

}
