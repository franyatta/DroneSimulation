import java.util.ArrayList;
import java.util.Random;
public class DroneController extends Entity {
	
	private static int simTime = 0;
	
	private ArrayList<Drone> activeDrones;
	private ArrayList<Drone> idleDrones;
	private ArrayList<ReservedDrone> reservedDrones;
	private ArrayList<Drone> dronesInRange;
	private int maxDrones;
	private int signalRange;
	
	private class ReservedDrone{
		Drone drone;
		int endTime;
		
		ReservedDrone(Drone drone, int endTime){
		this.drone = drone;
		this.endTime = endTime;
		}
		
	}
	
	DroneController(int max, int x, int y, int range){
		super(x,y);
		maxDrones = max;
		signalRange = range;
		
		activeDrones = new ArrayList<>();
		idleDrones = new ArrayList<>();
		reservedDrones = new ArrayList<>();
		dronesInRange = new ArrayList<>();
	}
	
	public ArrayList<Drone> getActiveDrones() {
		return activeDrones;
	}

	public void setActiveDrones(ArrayList<Drone> activeDrones) {
		this.activeDrones = activeDrones;
	}

	public ArrayList<Drone> getDronesInRange() {
		return dronesInRange;
	}
	public ArrayList<Drone> getIdleDrones(){
		return idleDrones;
	}
	public ArrayList<ReservedDrone> getReservedDrones(){
		return reservedDrones;
	}
	public void setDronesInRange(ArrayList<Drone> dronesInRange) {
		this.dronesInRange = dronesInRange;
	}

	public int getMaxDrones() {
		return maxDrones;
	}

	public void setMaxDrones(int maxDrones) {
		this.maxDrones = maxDrones;
	}

	public int getSignalRange() {
		return signalRange;
	}

	public void setSignalRange(int signalRange) {
		this.signalRange = signalRange;
	}
	public void resetIdleDrones() {
		for(Drone d : idleDrones) {
			d.setStatus(Drone.Status.ACTIVE);
		}
		idleDrones.clear();
	}
	public void chooseIdleDrones() {
		ArrayList<Integer> idxs = new ArrayList<>();
		
		Random gen = new Random();
		int upperBound = activeDrones.size();
		Integer idx;
		while(idxs.size() != 5) {
			do {
				idx = gen.nextInt(upperBound);
			}while((idxs.contains(idx)));
			idxs.add(idx);
		}
		for(int i : idxs) {
			activeDrones.get(i).setStatus(Drone.Status.IDLE);
			idleDrones.add(activeDrones.get(i));
		}
	}
	public void reserveDrones() {
		int numToBeReserved = (int) (0.5 * idleDrones.size());
		Random gen = new Random();
		Drone reservedDrone;
		while(numToBeReserved != 0) {
			reservedDrone = idleDrones.get(gen.nextInt(idleDrones.size()));
			reservedDrone.setStatus(Drone.Status.RESERVED);
			reservedDrones.add(new ReservedDrone(reservedDrone, simTime+1));
			idleDrones.remove(reservedDrone);
			numToBeReserved--;
		}
	}
	public void reclaimDrones() {
		for(ReservedDrone rd : reservedDrones) {
			if(rd.endTime == simTime) {
				rd.drone.setStatus(Drone.Status.ACTIVE);
				activeDrones.add(rd.drone);
			}
		}
		reservedDrones.removeIf(rd -> rd.drone.getStatus() == Drone.Status.ACTIVE);
	}
	void signalDrone(String message, int ID) {
		if(dronesInRange.isEmpty()) {
			System.out.println("No drones in range");
			return;
		}
		for(int i = 0; i < dronesInRange.size(); i++) {
			if(dronesInRange.get(i).getId() == ID) {
				dronesInRange.get(i).setMessage(message);
			}
		}
	}
	void signalDeploy(DroneDepot depot) {
		if(activeDrones.size()< maxDrones) {
			activeDrones.add(depot.deployDrone(0,5,0,null));
		}
		else {
			System.out.println("Maximum amount of drones reached.");
		}
	}
	void scanRange() {
		int dist = 0;
		for(Drone d: activeDrones) {
			dist = (Math.abs(posX - d.getPosX()) + (Math.abs(posY - d.getPosY())));
			if(dist < signalRange) {
				if(!dronesInRange.contains(d)) {
					dronesInRange.add(d);
					}
			}else {
			dronesInRange.remove(d); 
		}
		
		}
	}
	public void print() {
		System.out.println("Time step " + simTime);
		System.out.println("Active drones");
		for(Drone d : activeDrones) {
			System.out.println(d.getId() + ": " + d.getStatus());
		}
		System.out.println("Idle drones");
		for(Drone d: idleDrones) {
			System.out.println(d.getId() + ": " + d.getStatus());
		}
		System.out.println("Reserved drones");
		for(ReservedDrone rd : reservedDrones) {
			System.out.println(rd.drone.getId() + " " + rd.drone.getStatus() + " until " + rd.endTime);
		}
		System.out.println();
	}
	public static void advanceTime() {
		simTime++;
	}
	public static int getTime() {
		return simTime;
	}
}
		
		
