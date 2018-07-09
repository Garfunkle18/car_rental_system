/**
 * A concrete small car class that extends from the Abstract car. This class makes use of the drive method and returns the fuel used based on how far the
 * the client has driven. 
 * 
 * @author Matthew Garfitt 
 * @version 10/03/2018
 */
public class SmallCar extends AbstractCar {
	public static final int FUEL_CAPACITY = 49;

	public SmallCar(RegistrationNumber registrationNumber) {
		super(registrationNumber, FUEL_CAPACITY);
	}
	

	/**
	 * The drive method that calculates and returns how much fuel has been consumed in a single journey. 
	 * @param distance An integer to represent the distance travelled by the client.
	 * @return An integer that returns the fuel consumption.
	 */
	@Override
	public int drive(int distance) {
		if (isRented() && !isEmpty() && distance > 0) {
			int fuelConsumed = distance / 20;
			if (distance % 20 != 0) {
				fuelConsumed += 1;
			 setFuel(getFuelAmount() - fuelConsumed);
				return fuelConsumed;
			}
			setFuel(getFuelAmount() - fuelConsumed);
			return fuelConsumed;

		}
		return 0;
	}
	
}
