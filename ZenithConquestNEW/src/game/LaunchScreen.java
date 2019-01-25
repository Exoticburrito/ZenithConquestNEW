package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LaunchScreen extends JFrame implements SharedData {

	JLabel bckHold;
	JButton easyBtn, hardBtn;
	
	public String difficulty;
	static Music mu = new Music();
	
	public LaunchScreen() {
		super("Zenith Conquest");
		mu.play("music/BGM.wav");
		setSize(550,550);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		bckHold = new JLabel(titleScr);
		setContentPane(bckHold);
		setLayout(new GridBagLayout());
		
		setIconImage(TASKBAR_ICON);
		GridBagConstraints gbc = new GridBagConstraints();
		
		Handler handler = new Handler();
		
		easyBtn = new JButton();
			easyBtn.addActionListener(handler);
			easyBtn.setIcon(easyImg);
			easyBtn.setBorder(BorderFactory.createEmptyBorder());
			easyBtn.setContentAreaFilled(false);
				gbc.gridy = 0;
			add(easyBtn,gbc);
		hardBtn = new JButton(hardImg);
			hardBtn.addActionListener(handler);
			hardBtn.setIcon(hardImg);
			hardBtn.setBorder(BorderFactory.createEmptyBorder());
			hardBtn.setContentAreaFilled(false);
				gbc.gridy = 1;
			add(hardBtn, gbc);

		setVisible(true);
	}
	

	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
            LaunchScreen ex = new LaunchScreen();
        });
	}
	
	
	public class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == easyBtn) {
				
				difficulty = "easy";
				InfoPrompt frm = new InfoPrompt(difficulty);
				dispose();
			} else if (e.getSource() == hardBtn) {
				difficulty = "hard";
				InfoPrompt frm = new InfoPrompt(difficulty);
				dispose();
			}
			
		}
	}



}