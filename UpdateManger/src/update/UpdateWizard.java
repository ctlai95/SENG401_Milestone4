package update;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UpdateWizard extends JPanel {
	
	private static final JPanel wizardCards = new JPanel(new CardLayout());
	private static final JPanel wizardControls = new JPanel(new CardLayout());
	
	public UpdateWizard() { 
		this.setPreferredSize(new Dimension(400, 500));
	}
	
	public static void createWizard() { 
		
		String next = "Next \u22b3"; 
		String back = "\u22b2 Back";
		String finish = "Finish";
		String cancel = "Cancel";
		
		
		JFrame frame = new JFrame("Update Wizard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		wizardCards.add(startPanel());
		
		wizardControls.add(startControl(next, cancel));
		wizardControls.add(defaultControl(back, next, cancel));
		
		frame.add(wizardCards, BorderLayout.CENTER);
		frame.add(wizardControls, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	private static JPanel startPanel() {
		
		UpdateWizard panelStart = new UpdateWizard();
		panelStart.setLayout(new BorderLayout());
		panelStart.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JLabel welcomeHeader = new JLabel("<html>Welcome to the Perfcake " + 
		"Update Wizard </html>", JLabel.CENTER);
		welcomeHeader.setFont(new Font("Sherif", Font.BOLD, 20));
		
		JLabel welcomeParagraph = new JLabel("<html> This wizard will guide you " +
		"through the update of Perfcake. <br><br> It is recommended you close " +
				"Perfcake before continuing this installation. Your scenario " +
				"and results will NOT be deleted.<br><br> " + 
				" Click Next to continue.</html>", JLabel.CENTER);
		
		panelStart.add(welcomeHeader, BorderLayout.NORTH);
		panelStart.add(welcomeParagraph, BorderLayout.CENTER);
		
		return panelStart;
	}
	

	
	private static JPanel startControl(String next, String cancel) {
		
		JPanel control = new JPanel(); 
		
		control.add(new JButton(new AbstractAction(next) {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) wizardControls.getLayout();
				cl.next(wizardControls);
			}
		}));
		
		control.add(new JButton(new AbstractAction(cancel) {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}));
		
		return control; 
	}
	
	
	private static JPanel defaultControl(String prev, String next, String cancel) {
		
		JPanel control = new JPanel(); 
		control.add(new JButton(new AbstractAction(prev) {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) wizardControls.getLayout();
				cl.previous(wizardControls);
			}
		}));
		
		control.add(new JButton(new AbstractAction(next) {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) wizardControls.getLayout();
				cl.next(wizardControls);
			}
		}));
		
		control.add(new JButton(new AbstractAction(cancel) {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}));
		
		return control; 
	}
	
	
	
	
	
}

