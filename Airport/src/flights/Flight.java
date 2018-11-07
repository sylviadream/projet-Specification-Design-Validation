package flights;

import airport.Airport;
import airport.BoardingRoom;
import airport.Gateway;

public abstract class Flight implements Runnable {
	
	private static int nbFlights = 0;
	protected static int nbDepartures = 0;
	protected static int nbArrivals = 0;
	protected static int nbStepovers = 0;
	protected Thread threadFlight = new Thread(this);
	
	public Flight()
	{
		nbFlights++;
		Airport.addFlight(this);
	}
	
	public static int getNbFlights() { return nbFlights; }
	
	public static int getNbDepartures() { return nbDepartures; }
	
	public static int getNbArrivals() { return nbArrivals; }
	
	public static int getNbStepovers(){ return nbStepovers; }
	
	public abstract void run();
	
	public void startFlight()
	{
		threadFlight.start();
	}
	
	protected void endFlight()
	{
		
		Airport.removeFlight(this);
		try {
			threadFlight.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
