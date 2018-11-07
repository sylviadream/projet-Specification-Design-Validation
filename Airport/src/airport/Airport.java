package airport;

import flights.*;
import java.util.*;

public class Airport {
	
	private static final int limitAirport = 1;
	private static int nbAirport = 0;
	private static BoardingRoom[] ListBR = new BoardingRoom[BoardingRoom.getLimitBR()];
	private static Gateway[] ListGW = new Gateway[Gateway.getLimitGW()];
	private static List<Flight> Flights = new ArrayList<Flight>();
	
	private Airport() {}
	
	public static synchronized Airport creation()
	{
		if (nbAirport < limitAirport)
		{
			Airport Airport = new Airport();
			nbAirport++;
			return Airport;
		}
		System.out.println("There are only " + limitAirport + " airport accepted !");
		return null;
	}
	
	public void initAirport()
	{
		for (int i = 0; i < ListBR.length; i++)
			ListBR[i] = BoardingRoom.creation();
		for (int i = 0; i < ListGW.length; i++)
			ListGW[i] = Gateway.creation();
	}
	
	public static BoardingRoom[] getListBR() { return ListBR; }
	
	public static Gateway[] getListGW() { return ListGW; }
	
	public static List<Flight> getFlights() { return Flights; }
	
	public static void addFlight(Flight flight)
	{
		Flights.add(flight);
	}
	
	public static void removeFlight(Flight flight)
	{
		Flights.remove(flight);
	}
}
