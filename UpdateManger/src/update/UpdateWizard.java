package update;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UpdateWizard extends JPanel {
	
	private static final JPanel wizardCards = new JPanel(new CardLayout());
	
	public UpdateWizard() { 
		this.setPreferredSize(new Dimension(800, 600));
	}
	
	public static void createWizard() { 
		
		String next = "Next \u22b3"; 
		String back = "\u22b2 Back";
		String finish = "Finish";
		String cancel = "Cancel";
		
		
		JFrame frame = new JFrame("Update Wizard");
		JPanel control = new JPanel(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		UpdateWizard panelStart = new UpdateWizard();
		panelStart.setLayout(new BorderLayout());
		JLabel welcomeHeader = new JLabel("Welcome to the Perfcake Update Wizard");
		panelStart.add(welcomeHeader, BorderLayout.NORTH);
		wizardCards.add(panelStart);
		
		
		
		control.add(new JButton(new AbstractAction(back) {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) wizardCards.getLayout();
				cl.previous(wizardCards);
			}
		}));
		
		control.add(new JButton(new AbstractAction(next) {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) wizardCards.getLayout();
				cl.next(wizardCards);
			}
		}));
		
		frame.add(wizardCards, BorderLayout.CENTER);
		frame.add(control, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
}

