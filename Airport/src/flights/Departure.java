package flights;

import airport.Airport;
import airport.BoardingRoom;
import airport.Gateway;

public class Departure extends Flight {
	
	public Departure()
	{
		nbDepartures++;
	}
	
	public void run()
	{
		try {
			//Booking boarding room in first
			System.out.println("Booking boarding room for departure...");
			Thread.sleep(1000);
			int i = 0;
			while (!Airport.getListBR()[i].bookBR())
			{
				i++;
				if (i >= BoardingRoom.getNbBR())
					i = 0;
			}
			System.out.println("Boarding room booked for departure");
			
			//Booking gateway in second
			System.out.println("Booking gateway for departure...");
			Thread.sleep(1000);
			i = 0;
			while (!Airport.getListGW()[i].bookGW())
			{
				i++;
				if (i >= Gateway.getNbGW())
					i=0;
			}
			System.out.println("Gateway booked for departure");
			
			//Boarding room and gateway book, we'll free them 
			System.out.println("Boarding...");
			Thread.sleep(1000);
			i = 0;
			while (!Airport.getListBR()[i].freeBR())
			{
				i++;
				if (i >= BoardingRoom.getNbBR())
					i = 0;
			}
			i = 0;
			while (!Airport.getListGW()[i].freeGW())
			{
				i++;
				if (i >= Gateway.getNbGW())
					i = 0;
			}
			System.out.println("Boarding room and gateway are free");
			
			//End of thread
			endFlight();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}
