package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import airport.Airport;
import airport.BoardingRoom;
import airport.Gateway;
import flights.Arrival;
import flights.Departure;
import flights.Flight;
import flights.Stepover;

import javax.swing.JScrollPane;

public class Window extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	
	private JButton newDeparture = new JButton("Add departure");
	private JButton newArrival = new JButton("Add arrival");
	private JButton newStepover = new JButton("Add stepover");
	private JButton start = new JButton("Start");
	private JButton startAutoMode = new JButton("Start auto mode");
	
	private JTextPane flightsToDisplay = new JTextPane();
	private JTextPane execution = new JTextPane();
	private JTextPane boardingRoom = new JTextPane();
	private JTextPane gateway = new JTextPane();
	
	private JScrollPane scrollFlights = new JScrollPane(flightsToDisplay);
	private JScrollPane scrollExecution = new JScrollPane(execution);
	
	public Window() 
	{
		newDeparture.setBounds(50, 30, 130, 30);
		newArrival.setBounds(50, 70, 130, 30);
		newStepover.setBounds(50, 110, 130, 30);
		start.setBounds(50, 150, 130, 30);
		startAutoMode.setBounds(50, 190, 130, 30);
		
		boardingRoom.setBounds(50, 240, 130, 40);
		gateway.setBounds(50, 290, 130, 40);
		
		scrollFlights.setBounds(230, 30, 130, 300);
		scrollExecution.setBounds(410, 30, 240, 300);
		
		newDeparture.addActionListener(this);
		newArrival.addActionListener(this);
		newStepover.addActionListener(this);
		start.addActionListener(this);
		startAutoMode.addActionListener(this);
		
		boardingRoom.setEditable(false);
		gateway.setEditable(false);
		flightsToDisplay.setEditable(false);
		execution.setEditable(false); 
		
		MutableAttributeSet center = new SimpleAttributeSet();		
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		StyledDocument doc; 
		doc = boardingRoom.getStyledDocument();		
		doc.setParagraphAttributes(0, 0, center, true);
		doc = gateway.getStyledDocument();
		doc.setParagraphAttributes(0, 0, center, true);
		
		boardingRoom.setBackground(Color.GRAY);
		gateway.setBackground(Color.GRAY);
		
		boardingRoom.setText("Boarding rooms available : " + BoardingRoom.getAvailableBR());
		gateway.setText("Gateways available : " + Gateway.getAvailableGW());
		
		panel.add(newDeparture);
		panel.add(newArrival);
		panel.add(newStepover);
		panel.add(start);
		panel.add(startAutoMode);
		panel.add(boardingRoom);
		panel.add(gateway);
		panel.add(scrollFlights);
		panel.add(scrollExecution);
		panel.setBackground(Color.GRAY);
		panel.setLayout(null);
		
		this.setTitle("Airport");
		this.setSize(700, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == newDeparture)
		{
			new Departure();
			flightsToDisplay.setText(flightsToDisplay.getText() + "Departure\n");
		}
		if (source == newArrival)
		{
			new Arrival();
			flightsToDisplay.setText(flightsToDisplay.getText() + "Arrival\n");
		}
		if (source == newStepover)
		{
			new Stepover();
			flightsToDisplay.setText(flightsToDisplay.getText() + "Stepover\n");
		}
		if (source == start)
		{
			for (Flight flight : Airport.getFlights())
				flight.startFlight();
			while(Airport.getFlights().size() != 0)
			{
				
			}
		}
	}
}
