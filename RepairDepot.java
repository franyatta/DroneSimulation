public class RepairDepot extends Depot {

	class RepairDrone extends Entity {
		int id;
		int posX, posY;

		RepairDrone(int id, int posX, int posY) {
			super(posX, posY);
			this.id = id;

		}

		void repair(Drone d) {
			d.setTopSpeed(d.getTopSpeed() * 2);
		}

		void moveX(int dx) {
			posX += dx;
		}

		void moveY(int dy) {
			posY += dy;
		}
	}

	private int id;
	private static int nextId = 0;

	public RepairDepot(int id, int posX, int posY) {
		super(posX, posY);
		this.setId(id);

	}

	public RepairDrone deployDrone() {
		return new RepairDrone(nextId++, posX, posY);
	}

	void repair(Drone d) {
		d.setTopSpeed(d.getTopSpeed() * 3);
		;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
