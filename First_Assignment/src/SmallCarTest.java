import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SmallCarTest {

	SmallCar car;
	SmallCar car1;
	
	@Before
	public void setUp() {
		car = new SmallCar(RegistrationNumber.getInstance());
		car1 = new SmallCar(RegistrationNumber.getInstance());
		//so that it can be driven.
		car.setRented(true);
	}
	
	@Test
	public void test_instance() {
		assertTrue(car instanceof SmallCar);
	}
	
	@Test
	public void test_getFuelCapacity() {
		car.drive(1);
		assertEquals(49, car.getFuelCapacity());
		assertFalse(car.isFull());
		car.topUpFuel(1);
		assertTrue(car.isFull());
	}
	
	@Test
	public void test_getFuelAmount() {
		assertEquals(49, car.getFuelAmount());
		car.drive(1);
		assertEquals(48, car.getFuelAmount());
	}
	
	@Test
	public void test_drive() {
		assertEquals(0, car.drive(-1));
		assertEquals(0, car.drive(0));
		assertEquals(49, car.drive(980));
		assertEquals(0, car.drive(100));
		assertTrue(car.isEmpty());
		car.topUpFuel(49);
		assertTrue(car.isFull());
	}

}
