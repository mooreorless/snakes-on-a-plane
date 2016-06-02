/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable, ActionListener {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 550;

	private JTextArea mainTextArea;
	private JPanel btnPanel;
	private JLabel lblButtons, lblSettings, lblFareClasses;
	private JButton btnRunSim, btnShowChart;


	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		createGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Todo
	}

	private void createGUI() {
		JFrame frame = new JFrame("Aircraft Simulator");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		//Styles
		String runSimLblStyle = "<html><h1 style='font-weight: bold; font-size: 1.1em; color: grey;'>Run Simulation</h1></html>";
		String operationsLblStyle = "<html><h1 style='font-weight: bold; font-size: 1.2em; color: green;'>Operations</h1></html>";


		mainTextArea = createTextArea();
		//btnRunSim = createButton("Run Simulation");
		btnRunSim = createButton(runSimLblStyle);
		btnShowChart = createButton("Show Chart");
		btnPanel = createPanel(Color.WHITE);

		lblButtons = createLabel(operationsLblStyle);
		lblSettings = createLabel("Simulation");
		lblFareClasses = createLabel("Fare Classes");



		frame.getContentPane().add(mainTextArea, BorderLayout.CENTER);
		frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);

// Don't add buttons here - use addToPanel method

		layoutButtonPanel();


		frame.repaint();
		frame.setVisible(true);
	}

	private JTextArea createTextArea() {
		JTextArea display = new JTextArea();
		display.setEditable(false);
		display.setLineWrap(true);
		//change text size later might be too big
		display.setFont(new Font("Arial", Font.BOLD, 24));
		display.setBorder(BorderFactory.createEtchedBorder());

		return display;
	}

	private JButton createButton(String str) {
		JButton button = new JButton();
		button.setText(str);
		button.addActionListener(this);

		return button;
	}

	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
		btnPanel.setLayout(layout);

		// add components to gridbag
		GridBagConstraints constraints = new GridBagConstraints();
		//Defaults
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.weightx = 100;
		constraints.weighty = 100;
		constraints.ipadx = 15;
		constraints.ipady = 15;


		addToPanel(btnPanel, lblButtons, constraints, 5, 1, 2, 1);

		addToPanel(btnPanel, btnRunSim, constraints, 5, 5, 2, 1);
		addToPanel(btnPanel, btnShowChart, constraints, 5, 2, 2, 1);

	}

	private void addToPanel(JPanel panel, Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;

		panel.add(c, constraints);
	}

	private JPanel createPanel(Color c) {
		JPanel panel = new JPanel();
		panel.setBackground(c);

		return panel;
	}

	private JLabel createLabel(String str) {
		JLabel label = new JLabel();
		label.setText(str);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		//label.setFont(new Font("Sans-serif", Font.BOLD, 18));
		label.setAlignmentX(0.5f);

		return label;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new GUISimulator("Aircraft Simulator"));

	}

}
