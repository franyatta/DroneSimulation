public class Drone extends Entity{
	
	public enum Status{
		ACTIVE, IDLE, RESERVED;
	}
	private int id;
	private int posX, posY;
	private int topSpeed;
	private int signalRange;
	private int payload;
	private int currentPayload;
	private String message;
	private Status status;
	
	public Drone(int id, int x, int y, int speed, int signalRange, int payload) {
		super(x,y);
		this.id = id;
		topSpeed = speed;
		this.signalRange = signalRange;
		currentPayload = 0;
		this.payload = payload;
		message = "";
		setStatus(Status.ACTIVE);
	
	}
	public Drone(int id, int x, int y, int speed, int range, int limit, String m) {
		super(x,y);
		this.id = id;
		topSpeed = speed;
		signalRange = range;
		currentPayload = 0;
		payload = limit;
		message = m;
		setStatus(Status.ACTIVE);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}
	public int getTopSpeed() {
		return topSpeed;
	}
	public int getSignalRange() {
		return signalRange;
	}
	public void setSignalRange(int signalRange) {
		this.signalRange = signalRange;
	}
	public int getCurrentPayload() {
		return currentPayload;
	}
	public void setCurrentPayload(int currentPayload) {
		this.currentPayload = currentPayload;
	}
	public int getPayload() {
		return payload;
	}
	public void setPayload(int payload) {
		this.payload = payload;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public void moveX(int dx) {
		posX = posX + dx;
		
	}
	public void moveY(int dy) {
		posY = posY + dy;
		
	}
	// weight method 
	public void loadPayload(double payLoadWeight) {
		if(payLoadWeight + currentPayload <= payload) {
			currentPayload += payLoadWeight;
		}
		else {
			System.out.println(" Payload exceeeds the payload limit for Drone ");
		}
	}
}
