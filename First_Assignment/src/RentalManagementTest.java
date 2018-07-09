import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class RentalManagementTest {
	
	RentalManagement manager;
	Date nameDob;
	Date name1Dob;
	Date name2Dob;
	Date name3Dob;
	Date name4Dob;
	Date name5Dob;
	Date nameDoi;
	Date name1Doi;
	Date name2Doi;
	Date name3Doi;
	Date name4Doi;
	Date name5Doi;
	
	Name name;
	Name name1;
	Name name2;
	Name name3;
	Name name4;
	Name name5;
	
	DriverLicence licence;
	DriverLicence licence1;
	DriverLicence licence2;
	DriverLicence licence3;
	DriverLicence licence4;
	DriverLicence licence5;

	@Before
	public void setUp() {
		manager = RentalManagement.getInstance();
		Calendar calendar = Calendar.getInstance();
		Calendar calendar1 = (Calendar) calendar.clone();
		Calendar calendar2 = (Calendar) calendar.clone();
		Calendar calendar3 = (Calendar) calendar.clone();
		Calendar calendar4 = (Calendar) calendar.clone();
		Calendar calendar5 = (Calendar) calendar.clone();
		Calendar calendar6 = (Calendar) calendar.clone();
		Calendar calendar7 = (Calendar) calendar.clone();
		Calendar calendar8 = (Calendar) calendar.clone();
		Calendar calendar9 = (Calendar) calendar.clone();
		
		calendar1.add(Calendar.YEAR, -1);
		calendar2.add(Calendar.YEAR, -5);
		calendar3.add(Calendar.YEAR, -21);
		calendar4.add(Calendar.YEAR, -21);
		calendar5.add(Calendar.YEAR, -25);
		calendar6.add(Calendar.YEAR, -25);
		calendar7.add(Calendar.YEAR, -20);
		calendar8.add(Calendar.YEAR, -24);
		calendar4.add(Calendar.MONTH, 7);
		calendar6.add(Calendar.MONTH, 7);
		nameDob = calendar3.getTime();
		name1Dob = calendar5.getTime();
		name2Dob = calendar4.getTime();
		name3Dob = calendar6.getTime();
		name4Dob = calendar7.getTime();
		name5Dob = calendar8.getTime();
		nameDoi = calendar1.getTime();
		name1Doi = calendar2.getTime();
		name2Doi = calendar2.getTime();
		name3Doi = calendar2.getTime();
		name4Doi = calendar2.getTime();
		name5Doi = calendar2.getTime();
		
		
		calendar9.add(Calendar.YEAR, -4);
		
		name = new Name("Matthew", "Garfitt");
		name1 = new Name("Daniel", "Jzepee");
		name2 = new Name("Elise", "Boona");
		name3 = new Name("Nadine","Spence");
		name4 = new Name("Peter", "Staker");
		name5 = new Name("George","Cauldron");
		
		licence = new DriverLicence(name, nameDob, nameDoi, true);
		licence1 = new DriverLicence(name1, name1Dob, name1Doi, true);
		licence2 = new DriverLicence(name2, name2Dob, name2Doi, true);
		licence3 = new DriverLicence(name3, name3Dob, name3Doi, true);
		licence4 = new DriverLicence(name4, name4Dob, name4Doi, true);
		licence5 = new DriverLicence(name5, name5Dob, name5Doi, true);
	}
	
	@Test
	public void test_getCar(){
		manager.issueCar(licence, CarType.SMALLCAR);
		manager.issueCar(licence1, CarType.LARGECAR);
		assertTrue(manager.getCar(licence) instanceof SmallCar);
		assertTrue(manager.getCar(licence1) instanceof LargeCar);
		assertFalse(manager.getCar(licence) instanceof LargeCar);
		assertFalse(manager.getCar(licence1) instanceof SmallCar);
		assertEquals(null, manager.getCar(licence2));
		manager.terminateRental(licence);
		manager.terminateRental(licence1);
	}
	
	@Test
	public void test_issueCar() {
		assertEquals(true, manager.issueCar(licence, CarType.SMALLCAR));
		// a car has already been issued to this licence holder.
		assertEquals(false, manager.issueCar(licence, CarType.SMALLCAR));
		assertEquals(true, manager.issueCar(licence1, CarType.LARGECAR));
		//this driver is not old enough, they turn 21 in July.
		assertEquals(false, manager.issueCar(licence2, CarType.SMALLCAR));
		//this driver is not old enough, they turn 25 in July.
		assertEquals(false, manager.issueCar(licence3, CarType.LARGECAR));
		//this driver is only 24, they are not old enough to rent a large car.
		assertEquals(false, manager.issueCar(licence5, CarType.LARGECAR));
		//but they are old enough to rent a small car.
		assertEquals(true, manager.issueCar(licence5, CarType.SMALLCAR));
		manager.terminateRental(licence);
		manager.terminateRental(licence1);
		manager.terminateRental(licence5);
	}
	
	//this method checks whether the correct number of cars are still available after a few have been issued.
	@Test
	public void test_availableCars(){
		assertEquals(30, manager.availableCars(CarType.SMALLCAR));
		assertEquals(20, manager.availableCars(CarType.LARGECAR));
	}
	
	//3 cars have been issued to drivers.
	@Test
	public void test_getRentedCars(){
		assertEquals(0, manager.getRentedCars().size());
		manager.issueCar(licence, CarType.SMALLCAR);
		manager.issueCar(licence1, CarType.LARGECAR);
		manager.issueCar(licence5, CarType.SMALLCAR);
		assertEquals(3, manager.getRentedCars().size());
		manager.terminateRental(licence);
		manager.terminateRental(licence1);
		manager.terminateRental(licence5);
		assertEquals(0, manager.getRentedCars().size());
	}
	
	@Test
	public void test_terminateRental(){
		manager.issueCar(licence, CarType.SMALLCAR);
		manager.issueCar(licence1, CarType.LARGECAR);
		manager.issueCar(licence5, CarType.SMALLCAR);
		manager.getCar(licence5).drive(200);
		manager.getCar(licence1).drive(61);
		manager.getCar(licence1).drive(66);
		assertEquals(0, manager.terminateRental(licence));
		assertEquals(13, manager.terminateRental(licence1));
		assertEquals(10, manager.terminateRental(licence5));
		//no car was issued to this driver so -1 indicates an error in the system.
		assertEquals(-1, manager.terminateRental(licence2));
	}

}
