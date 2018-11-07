package airport;

public class Gateway {
	
	private static final int limitGW = 3;
	private static int nbGW = 0;
	private static int availableGW = 0;
	private boolean available = true;
	
	private Gateway() {}
	
	public static synchronized Gateway creation()
	{
		if (nbGW < limitGW)
		{
			Gateway BR = new Gateway();
			nbGW++;
			availableGW++;
			return BR;
		}
		System.out.println("There are only " + limitGW + " gateways accepted !");
		return null;
	}
	
	public static int getLimitGW() { return limitGW; }

	public static int getNbGW() { return nbGW; }
	
	public static int getAvailableGW() { return availableGW; }
	
	public boolean getAvailable() { return this.available; }
	
	public synchronized boolean bookGW()
	{
		if (this.available)
		{
			this.available = false;
			availableGW--;
			return true;
		}
		return false;
	}
	
	public synchronized boolean freeGW()
	{
		if (!this.available)
		{
			this.available = true;
			availableGW++;
			return true;
		}
		return false;
	}
}
