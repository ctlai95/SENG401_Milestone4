package update;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WizardPanel extends JPanel {
	
	private static final JPanel wizardCards = new JPanel(new CardLayout());
	private static final JPanel wizardControls = new JPanel(new CardLayout());
	
	public WizardPanel() { 
		this.setPreferredSize(new Dimension(500, 500));
	}
	
	public static void createWizard() { 
		
		String next = "Next \u22b3"; 
		String back = "\u22b2 Back";
		String finish = "Finish";
		String cancel = "Cancel";
		
		
		JFrame frame = new JFrame("Update Wizard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		wizardCards.add(startPanel());
		wizardCards.add(fileDirectoryPanel());
		
		wizardControls.add(startControl(next, cancel));
		wizardControls.add(defaultControl(back, next, cancel));
		
		frame.add(wizardCards, BorderLayout.CENTER);
		frame.add(wizardControls, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	
	
	/**************************Panels**************************/
	
	private static JPanel startPanel() {
		
		WizardPanel panelStart = new WizardPanel();
		panelStart.setLayout(new BorderLayout());
		panelStart.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JLabel welcomeHeader = new JLabel("<html> Welcome to the Perfcake " + 
		"Update Wizard </html>");
		welcomeHeader.setFont(new Font("Sherif", Font.BOLD, 20));
		
		JLabel welcomeParagraph = new JLabel("<html> This wizard will guide you " +
		"through the update of Perfcake. <br><br> It is recommended you close " +
				"Perfcake before continuing this installation. Your scenario " +
				"and results will NOT be deleted.<br><br> " + 
				" Click Next to continue. </html>");
		
		panelStart.add(welcomeHeader, BorderLayout.NORTH);
		panelStart.add(welcomeParagraph, BorderLayout.CENTER);
		
		return panelStart;
	}
	
	private static JPanel fileDirectoryPanel() {
		
		WizardPanel panel = new WizardPanel();
		panel.setLayout(new GridBagLayout());
		
		JLabel fileHeader = new JLabel("<html> Choose Perfcake "
				+ "Directory </html>");
		fileHeader.setFont(new Font("Sherif", Font.BOLD, 20));
		
		JLabel fileParagraph = new JLabel("<html> Setup will remove Perfcake " +
				"and upgrade in the following directory. To upgrade or install in a  " +
						"different directory, click browse and select another folder. " +
						"Click Next to continue. </html>");
		
		JTextField textField = new JTextField(50);
		JFileChooser jfc = new JFileChooser();
		textField.setText(jfc.getCurrentDirectory().getPath());
		jfc.setDialogTitle("Choose Directory");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		JButton browseButton = new JButton(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(null)) {
					textField.setText(jfc.getSelectedFile().getPath());
				}
			}
		});
		browseButton.setText("Browse");
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(10, 10, 10, 10);
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.25;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(fileHeader, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weighty = 0.75;
		panel.add(fileParagraph, gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weighty = 1.0;
		gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.CENTER;
		panel.add(textField,  gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weighty = 1.0;
		gc.weightx = 0.25;
		panel.add(browseButton, gc);
		
		
		return panel; 
		
	}
	
	private static JPanel downloadPanel() {
		
		
		return null; 
		
	}
	
	/**************************Controls**************************/

	
	private static JPanel startControl(String next, String cancel) {
		
		JPanel control = new JPanel(); 
		
		control.add(new JButton(new AbstractAction(next) {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout clc = (CardLayout) wizardControls.getLayout();
				clc.next(wizardControls);
				CardLayout clp = (CardLayout) wizardCards.getLayout();
				clp.next(wizardCards);
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
				CardLayout clc = (CardLayout) wizardControls.getLayout();
				clc.previous(wizardControls);
				CardLayout clp = (CardLayout) wizardCards.getLayout();
				clp.previous(wizardCards);
			}
		}));
		
		control.add(new JButton(new AbstractAction(next) {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout clc = (CardLayout) wizardControls.getLayout();
				clc.next(wizardControls);
				CardLayout clp = (CardLayout) wizardCards.getLayout();
				clp.next(wizardCards);
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

