/**
 * This class simply tests the system interface from the client's perspective. A static main method has been used to test the behavior of the objects.
 * 
 * @author Matthew Garfitt 
 * @version 10/03/2018
 */
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Test {
	private static Calendar calendar = new GregorianCalendar();

	public static void main(String[] args) {
		Name name = new Name("Matthew", "Garfitt");
		Calendar cal = calendar.getInstance();
		Calendar calTemp = (Calendar) cal.clone();
		Calendar calTemp2 = (Calendar) cal.clone();
		calTemp.add(Calendar.YEAR, -25);
		calTemp2.add(Calendar.YEAR, -5);
		Date dateOfIssue = calTemp2.getTime();
		Date dateOfBirth = calTemp.getTime();
		DriverLicence licence = new DriverLicence(name, dateOfBirth, dateOfIssue, true);
		DriverLicence licence2 = new DriverLicence(name, dateOfBirth, dateOfIssue, true);
		System.out.println(licence);
		RentalManagement manager = new RentalManagement();
		manager.issueCar(licence, CarType.SMALLCAR);
		manager.issueCar(licence2, CarType.LARGECAR);
		System.out.println(manager.availableCars(CarType.SMALLCAR));
		System.out.println(manager.availableCars(CarType.LARGECAR));
		System.out.println(manager.getRentedCars());
		System.out.println(manager.getCar(licence));
		System.out.println(manager.getCar(licence).drive(1));
		System.out.println(manager.getCar(licence2).drive(1));
		System.out.println(manager.getCar(licence).getFuelAmount());
		System.out.println(manager.getCar(licence2).getFuelAmount());
		System.out.println(manager.terminateRental(licence2));
	}
	
}
