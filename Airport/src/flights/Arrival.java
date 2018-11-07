package flights;

import airport.Airport;
import airport.Gateway;

public class Arrival extends Flight {
	
	public Arrival()
	{
		nbArrivals++;
	}
	
	public void run()
	{
		try {
			//Booking gateway
			System.out.println("Booking gateway for arrival...");
			Thread.sleep(1000);
			int i = 0;
			while (!Airport.getListGW()[i].bookGW())
			{
				i++;
				if (i >= Gateway.getNbGW())
					i = 0;
			}
			System.out.println("Gateway booked for arrival");
			
			//Gateway book, we'll free it
			System.out.println("Landing...");
			Thread.sleep(1000);
			i = 0;
			while (!Airport.getListGW()[i].freeGW())
			{
				i++;
				if (i >= Gateway.getNbGW())
					i = 0;
			}
			System.out.println("Gateway is free");
			
			//End of thread
			endFlight();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
