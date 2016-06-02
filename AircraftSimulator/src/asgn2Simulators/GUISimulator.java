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
import javax.swing.event.DocumentListener;

import asgn2Aircraft.AircraftException;
import asgn2Passengers.PassengerException;

/**
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements Runnable, ActionListener {

	private static final int WIDTH = 800; 
	private static final int HEIGHT = 550;

	private JTextArea mainTextArea;
	private JTextField rngSeedTxt, meanTxt, queueSizeTxt, cancelTxt,
	firstTxt, businessTxt, premiumTxt, economyTxt;
	private JLabel rngSeedLbl, meanLbl, queueSizeLbl, cancelLbl, simulationLbl, fareClassLbl,
										firstLbl, businessLbl, premiumLbl, economyLbl;
	private JPanel btnPnl, simPnl;
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
	}

	private void createGUI() {
		JFrame frame = new JFrame("Aircraft Simulator");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);

		//Styles
		String runSimBtnText = "<html><h1 style='font-weight: bold; font-size: 1em; color: grey; text-decoration: underline;'>Run Simulation</h1></html>";
		String showChartBtnText = "<html><h1 style='font-weight: bold; font-size: 1em; color: grey;'>Show Chart</h1></html>";

		String simulationLblStyle = "<html><h1 style='font-weight: bold; font-size: 1.1em; color: black;'>Simulation &nbsp;</h1></html>";
		String fareClassLblStyle = "<html><h1 style='font-weight: bold; font-size: 1.1em; color: black;'>Fare Classes &nbsp;</h1></html>";


		// On load
		mainTextArea = createTextArea("Run the simulation as is, or specify your own values below.");

		JScrollPane textAreaScrollable = new JScrollPane(mainTextArea);


		btnPnl = createPanel(Color.WHITE);
		simPnl = createPanel(Color.lightGray);

		btnRunSim = createButton(runSimBtnText);
		btnShowChart = createButton(showChartBtnText);

		rngSeedTxt = createTextField();
		meanTxt = createTextField();
		queueSizeTxt = createTextField();
		cancelTxt = createTextField();

		simulationLbl = createLabel(simulationLblStyle);

		rngSeedLbl = createLabel("RNG Seed");
		meanLbl = createLabel("Daily Mean");
		queueSizeLbl = createLabel("Queue Size");
		cancelLbl = createLabel("Cancellation");

		fareClassLbl = createLabel(fareClassLblStyle);

		firstTxt = createTextField();
		businessTxt = createTextField();
		premiumTxt = createTextField();
		economyTxt = createTextField();
		
		//Set defaults run sim values

		rngSeedTxt.setText("100");
		meanTxt.setText("1300.0");
		queueSizeTxt.setText("500");
		cancelTxt.setText("0.1");

		firstTxt.setText("0.03");
		businessTxt.setText("0.14");
		premiumTxt.setText("0.13");
		economyTxt.setText("0.7");

		firstLbl = createLabel("First");
		businessLbl = createLabel("Business");
		premiumLbl = createLabel("Premium");
		economyLbl = createLabel("Economy");

		layoutButtonPanel();

		btnShowChart.setEnabled(false);

		frame.getContentPane().add(textAreaScrollable, BorderLayout.PAGE_START);
		frame.getContentPane().add(btnPnl, BorderLayout.PAGE_END);
		frame.getContentPane().add(simPnl, BorderLayout.CENTER);


		// Don't add buttons here - use addToPanel method

		frame.repaint();
		frame.pack();
		frame.setVisible(true);
	}

	private JTextArea createTextArea(String str) {
		JTextArea display = new JTextArea(30, 2);
		display.setEditable(false);
		display.setLineWrap(true);
		//change text size later might be too big
		display.setText(str);
		display.setFont(new Font("Arial", Font.PLAIN, 16));
		display.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		display.setAlignmentX(LEFT_ALIGNMENT);
		
		return display;
	}

	private void layoutButtonPanel() {

		//Setup gridbags for separate button panels

		GridBagLayout btnLayout = new GridBagLayout();

		btnPnl.setLayout(btnLayout);
		
		// add components to gridbag
		GridBagConstraints btnConstraints = new GridBagConstraints();
		//Defaults
		btnConstraints.fill = GridBagConstraints.CENTER;
		btnConstraints.anchor = GridBagConstraints.SOUTH;
		btnConstraints.weightx = 100;
		btnConstraints.weighty = 100;
		btnConstraints.ipadx = 15;
		btnConstraints.ipady = 15;
		
		//Buttons
		addToPanel(btnPnl, btnRunSim, btnConstraints, 1, 2, 1, 1);
		addToPanel(btnPnl, btnShowChart, btnConstraints, 1, 1, 1, 1);

		//Simulation
		addToPanel(simPnl, simulationLbl, btnConstraints, 1, 1, 1, 1);

		addToPanel(simPnl, rngSeedLbl, btnConstraints, 1, 1, 1, 1);
		addToPanel(simPnl, rngSeedTxt, btnConstraints, 1, 1, 1, 1);

		addToPanel(simPnl, meanLbl, btnConstraints, 1, 1, 1, 1);
		addToPanel(simPnl, meanTxt, btnConstraints, 1, 1, 1, 1);

		addToPanel(simPnl, queueSizeLbl, btnConstraints, 1, 1, 1, 1);
		addToPanel(simPnl, queueSizeTxt, btnConstraints, 1, 1, 1, 1);

		addToPanel(simPnl, cancelLbl, btnConstraints, 1, 1, 1, 1);
		addToPanel(simPnl, cancelTxt, btnConstraints, 1, 1, 1, 1);

		//Fare Class
		addToPanel(simPnl, fareClassLbl, btnConstraints, 1, 1, 1, 1);

		addToPanel(simPnl, firstLbl, btnConstraints, 1, 1, 1, 1);
		addToPanel(simPnl, firstTxt, btnConstraints, 1, 1, 1, 1);

		addToPanel(simPnl, businessLbl, btnConstraints, 1, 1, 1, 1);
		addToPanel(simPnl, businessTxt, btnConstraints, 1, 1, 1, 1);

		addToPanel(simPnl, premiumLbl, btnConstraints, 1, 1, 1, 1);
		addToPanel(simPnl, premiumTxt, btnConstraints, 1, 1, 1, 1);

		addToPanel(simPnl, economyLbl, btnConstraints, 1, 1, 1, 1);
		addToPanel(simPnl, economyTxt, btnConstraints, 1, 1, 1, 1);
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
		panel.setMaximumSize(new Dimension(80, 25));

		return panel;
	}

	private JLabel createLabel(String str) {
		JLabel label = new JLabel();
		label.setText(str);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);

		return label;
	}

	private JTextField createTextField() {
		JTextField textField = new JTextField(5);

		return textField;
	}
	
	private JButton createButton(String str) {
		JButton button = new JButton();
		button.setText(str);
		button.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e){
            	
            	String s = validateAllFields();

            	if(s == "A"){
            		// do nothing
            	} else {
            		JOptionPane.showMessageDialog(null, "Error: " + s, "InfoBox: " + "Invalid input!", JOptionPane.INFORMATION_MESSAGE);
            		return;
            	}
            	
            	int seed = Integer.parseInt(rngSeedTxt.getText());
                int maxQueueSize = Integer.parseInt(queueSizeTxt.getText());
                double meanDailyBookings = Double.parseDouble(meanTxt.getText());
                double sdDailyBookings = 0.33*meanDailyBookings;
                double firstProb = Double.parseDouble(firstTxt.getText());
                double businessProb = Double.parseDouble(businessTxt.getText());
                double premiumProb = Double.parseDouble(premiumTxt.getText());
                double economyProb = Double.parseDouble(economyTxt.getText());
                double cancelProb = Double.parseDouble(cancelTxt.getText());
                
                try {
					Simulator sim = new Simulator(seed, maxQueueSize, meanDailyBookings, sdDailyBookings, firstProb, businessProb, premiumProb, economyProb, cancelProb);
	                mainTextArea.setText(runSim(sim));
                } catch (SimulationException | AircraftException | PassengerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        
            }
        });  
		button.setPreferredSize(new Dimension(220, 45));

		return button;
	}
	
	private String runSim(Simulator sim) throws AircraftException, PassengerException, SimulationException {
		
		String returnString = "";
		
		sim.createSchedule();
		//this.log.initialEntry(sim);
		returnString += "Start of simulation:\n";
		returnString += sim.toString() + "\n";
		String capacities = sim.getFlights(Constants.FIRST_FLIGHT).initialState();
		returnString += capacities;
		
		//Main simulation loop 
		for (int time = 0; time <= Constants.DURATION; time++) {
			sim.resetStatus(time); 
			sim.rebookCancelledPassengers(time);
			sim.generateAndHandleBookings(time);
			sim.processNewCancellations(time);
			if (time >= Constants.FIRST_FLIGHT) {
				sim.processUpgrades(time);
				sim.processQueue(time);
				sim.flyPassengers(time);
				sim.updateTotalCounts(time);
				//this.log.logFlightEntries(time, sim);
			} else {
				sim.processQueue(time);
			}
			//Log progress 
			//this.log.logQREntries(time, sim);
			//this.log.logEntry(time,this.sim);
			Boolean flying = (time >= Constants.FIRST_FLIGHT);
			returnString += sim.getSummary(time, flying);
		}
		sim.finaliseQueuedAndCancelledPassengers(Constants.DURATION); 
		//this.log.logQREntries(Constants.DURATION, sim);
		//this.log.finalise(sim);
		returnString += "\nEnd of Simulation\n";
		returnString += sim.finalState();
		
		return returnString;
	}
	
	/*
	 * Function is used to call all validate field functions. At any time, string s will either hold an error message
	 * or the string "A". The string "A" represents that all currently checked input has been "A"ccepted.
	 */
	
	private String validateAllFields(){
		String s = validateSeed();
		if(s != "A"){
			return s;
		}
		s = validateMean();
		if(s != "A"){
			return s;
		}
		s = validateQueue();
		if(s != "A"){
			return s;
		}
		s = validateCancel();
		if(s != "A"){
			return s;
		}
		s = validateFirst();
		if(s != "A"){
			return s;
		}
		s = validateBusiness();
		if(s != "A"){
			return s;
		}
		s = validatePremium();
		if(s != "A"){
			return s;
		}
		s = validateEconomy();
		if(s != "A"){
			return s;
		}
		s = validateProbability();
		return s;
	}
	
	/*
	 * Function used to check that input from the Seed textfield is not a string and is also greater than 0.
	 * Will return either an error message if input is invalid or "A" if input has been "A"ccepted.
	 */
	
	private String validateSeed(){
		try{
			int value = Integer.parseInt(rngSeedTxt.getText());
			if(value <= 0){
				return "Seed cannot be less than or equal to zero. ";
			} else {
				return "A";
			}
		} catch (java.lang.NumberFormatException e1) {
			return "Seed cannot be a string. ";
		}
	}
	
	/*
	 * Function used to check that input from the Mean textfield is not a string and is also greater than 0.
	 * Will return either an error message if input is invalid or "A" if input has been "A"ccepted.
	 */
	
	private String validateMean(){
		try{
			double value = Double.parseDouble(meanTxt.getText());
			if(value <= 0){
				return "Mean cannot be less than or equal to zero. ";
			} else {
				return "A";
			}
		} catch (java.lang.NumberFormatException e1) {
			return "Mean cannot be a string. ";
		}
	}
	
	/*
	 * Function used to check that input from the Queue textfield is not a string and is also greater than 0.
	 * Will return either an error message if input is invalid or "A" if input has been "A"ccepted.
	 */
	
	private String validateQueue(){
		try{
			int value = Integer.parseInt(queueSizeTxt.getText());
			if(value <= 0){
				return "Queue cannot be less than or equal to zero. ";
			} else {
				return "A";
			}
		} catch (java.lang.NumberFormatException e1) {
			return "Queue cannot be a string. ";
		}
	}
	
	/*
	 * Function used to check that input from the Cancel textfield is not a string and is also greater than 0.
	 * Will return either an error message if input is invalid or "A" if input has been "A"ccepted.
	 */
	
	private String validateCancel(){
		try{
			double value = Double.parseDouble(cancelTxt.getText());
			if(value <= 0){
				return "Cancel cannot be less than or equal to zero. ";
			} else {
				return "A";
			}
		} catch (java.lang.NumberFormatException e1) {
			return "Cancel cannot be a string. ";
		}
	}
	
	/*
	 * Function used to check that input from the First textfield is not a string and input is between 0 and 1.
	 * Will return either an error message if input is invalid or "A" if input has been "A"ccepted.
	 */
	
	private String validateFirst(){
		try{
			double value = Double.parseDouble(firstTxt.getText());
			if(value < 0 || value > 1){
				return "First probability must be between 0 and 1 ";
			} else {
				return "A";
			}
		} catch (java.lang.NumberFormatException e1) {
			return "First probability cannot be a string. ";
		}
	}
	
	/*
	 * Function used to check that input from the Business textfield is not a string and input is between 0 and 1.
	 * Will return either an error message if input is invalid or "A" if input has been "A"ccepted.
	 */
	
	private String validateBusiness(){
		try{
			double value = Double.parseDouble(businessTxt.getText());
			if(value < 0 || value > 1){
				return "Business probability must be between 0 and 1 ";
			} else {
				return "A";
			}
		} catch (java.lang.NumberFormatException e1) {
			return "Business probability cannot be a string. ";
		}
	}
	
	/*
	 * Function used to check that input from the Premium textfield is not a string and input is between 0 and 1.
	 * Will return either an error message if input is invalid or "A" if input has been "A"ccepted.
	 */
	
	private String validatePremium(){
		try{
			double value = Double.parseDouble(premiumTxt.getText());
			if(value < 0 || value > 1){
				return "Premium probability must be between 0 and 1 ";
			} else {
				return "A";
			}
		} catch (java.lang.NumberFormatException e1) {
			return "Premium probability cannot be a string. ";
		}
	}
	
	/*
	 * Function used to check that input from the Economy textfield is not a string and input is between 0 and 1.
	 * Will return either an error message if input is invalid or "A" if input has been "A"ccepted.
	 */
	
	private String validateEconomy(){
		try{
			double value = Double.parseDouble(economyTxt.getText());
			if(value < 0 || value > 1){
				return "Economy probability must be between 0 and 1 ";
			} else {
				return "A";
			}
		} catch (java.lang.NumberFormatException e1) {
			return "Economy probability cannot be a string. ";
		}
	}
	
	/*
	 * Function used to check that all culmative probabilities are equal to 100. 
	 * Will return an error message if the probability total is invalid, otherwise will return "A".
	 */
	
	private String validateProbability(){
		
		double first = Double.parseDouble(firstTxt.getText());
		double business = Double.parseDouble(businessTxt.getText());
		double premium = Double.parseDouble(premiumTxt.getText());
		double economy = Double.parseDouble(economyTxt.getText());
		double totalProbability = first + business + premium + economy;
		double validProbability = 1.0;
		
		if(totalProbability == validProbability){
			return "A";
		} else {
			return "Invalid probabilities. Total class probabilities must equal 100.";
		}
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
