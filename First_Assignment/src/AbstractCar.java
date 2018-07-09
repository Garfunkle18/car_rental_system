/**
 * The abstract car class which implements all of the methods from the car interface, this class leaves the drive method as abstract,
 * to be implemented in the large and small car classes.
 * 
 * @author Matthew Garfitt 
 * @version 10/03/2018
 */
public abstract class AbstractCar implements Car {

	private final RegistrationNumber registrationNumber;
	private int fuel;
	private final int fuelCapacity;
	private boolean rented;

	public AbstractCar(RegistrationNumber registrationNumber, int fuelCapacity) {
		this.registrationNumber = RegistrationNumber.getInstance();
		this.fuelCapacity = fuelCapacity;
		fuel = fuelCapacity;
		rented = false;
	}

	/**
	 * Retrieves the registration number associated with this particular car.
	 * @return The registration number that was generated in the constructor.
	 */
	@Override
	public RegistrationNumber getRegistrationNumber() { return registrationNumber; }

	/**
	 * Retrieves the maximum amount of fuel the car can have.
	 * @return The fuel capacity of the car depending on it's instance.
	 */
	@Override
	public int getFuelCapacity() { return fuelCapacity; }

	/**
	 * Retrieves the car's current fuel level.
	 * @return An int representing the amount of fuel in the car.
	 */
	@Override
	public int getFuelAmount() { return fuel; }

	
	/**
	 * Checks whether the car's fuel level is currently full.
	 * @return True if the fuel capacity is equal to the current fuel level.
	 */
	@Override
	public boolean isFull() { return fuel == getFuelCapacity(); }

	/**
	 * Checks whether the car's current fuel level is currently empty.
	 * @return True if the fuel level is less than or equal to 0.
	 */
	@Override
	public boolean isEmpty() { return fuel <= 0; }
	
	/**
	 * Method to change whether a car is rented or not, this is used when the car is issued or terminated.
	 * @param A boolean value that sets whether the car is rented or not.
	 */
	@Override
	public void setRented(boolean value) {
		rented = value;
	}

	/**
	 * Checks whether the car is rented.
	 * @return A boolean that returns true if the car is currently rented.
	 */
	@Override
	public boolean isRented() { return rented; }
	
	/**
	 * Sets the fuel to a given level. This is used when the car is using fuel in the drive method.
	 * @param Sets the cars fuel level to the number of litres.
	 */
	@Override
	public void setFuel(int litres) { 
		fuel = litres;
	}

	/**
	 * Adds fuel to the car as long as the tank isn't full, and the number of litres isn't negative.
	 * @param An integer to indicate the number of litres to be added to the tank.
	 * @return An integer to indicate the number of litres that were actually added to the car.
	 */
	@Override
	public int topUpFuel(int litres) {
		if (isFull() || litres <= 0 || !isRented())
			return 0;

		fuel += litres;

		if (fuel > fuelCapacity) {
			int fuelAdded = fuel - fuelCapacity;
			fuel = fuelCapacity;
			return fuelAdded;
		}
		
		return litres;
	}

	/**
	 * Abstract drive method, to be implemented in the small and large car classes.
	 * @param An integer to indicate the distance driven.
	 */
	public abstract int drive(int distance);
	
	/**
	 * A method to represent the car details.
	 * @return The registration number to uniquely identify the car.
	 */
	@Override
	public String toString(){
		return registrationNumber + "";
	}
}
