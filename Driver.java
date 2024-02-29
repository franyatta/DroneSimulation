import java.util.Random;

public class Driver {

	public static void main(String[] args) {
		
		DroneController controller = new DroneController(5, 0, 0, 10);
		DroneDepot depot1 = new DroneDepot(5,5);
		controller.signalDeploy(depot1);
		
		Random gen = new Random();
		int direction;
		for(int i = 0; i < 20; i++) {
			for (int j = 0; j < controller.getActiveDrones().size(); j++) {
				direction = gen.nextInt(2);
				if (direction == 0) {
					controller.getActiveDrones().get(j).moveX(gen.nextInt(-2,2));
				}
				else {
					controller.getActiveDrones().get(j).moveY(gen.nextInt(-2,2));
				}
			}
			controller.scanRange();
			
			if(gen.nextInt(2) == 0) {
				controller.signalDeploy(depot1);
			}
			else {
				controller.signalDrone("MSG", gen.nextInt(controller.getActiveDrones().size()));
			}
			
			System.out.println("Step " + i);
			System.out.println("DroneID\tposX\tposY\tMessage");
			for (Drone drone : controller.getActiveDrones()) {
				if (drone == null)
					break;
				System.out.println(drone.getId() + "\t" + drone.getPosX() + "\t" + drone.getPosY() + "\t" + drone.getMessage());
			}
		}
	}

}
