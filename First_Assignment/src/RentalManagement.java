/**
 * The rental management system, this is a singleton class to ensure that it only generates one instance of itself. This means that the values that are
 * stored inside this class are consistent. 
 * As soon as this class is called, 50 cars are added to the system, 30 small cars and 20 large cars, this means that the system is ready to use. 
 * The client can use this class to issue cars and to terminate contracts providing the prerequisites are met. 
 * 
 * @author Matthew Garfitt 
 * @version 10/03/2018
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Calendar;

public class RentalManagement {
	private static final RentalManagement INSTANCE = new RentalManagement();
	private static Map<DriverLicence, Car> rentedCars;
	private static List<Car> availableCars;
	private static final int LARGE_CAR_CAPACITY = 20;
	private static final int SMALL_CAR_CAPACITY = 30;
	private static final int SMALL_CAR_AGE = 21;
	private static final int LARGE_CAR_AGE = 25;
	private static final int SMALL_CAR_LICENCE_HELD = 1;
	private static final int LARGE_CAR_LICENCE_HELD = 5;

	public RentalManagement() {
		rentedCars = new HashMap<DriverLicence, Car>();
		availableCars = new ArrayList<>();
		addLargeCars();
		addSmallCars();
	}

	/**
	 * Adds 20 large cars to the system.
	 */
	private static void addLargeCars() {
		for (int i = 0; i < LARGE_CAR_CAPACITY; i++) {
			availableCars.add(new LargeCar(RegistrationNumber.getInstance()));
		}
	}

	/**
	 * Adds 30 small cars to the system.
	 */
	private static void addSmallCars() {
		for (int i = 0; i < SMALL_CAR_CAPACITY; i++) {
			availableCars.add(new SmallCar(RegistrationNumber.getInstance()));
		}
	}

	/**
	 * Returns an instance of itself to create a singleton.
	 * @return An instance of the rental management system.
	 */
	public static RentalManagement getInstance() {
		return INSTANCE;
	}

	/**
	 * Returns the number of available cars in the system.
	 * @param typeOfCar An enum that represents the type of car that the user is requesting.
	 * @return An integer that represents the number of available cars in the system.
	 */
	public static int availableCars(CarType typeOfCar) {
		int count = 0;
		if (typeOfCar == CarType.SMALLCAR) {
			for (Car car : availableCars) {
				if (car instanceof SmallCar)
					count++;
			}
		} else {
			for (Car car : availableCars) {
				if (car instanceof LargeCar)
					count++;
			}
		}
		return count;
	}
	
	/**
	 * Copies a car object to create defensive copying.
	 * @param car The car object.
	 * @return A copied car object.
	 */
	public Car copyCar(Car car) {
		Car copyCar;
		if(car instanceof SmallCar){ 
			copyCar = new SmallCar(car.getRegistrationNumber());
		return car;
		}
		else{
			copyCar = new LargeCar(car.getRegistrationNumber());
		return car;
		}
	}

	/**
	 * Returns a collection of cars that have currently been issued to drivers.
	 * @return An array list of cars that have been issued.
	 */
	public List<Car> getRentedCars() {
		ArrayList<Car> carsOut = new ArrayList<>();
		for(Car car : rentedCars.values()) {
			Car car1 = copyCar(car);
			carsOut.add(car1);
		}
		return carsOut;
	}

	/**
	 * Returns the car currently in use by the driver.
	 * @param licence A licence object that represents the driver.
	 * @return The car object if a client is renting it and null if the driver is currently not renting a car.
	 */
	public Car getCar(DriverLicence licence) {
		return rentedCars.get(licence);
	};
	
	/**
	 * Manipulates the date using the calendar class and then sets it back as a date object.
	 * @param year An integer to represent the number of years back the date goes.
	 * @return A date object that is the new date that has been set.
	 */
	public Date setDate(int year) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.YEAR, -year);
		
		return calendar.getTime();
	}

	/**
	 * An issue car method that returns true if the car has been issued, and false if it hasn't.
	 * @param licence The driver that is trying to rent a car.
	 * @param typeOfCar The type of car that the client has selected
	 * @return A boolean value that returns true if the car has been issued and false if it hasn't.
	 */
	public boolean issueCar(DriverLicence licence, CarType typeOfCar) {
		Date dateOfBirth = licence.getDob();
		Date dateOfIssue = licence.getDateOfIssue();
		if ((typeOfCar == CarType.SMALLCAR) && (getCar(licence) == null) && (licence.isFullLicence()) && dateOfBirth.before(setDate(SMALL_CAR_AGE)) && dateOfIssue.before(setDate(SMALL_CAR_LICENCE_HELD))){
			for (Car car : availableCars) {
				if (car instanceof SmallCar && car.isFull() && !(car.isRented())) {
					rentedCars.putIfAbsent(licence, car);
					availableCars.remove(car);
					car.setRented(true);
					return true;
				}
			}
		} else if((typeOfCar == CarType.LARGECAR) && (getCar(licence) == null) && (licence.isFullLicence()) && dateOfBirth.before(setDate(LARGE_CAR_AGE)) && dateOfIssue.before(setDate(LARGE_CAR_LICENCE_HELD))){
			for (Car car : availableCars) {
				if (car instanceof LargeCar && car.isFull() && !(car.isRented())) {
					rentedCars.putIfAbsent(licence, car);
					availableCars.remove(car);
					car.setRented(true);
					return true;
				}
			}
		}
		return false;
	};

	 /**
	  * Method that terminates the contract between the client and the car that is currently rented. 
	  * @param licence The driver that is returning the car
	  * @return An integer that represents the amount of fuel needed to be added back into the car, and -1 if there is an error with terminating the contract.
	  */
	public int terminateRental(DriverLicence licence) {
		if(rentedCars.containsKey(licence)){
			Car car = rentedCars.get(licence);
			int difference = car.getFuelCapacity() - car.getFuelAmount();
			rentedCars.remove(licence, car);
			availableCars.add(car);
			car.setRented(false);
			return difference;
		} else if(!(rentedCars.containsKey(licence)))
			return -1;
		
		return 0;
	}
}
