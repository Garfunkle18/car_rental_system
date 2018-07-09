/**
 * This is the car interface class that represents a high level view of its methods, their return types and the parameters that
 * are used. 
 * 
 * @author Matthew Garfitt 
 * @version 10/03/2018
 */
public interface Car {
	
	RegistrationNumber getRegistrationNumber(); //returns the registration number of the car.
	
	int getFuelCapacity(); //returns fuel capacity of the car depending on the car's size.
	
	int getFuelAmount(); //returns current amount of fuel in the car.
	
	boolean isFull(); //checks whether the fuel tank is full or not.
	
	boolean isEmpty(); //checks whether there is any fuel in the car.
	
	boolean isRented(); //checks whether a driver is associated with a car.
	
	void setRented(boolean value); //sets rented to true or false.
	
	int topUpFuel(int litres); //adds fuel to the car and returns how much was added, (O if car is not rented or already full)
	
	void setFuel(int litres); //set fuel amount.
	
	int drive(int distance); //deducts amount of fuel in the tank and returns the amount of fuel consumed.
}

