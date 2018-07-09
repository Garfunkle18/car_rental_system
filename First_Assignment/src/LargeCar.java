/**
 * A concrete large car class that extends from the Abstract car. This class makes use of the drive method, the behaviour of this method changes depending on the distance that
 * the client has driven. 
 * 
 * @author Matthew Garfitt 
 * @version 10/03/2018
 */
public class LargeCar extends AbstractCar {
	public static final int FUEL_CAPACITY = 60;

	public LargeCar(RegistrationNumber registrationNumber){
		super(registrationNumber, FUEL_CAPACITY);
	}
	
	/**
	 * Drive method that returns the fuel used in a single journey.
	 * @param An integer representing the distance that has been driven.
	 * @return An integer to represent the amount of fuel that has been consumed.
	 */
	@Override
	public int drive(int distance) {
		if (isRented() && getFuelAmount() > 0 && distance > 0) {
			if (distance <= 50) {
				int fuelConsumed = distance / 10;
				if (distance % 10 != 0) {
					fuelConsumed += 1;
					setFuel(getFuelAmount() - fuelConsumed);
					return fuelConsumed;
				} else {
					setFuel(getFuelAmount() - fuelConsumed);
					return fuelConsumed;
				}
			}
			int difference = distance - 50;
			distance = 50;
			int tenKMH = distance / 10;
			int fifteenKMH = difference / 15;
			if (distance % 10 != 0) {
				tenKMH += 1;
			}
			if (difference % 15 != 0) {
				fifteenKMH += 1;
			}
			int fuelConsumed = tenKMH + fifteenKMH;
			setFuel(getFuelAmount() - fuelConsumed);
			return fuelConsumed;
		}
		return 0;
	}
}
