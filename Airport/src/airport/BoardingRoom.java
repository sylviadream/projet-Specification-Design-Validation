package airport;

public class BoardingRoom {
	
	private static final int limitBR = 4;
	private static int nbBR = 0;
	private static int availableBR = 0;
	private boolean available = true;
	
	private BoardingRoom() {}
	
	public static synchronized BoardingRoom creation()
	{
		if (nbBR < limitBR)
		{
			BoardingRoom BR = new BoardingRoom();
			nbBR++;
			availableBR++;
			return BR;
		}
		System.out.println("There are only " + limitBR + " boarding rooms accepted !");
		return null;
	}
	
	public static int getLimitBR() { return limitBR; }

	public static int getNbBR() { return nbBR; }
	
	public static int getAvailableBR() { return availableBR; }
	
	public synchronized boolean bookBR()
	{
		if (this.available)
		{
			this.available = false;
			availableBR--;
			return true;
		}
		return false;
	}
	
	public synchronized boolean freeBR()
	{
		if (!this.available)
		{
			this.available = true;
			availableBR++;
			return true;
		}
		return false;
	}
}
