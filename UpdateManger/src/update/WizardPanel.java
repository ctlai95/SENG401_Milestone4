package update;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Scanner;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class WizardPanel extends JPanel {

	private static final JPanel wizardCards = new JPanel(new CardLayout());
	private static final JPanel wizardControls = new JPanel(new CardLayout());
	
	private static JProgressBar bar = new JProgressBar();
	private static String filePath = System.getProperty("user.home"); 
	private static String next = "Next \u22b3";
	private static String back = "\u22b2 Back";
	private static String install = "Install";
	private static String finish = "Finish";
	private static String cancel = "Cancel";

	public WizardPanel() {
		this.setPreferredSize(new Dimension(500, 500));
	}

	public static void createWizard() {

		JFrame frame = new JFrame("Update Wizard");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bar.setMaximum(100000);
	
		wizardCards.add(startPanel());
		wizardCards.add(fileDirectoryPanel());
		wizardCards.add(updatePanel());
		wizardCards.add(endPanel());
		
		// TODO: switch to args... later on
		String[] start = {next, cancel};
		String[] basic = {back, next, cancel};
		String[] update = {back, install, cancel};
		String[] end = {finish, cancel};
	
		wizardControls.add(controlPanel(start));
		wizardControls.add(controlPanel(basic));
		wizardControls.add(controlPanel(update));
		wizardControls.add(controlPanel(end));
		frame.add(wizardCards, BorderLayout.CENTER);
		frame.add(wizardControls, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	/************************** Panels **************************/

	private static WizardPanel startPanel() {

		WizardPanel panelStart = new WizardPanel();
		panelStart.setLayout(new BorderLayout());
		panelStart.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JLabel welcomeHeader = new JLabel("<html> Welcome to the Perfcake " + "Update Wizard </html>");
		welcomeHeader.setFont(new Font("Sherif", Font.BOLD, 20));

		JLabel welcomeParagraph = new JLabel("<html> This wizard will guide you "
				+ "through the update of Perfcake. <br><br> It is recommended you close "
				+ "Perfcake before continuing this installation. Your scenario "
				+ "and results will NOT be deleted.<br><br> " + " Click Next to continue. </html>");

		panelStart.add(welcomeHeader, BorderLayout.NORTH);
		panelStart.add(welcomeParagraph, BorderLayout.CENTER);

		return panelStart;
	}

	private static WizardPanel endPanel() { 
		
		WizardPanel panelEnd = new WizardPanel();
		panelEnd.setLayout(new BorderLayout());
		panelEnd.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JLabel endHeader = new JLabel("<html> Completing the PerfCake Update Wizard </html>");
		endHeader.setFont(new Font("Sherif", Font.BOLD, 20));

		JLabel endParagraph = new JLabel("<html> Perfcake has been downloaded and unzipped in the folder. "
				+ "<br><br> Click Finish to close this wizard. </html>");

		panelEnd.add(endHeader, BorderLayout.NORTH);
		panelEnd.add(endParagraph, BorderLayout.CENTER);
		
		
		return panelEnd;
	}
	
	private static WizardPanel fileDirectoryPanel() {

		WizardPanel panel = new WizardPanel();
		panel.setLayout(new GridBagLayout());

		JLabel fileHeader = new JLabel("<html> Choose Perfcake " + "Directory </html>");
		fileHeader.setFont(new Font("Sherif", Font.BOLD, 20));

		JLabel fileParagraph = new JLabel("<html> Setup will remove Perfcake "
				+ "and upgrade in the following directory. To upgrade or install in a  "
				+ "different directory, click browse and select another folder. " + "Click Next to continue. </html>");

		JTextField textField = new JTextField(50);
		JFileChooser jfc = new JFileChooser();
		textField.setText(jfc.getCurrentDirectory().getPath());
		jfc.setDialogTitle("Choose Directory");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		JButton browseButton = new JButton(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(null)) {
					filePath = jfc.getSelectedFile().getPath();
					textField.setText(filePath);
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

		gc.gridy = 1;
		gc.weighty = 0.75;
		panel.add(fileParagraph, gc);

		gc.gridy = 2;
		gc.weighty = 1.0;
		gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.CENTER;
		panel.add(textField, gc);

		gc.gridx = 1;
		gc.weightx = 0.25;
		panel.add(browseButton, gc);

		return panel;

	}

	private static WizardPanel updatePanel() {
		
		WizardPanel panel = new WizardPanel();
		panel.setLayout(new GridBagLayout());
				
		JLabel updateHeader = new JLabel("<html> Download </html>");
		updateHeader.setFont(new Font("Sherif", Font.BOLD, 20));
		
		JLabel updateParagraph = new JLabel("<html> The application is being installed. Please wait... </html>");

		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(10, 10, 10, 10);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.25;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		panel.add(updateHeader, gc);

		gc.gridy = 1;
		gc.weighty = 0.75;
		panel.add(updateParagraph, gc);

		gc.gridy = 2;
		gc.weighty = 1.0;
		gc.gridwidth = 1;
		gc.anchor = GridBagConstraints.CENTER;
		panel.add(bar, gc);
	
		return panel;

	}
	
	
	// TODO Switch to ...args later on
	private static JPanel controlPanel(String[] buttons) {

		JPanel control = new JPanel();
		CardLayout clc = (CardLayout) wizardControls.getLayout();
		CardLayout clp = (CardLayout) wizardCards.getLayout();

		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].equals(next)) {
				control.add(new JButton(new AbstractAction(next) {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						clc.next(wizardControls);
						clp.next(wizardCards);
					}

				}));
			} else if (buttons[i].equals(back)) {
				JButton backButton = new JButton(new AbstractAction(back) {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						clc.previous(wizardControls);
						clp.previous(wizardCards);
					}
				});
				control.add(backButton);
			} else if (buttons[i].equals(finish) || buttons[i].equals(cancel)) {
				control.add(new JButton(new AbstractAction(buttons[i]) {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}

				}));
			} else if (buttons[i].equals(install)) {
				JButton updateButton = new JButton(new AbstractAction(install) {

					@Override
					public void actionPerformed(ActionEvent e) {
						UpdateManager um = new UpdateManager();
						JButton button = (JButton) e.getSource();
						button.setEnabled(false);	
						um.startDownload(filePath, bar);
					}
				});
				control.add(updateButton);
				clc.next(wizardControls);
				clp.next(wizardCards);
			}

		}

		return control;
	}
}
