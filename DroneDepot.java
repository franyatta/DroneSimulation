public class DroneDepot extends Depot{

	private int posX, posY;
	private static int nextID = 0;
	
	public DroneDepot(int x, int y) {
		super(x,y);
	}
	
	public int getNextID() {
		return nextID;
	}

	
	Drone deployDrone(int speed, int range, int limit, String m) {
		return new Drone(++nextID, posX, posY, speed, range, limit, m);
	}
	
	
	
}

