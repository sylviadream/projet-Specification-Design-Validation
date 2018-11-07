package main;

import airport.Airport;
import flights.Departure;
import flights.Flight;
import flights.Arrival;
import flights.Stepover;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Airport airport = Airport.creation();
		airport.initAirport();
		
		//Window window = new Window();
		
		new Thread() {
			public void run() {
				int i = 0;
				while(i < 100)
				{
					int j;
					Random random = new Random();
					Flight flight = null;
					j = random.nextInt(5);
					if (j == 0 || j == 1)
						flight = new Departure();
					if (j == 2 || j == 3)
						flight = new Arrival();
					if (j == 4)
						flight = new Stepover();
					flight.startFlight();
					i++;
				}
			}
		}.start();
	}
}
