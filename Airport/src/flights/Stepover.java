package flights;

import airport.Airport;
import airport.BoardingRoom;
import airport.Gateway;

public class Stepover extends Flight {
	
	public Stepover()
	{
		nbStepovers++;
	}
	
	public void run()
	{
		try {
			//Booking gateway in first
			System.out.println("Booking gateway for stopover...");
			Thread.sleep(1000);
			int i = 0;
			while (!Airport.getListGW()[i].bookGW())
			{
				i++;
				if (i >= Gateway.getNbGW())
					i = 0;
			}
			System.out.println("Gateway booked for stepover");
			
			//Gateway book, we'll free it
			System.out.println("Landing...");
			Thread.sleep(1000);;
			i = 0;
			while (!Airport.getListGW()[i].freeGW())
			{
				i++;
				if (i >= Gateway.getNbGW())
					i = 0;
			}
			System.out.println("Gateway is free");
			
			//Booking boarding room now to allow passengers to wait next departure of their plane
			System.out.println("Booking boarding room for stopover...");
			Thread.sleep(1000);
			i = 0;
			while (!Airport.getListBR()[i].bookBR())
			{
				i++;
				if (i >= BoardingRoom.getNbBR())
					i=0;
			}
			System.out.println("Broading room booked for stopover");
			
			//Booking new gateway to boarding
			System.out.println("Booking gateway for end of stopover...");
			Thread.sleep(1000);
			i = 0;
			while (!Airport.getListGW()[i].bookGW())
			{
				i++;
				if (i >= Gateway.getNbGW())
					i = 0;
			}
			System.out.println("Gateway booked for end of stepover");
			
			//Boarding room and gateway book for stopover, we'll free them
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
